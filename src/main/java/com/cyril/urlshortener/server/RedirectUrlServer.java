package com.cyril.urlshortener.server;

import com.cyril.urlshortener.cache.UrlCacheRefresher;
import com.cyril.urlshortener.listener.DBCleaner;
import com.cyril.urlshortener.mapper.ShortUrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RedirectUrlServer {
    private static final Logger LOG = LoggerFactory.getLogger(RedirectUrlServer.class);

    @Resource
    private ShortUrlMapper shortUrlMapper;

    @Resource
    private UrlCacheRefresher urlCacheRefresher;

    @Resource
    private DBCleaner dbCleaner;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    @PostConstruct
    private void loadCache() {
        scheduler.scheduleWithFixedDelay (urlCacheRefresher, 0, UrlCacheRefresher.REFRESH_PERIOD_IN_SECOND, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(dbCleaner, 0, DBCleaner.CLEAN_PROCESS_INTERVAL_IN_SECOND, TimeUnit.SECONDS);
    }

    public List<String> search(String shortUrlKey) {
        try {
            List<String> longUrls = searchInCache(shortUrlKey);
            if (longUrls.isEmpty()) {
                return searchInDB(shortUrlKey);
            }
            return longUrls;
        } catch (Exception e) {
            LOG.error("Error in RedirectUrlServer", e);
            return Collections.emptyList();
        }
    }

    private List<String> searchInDB(String shortUrlKey) {
        return shortUrlMapper.selectByString(shortUrlKey);
    }

    private List<String> searchInCache(String shortUrlKey) {
       return urlCacheRefresher.getUrlCache().get(shortUrlKey);
    }
}

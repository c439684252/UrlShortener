package com.cyril.urlshortener.server;

import com.cyril.urlshortener.cache.UrlCacheRefresher;
import com.cyril.urlshortener.mapper.ShortUrlMapper;
import com.cyril.urlshortener.utils.ToolBox;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Timer;

@Service
public class RedirectUrlServer {
    @Resource
    private ShortUrlMapper shortUrlMapper;

    @Resource
    private UrlCacheRefresher urlCacheRefresher;

    private static final Timer TIMER = new Timer();

    @PostConstruct
    private void loadCache() {
        TIMER.scheduleAtFixedRate(urlCacheRefresher, 0, ToolBox.getMinutesInLong(UrlCacheRefresher.REFRESH_PERIOD_IN_MINUTE));
    }

    public List<String> search(String shortUrlKey) {
        List<String> longUrls = searchInCache(shortUrlKey);
        if (longUrls.isEmpty()) {
            return searchInDB(shortUrlKey);
        }
        return longUrls;
    }

    private List<String> searchInDB(String shortUrlKey) {
        return shortUrlMapper.selectByString(shortUrlKey);
    }

    private List<String> searchInCache(String shortUrlKey) {
       return urlCacheRefresher.getUrlCache().get(shortUrlKey);
    }
}

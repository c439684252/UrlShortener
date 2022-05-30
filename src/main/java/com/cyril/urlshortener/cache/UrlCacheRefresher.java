package com.cyril.urlshortener.cache;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.TimerTask;

// todo Cache - Swap to Redis?
@Component
public class UrlCacheRefresher extends TimerTask {
    private static final Logger LOG = LoggerFactory.getLogger(UrlCacheRefresher.class);

    public static final double REFRESH_PERIOD_IN_MINUTE = 0.1;

    @Resource
    @Getter
    private UrlCache urlCache;

    @Override
    public void run() {
        LOG.info("Start refreshing cache...");
        urlCache.run();
        LOG.info("Refreshed {} items", urlCache.getCurrentCacheSize());
    }
}

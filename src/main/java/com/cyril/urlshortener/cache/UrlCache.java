package com.cyril.urlshortener.cache;

import com.cyril.urlshortener.dao.UrlPair;
import com.cyril.urlshortener.mapper.ShortUrlMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import static com.cyril.urlshortener.utils.Constant.URL_CACHE_CAPACITY;

@Component
public class UrlCache {
    private static final Logger LOG = LoggerFactory.getLogger(UrlCache.class);

    @Resource
    private ShortUrlMapper shortUrlMapper;

    @Getter
    private Map<String, List<String>> cache = new ConcurrentHashMap<>(URL_CACHE_CAPACITY);

    @Getter
    private int currentCacheSize = 0;

    public void run() {
        // Secure the re-load process
        cache = load();
    }

    public void add(Map<String, List<String>> map, UrlPair urlPair) {
        update(map, urlPair.getShortUrlKey(), urlPair.getLongUrl());
    }

    private List<String> addToList(List<String> list, String url) {
        boolean add = list.add(url);
        return list;
    }

    private void update(Map<String, List<String>> map ,String shortUrlKey, String longUrl) {
        if (map.get(shortUrlKey) != null) {
            map.computeIfPresent(shortUrlKey,
                    (key, value) -> addToList(value, longUrl));
        } else {
            List<String> longUrls = new ArrayList<>();
            longUrls.add(longUrl);
            map.put(shortUrlKey, longUrls);
        }
        this.currentCacheSize++;
    }

    public List<String> get(String shortUrlKey) {
        return cache.getOrDefault(shortUrlKey, Collections.emptyList());
    }

    private Map<String, List<String>> load() {
        Map<String, List<String>> tmpMap = new ConcurrentHashMap<>(URL_CACHE_CAPACITY);
        this.currentCacheSize = 0;
        LOG.debug("Reset url cache size count to 0");
        List<UrlPair> topN = shortUrlMapper.getTopN(URL_CACHE_CAPACITY);
        for (UrlPair e : topN) {
            add(tmpMap, e);
        }
        return tmpMap;
    }
}

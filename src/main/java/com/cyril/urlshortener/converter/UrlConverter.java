package com.cyril.urlshortener.converter;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.bean.ShortUrl;

public class UrlConverter {
    // todo
    public static String defaultConvert(String longUrl) {
        return longUrl.substring(0, 5);
    }

    public static String customConvert(String customUrl) {
        return customUrl;
    }

    public static ShortUrl shorten(InputUrl inputUrl) {
        ShortUrl shortUrl = new ShortUrl(inputUrl);
        if (inputUrl.getCustomUrl() != null) {
            shortUrl.setShortUrlKey(customConvert(inputUrl.getCustomUrl()));
        } else {
            shortUrl.setShortUrlKey(defaultConvert(inputUrl.getLongUrl()));
        }
        return shortUrl;
    }
}

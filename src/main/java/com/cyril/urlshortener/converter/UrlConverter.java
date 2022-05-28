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
        ShortUrl shortUrl = inputUrl.toShortUrl();
        if (inputUrl.getCustomUrl() != null) {
            shortUrl.setShortUrl(customConvert(inputUrl.getCustomUrl()));
        } else {
            shortUrl.setShortUrl(defaultConvert(inputUrl.getLongUrl()));
        }
        return shortUrl;
    }
}

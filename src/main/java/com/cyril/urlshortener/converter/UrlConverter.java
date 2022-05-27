package com.cyril.urlshortener.converter;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.utils.Constant;

public class UrlConverter {
    // todo
    public static String convert(String longUrl) {
        return longUrl.substring(0, 5);
    }

    public static String convert(String longUrl, String customUrl) {
        return String.format("%s/%s", Constant.PREFIX, customUrl);
    }

    public static ShortUrl shorten(InputUrl inputUrl) {
        ShortUrl shortUrl = inputUrl.toShortUrl();
        if (inputUrl.getCustomUrl() != null) {
            shortUrl.setShortUrl(convert(inputUrl.getLongUrl(), inputUrl.getCustomUrl()));
        } else {
            shortUrl.setShortUrl(convert(inputUrl.getLongUrl()));
        }
        return shortUrl;
    }
}

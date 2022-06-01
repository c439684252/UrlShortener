package com.cyril.urlshortener.converter;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.utils.UrlUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class UrlConverter {
    // todo
    public static String defaultConvert(String longUrl) throws MalformedURLException, URISyntaxException {
        int hashed = longUrl.hashCode();
        String sHashed = Integer.toString(Math.abs(hashed));
        if (sHashed.length() < 6) {
            return sHashed;
        } else {
            return sHashed.substring(0, 6);
        }
    }

    public static String customConvert(String customUrl) {
        return customUrl;
    }

    public static ShortUrl shorten(InputUrl inputUrl) throws MalformedURLException, URISyntaxException {
        ShortUrl shortUrl = new ShortUrl(inputUrl);
        String customUrl = inputUrl.getCustomUrl();
        if (!"".equals(customUrl)) {
            shortUrl.setShortUrlKey(customConvert(customUrl));
        } else {
            shortUrl.setShortUrlKey(defaultConvert(inputUrl.getLongUrl()));
        }
        return shortUrl;
    }
}

package com.cyril.urlshortener.mapper;

import com.cyril.urlshortener.bean.ShortUrl;

import java.util.List;

public interface ShortUrlMapper {
    int insert(ShortUrl shortUrl);

    List<String> selectByShortUrl(ShortUrl shortUrl);

    List<String> selectByString(String shortUrl);
}

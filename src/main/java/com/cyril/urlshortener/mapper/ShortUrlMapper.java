package com.cyril.urlshortener.mapper;

import com.cyril.urlshortener.bean.ShortUrl;

public interface ShortUrlMapper {
    int insert(ShortUrl shortUrl);
}

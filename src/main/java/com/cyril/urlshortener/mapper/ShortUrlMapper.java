package com.cyril.urlshortener.mapper;

import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.dao.UrlPair;

import java.sql.Date;
import java.util.List;

public interface ShortUrlMapper {
    int insert(ShortUrl shortUrl);

    List<String> selectByShortUrl(ShortUrl shortUrl);

    List<String> selectByString(String shortUrl);

    int deleteByDate(Date date);

    int deleteByUrlPair(String longUrl, String shortUrl);

    List<UrlPair> getTopN(Integer n);
}

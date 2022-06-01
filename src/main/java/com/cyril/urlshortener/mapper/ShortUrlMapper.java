package com.cyril.urlshortener.mapper;

import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.dao.UrlPair;

import java.util.Date;
import java.util.List;

public interface ShortUrlMapper {
    int insert(ShortUrl shortUrl);

    List<String> selectByString(String shortUrl);

    int deleteByDate(Date date);

    int deleteByExistPeriod(Integer days);

    /** Test only
     * */
    int deleteByExistPeriodTest(Integer days);

    List<UrlPair> getTopN(Integer n);

    List<UrlPair> selectAll();
}

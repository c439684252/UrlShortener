//package com.cyril.urlshortener.dao;
//
//import com.cyril.urlshortener.bean.ShortUrl;
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Data
//public class ShortUrlDao {
//    private static Map<Integer, ShortUrl> shortUrlMap = null;
//
//    static {
//        shortUrlMap = new HashMap<>();
//
//        ShortUrl shortUrl01 = new ShortUrl();
//        shortUrl01.setLongUrl("123456");
//        shortUrl01.setShortUrl("123");
//        shortUrl01.setCreateTimeStamp(1609L);
//        shortUrlMap.put(1, shortUrl01);
//
//        ShortUrl shortUrl02 = new ShortUrl();
//        shortUrl01.setLongUrl("7891011");
//        shortUrl01.setShortUrl("789");
//        shortUrl01.setCreateTimeStamp(1610L);
//        shortUrlMap.put(2, shortUrl02);
//    }
//}

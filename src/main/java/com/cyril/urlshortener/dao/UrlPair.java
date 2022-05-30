package com.cyril.urlshortener.dao;

import lombok.Data;

@Data
public class UrlPair {
    private Integer id;

    private String shortUrlKey;

    private String longUrl;

//    private String usageCnt;
}

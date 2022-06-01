package com.cyril.urlshortener.dao;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UrlPair {
    private Integer id;

    private String shortUrlKey;

    private String longUrl;

    private String createTime;

    private Integer accessCnt;

    private Timestamp lastAccessTs;
}

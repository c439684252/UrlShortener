package com.cyril.urlshortener.bean;

import lombok.Data;

@Data
public class InputUrl extends AbstractUrl {
    private String customUrl;

    public ShortUrl toShortUrl() {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(getLongUrl());
        shortUrl.setCreateTimeStamp(getCreateTimeStamp());
        return shortUrl;
    }
}

package com.cyril.urlshortener.bean;

import com.cyril.urlshortener.utils.Constant;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

public class ShortUrl extends AbstractUrl {

    public ShortUrl(){};

    public ShortUrl(InputUrl inputUrl) {
        this.setLongUrl(inputUrl.getLongUrl());
        this.setCreateTimeStamp(inputUrl.getCreateTimeStamp());
    }

    @Getter
    private String shortUrlKey;

    @Getter
    private String fullShortUrl;

    private Date createDate;

    public Date getCreateDate() {
        if (createDate != null) {
            return createDate;
        }

        Timestamp timestamp = new Timestamp(getCreateTimeStamp());
        createDate = new Date(timestamp.getTime());
        return createDate;
    }

    public void setShortUrlKey(String shortUrlKey) {
        this.shortUrlKey = shortUrlKey;
        this.fullShortUrl = transToFullShortUrl(shortUrlKey);
    }

    private String transToFullShortUrl(String shortUrlKey) {
        return String.format("%s/%s/%s", Constant.CURRENT_HOST, Constant.PREFIX, shortUrlKey);
    }
}
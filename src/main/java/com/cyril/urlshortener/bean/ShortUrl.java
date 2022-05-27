package com.cyril.urlshortener.bean;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class ShortUrl extends AbstractUrl {
    private String shortUrl;

    private Date createDate;

    public Date getCreateDate() {
        if (createDate != null) {
            return createDate;
        }

        Timestamp timestamp = new Timestamp(getCreateTimeStamp());
        createDate = new Date(timestamp.getTime());
        return createDate;
    }
}
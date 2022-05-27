package com.cyril.urlshortener.bean;

import lombok.Data;

@Data
public abstract class AbstractUrl {
    public String longUrl;

    public long createTimeStamp;
}

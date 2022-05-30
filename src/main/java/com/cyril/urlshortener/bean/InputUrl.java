package com.cyril.urlshortener.bean;

import lombok.Getter;
import lombok.Setter;

public class InputUrl extends AbstractUrl {

    @Getter
    @Setter
    private String customUrl;
}

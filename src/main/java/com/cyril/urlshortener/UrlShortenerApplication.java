package com.cyril.urlshortener;

import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.mapper.ShortUrlMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cyril.urlshortener.mapper")
public class UrlShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}

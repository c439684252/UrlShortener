package com.cyril.urlshortener.server;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.converter.UrlConverter;
import com.cyril.urlshortener.listener.ServerListener;
import com.cyril.urlshortener.mapper.ShortUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InputUrlServer {

    @Autowired
    ShortUrlMapper shortUrlMapper;

//    @PostConstruct
    public void startMonitor() {
        ServerListener serverListener = ServerListener.getInstance();
        serverListener.setInputUrlServer(this);
        serverListener.monitor();
    }

    private List<InputUrl> inputUrls = new ArrayList<>();

    public List<InputUrl> getInputUrls() {
        return inputUrls;
    }

    public void addNewUrl(InputUrl inputUrl) {
        System.out.println(String.format("Add new long URL: %s to server", inputUrl.getLongUrl()));
        inputUrls.add(inputUrl);
        // todo test
        insertToDB(UrlConverter.shorten(inputUrl));
    }

    private boolean insertToDB(ShortUrl shortUrl) {
        shortUrlMapper.insert(shortUrl);
        System.out.println(String.format("Insert shorten URL to Database..."));
        return true;
    }
}

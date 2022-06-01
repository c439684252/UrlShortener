package com.cyril.urlshortener.server;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.converter.UrlConverter;
import com.cyril.urlshortener.listener.ServerListener;
import com.cyril.urlshortener.mapper.ShortUrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class InputUrlServer {
    private static final Logger LOG = LoggerFactory.getLogger(InputUrlServer.class);

    @Resource
    ShortUrlMapper shortUrlMapper;

//    @PostConstruct
    public void startMonitor() {
        ServerListener serverListener = ServerListener.getInstance();
        serverListener.setInputUrlServer(this);
        serverListener.monitor();
    }

    // todo new added urls put into cache by default?
    public String process(InputUrl inputUrl) {
        try {
            ShortUrl shorten = UrlConverter.shorten(inputUrl);
            insertToDB(shorten);
            return shorten.getFullShortUrl();
        } catch (Exception e) {
            LOG.error("Error in InputUrlServer", e);
            return "";
        }
    }

    // todo remove log
    private void insertToDB(ShortUrl shortUrl) {
        shortUrlMapper.insert(shortUrl);
        LOG.debug("Insert {} shortened URL to Database...", shortUrl);
    }
}

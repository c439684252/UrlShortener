package com.cyril.urlshortener.listener;

import com.cyril.urlshortener.server.InputUrlServer;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class ServerListener extends TimerTask {

    private static final Logger LOG = LoggerFactory.getLogger(ServerListener.class);

    @Setter
    private InputUrlServer inputUrlServer;

    private ServerListener(InputUrlServer inputUrlServer) {
        this.inputUrlServer = inputUrlServer;
    }

    private final static ServerListener INSTANCE = new ServerListener();

    private final static Timer TIMER = new Timer();

    public static ServerListener getInstance() {
        return INSTANCE;
    }

    private ServerListener(){};

    @Override
    public void run() {
        LOG.debug("At {} data in ServerMap", System.currentTimeMillis());
//        List<InputUrl> inputUrls = inputUrlServer.getInputUrls();
//        for (InputUrl i : inputUrls) {
//            LOG.debug(i);
//        }
    }

    public void monitor() {
        TIMER.schedule(this, 0, 5000);
    }
}

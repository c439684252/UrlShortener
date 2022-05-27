package com.cyril.urlshortener.listener;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.server.InputUrlServer;
import lombok.Setter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ServerListener extends TimerTask {

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
        System.out.println(String.format("At %s data in ServerMap", System.currentTimeMillis()));
        List<InputUrl> inputUrls = inputUrlServer.getInputUrls();
        for (InputUrl i : inputUrls) {
            System.out.println(i);
        }
    }

    public void monitor() {
        TIMER.schedule(this, 0, 5000);
    }
}

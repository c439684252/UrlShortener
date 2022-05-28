package com.cyril.urlshortener.controller;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.server.InputUrlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InputController {

    private final InputUrlServer inputUrlServer;

    @Autowired
    public InputController(InputUrlServer inputUrlServer) {
        this.inputUrlServer = inputUrlServer;
    }

    @PostMapping("/input")
    @ResponseBody
    public InputUrl input(@RequestParam String longUrl, @RequestParam(required = false) String custom) {
        InputUrl inputUrl = new InputUrl();
        inputUrl.setLongUrl(longUrl);
        inputUrl.setCreateTimeStamp(System.currentTimeMillis());
        inputUrl.setCustomUrl(custom);
        inputUrlServer.addNewUrl(inputUrl);

        return inputUrl;
    }
}

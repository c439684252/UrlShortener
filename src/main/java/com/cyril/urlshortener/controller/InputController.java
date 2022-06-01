package com.cyril.urlshortener.controller;

import com.cyril.urlshortener.bean.InputUrl;
import com.cyril.urlshortener.server.InputUrlServer;
import com.cyril.urlshortener.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InputController {


    private final InputUrlServer inputUrlServer;
    private static final String STORE_PAGE = "store";

    @Autowired
    public InputController(InputUrlServer inputUrlServer) {
        this.inputUrlServer = inputUrlServer;
    }

    @PostMapping("/input")
    public String input(@RequestParam String longUrl, @RequestParam(required = false) String custom, Model model) {
        InputUrl inputUrl = new InputUrl();
        inputUrl.setLongUrl(longUrl);
        inputUrl.setCreateTimeStamp(System.currentTimeMillis());
        inputUrl.setCustomUrl(UrlUtil.urlPathCheck(custom));

        String shortUrl =  inputUrlServer.process(inputUrl);
        model.addAttribute("short_url", shortUrl);
        return STORE_PAGE;
    }


}

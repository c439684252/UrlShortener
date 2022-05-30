package com.cyril.urlshortener.controller;

import com.cyril.urlshortener.server.RedirectUrlServer;
import com.cyril.urlshortener.utils.UrlHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @Resource
    private RedirectUrlServer redirectUrlServer;

    // resources/templates/oops.html
    private final static String ERROR_PAGE = "oops";

    // resources/templates/multiple.html
    private final static String MULTIPLE_PAGE = "multiple";

    @GetMapping("/{tiny}")
    public String redirect(@PathVariable String tiny, Model model){
        try {
            List<String> longUrls = redirectUrlServer.search(tiny);
            if (longUrls.isEmpty()) {
                return ERROR_PAGE;
            } else if (longUrls.size() > 1) {
                model.addAttribute("urls", longUrls.toString());
                return MULTIPLE_PAGE;
            } else {
                String validLongUrl = UrlHelper.checkAndFix(longUrls.get(0));
                return String.format("redirect:%s", validLongUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_PAGE;
        }
    }
}
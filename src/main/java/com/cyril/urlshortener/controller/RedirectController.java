package com.cyril.urlshortener.controller;

import com.cyril.urlshortener.mapper.ShortUrlMapper;
import com.cyril.urlshortener.utils.UrlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/redirect")
public class RedirectController {
    private final ShortUrlMapper shortUrlMapper;

    @Autowired
    public RedirectController(ShortUrlMapper shortUrlMapper) {
        this.shortUrlMapper = shortUrlMapper;
    }

    @GetMapping("/{tiny}")
    public void redirect(HttpServletResponse response, @PathVariable String tiny){
        try {
            List<String> longUrls = shortUrlMapper.selectByString(tiny);
            if (longUrls.isEmpty()) {
                System.out.println("404, not found");
            } else if (longUrls.size() > 1) {
                System.out.println("undetermined destination");
            } else {
                String validLongUrl = UrlHelper.checkAndFix(longUrls.get(0));
                response.sendRedirect(validLongUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
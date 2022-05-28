package com.cyril.urlshortener.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlHelper {
    private enum ErrorCode {
        NO_ERROR,
        SCHEME_ERROR,
        HOST_ERROR,
        OTHER_ERROR,
    }

    public static ErrorCode isValid(String input) {
        try {
            URL url = new URL(input);
            URI uri = url.toURI();
            if (uri.getHost() == null) {
                return ErrorCode.HOST_ERROR;
            }
            return ErrorCode.NO_ERROR;
        } catch (URISyntaxException ignored) {
            return ErrorCode.OTHER_ERROR;
        } catch (MalformedURLException e) {
            return ErrorCode.SCHEME_ERROR;
        }
    }

    public static String checkAndFix(String input) {
        ErrorCode valid = isValid(input);
        switch (valid) {
            case NO_ERROR:
                return input;
            case SCHEME_ERROR:
                return String.format("https://%s", input);
            case HOST_ERROR:
            case OTHER_ERROR:
                // todo
                return input;
        }
        return input;
    }
}

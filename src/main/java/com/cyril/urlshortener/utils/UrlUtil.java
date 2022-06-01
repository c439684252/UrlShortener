package com.cyril.urlshortener.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlUtil {
    public enum ErrorCode {
        NO_ERROR,
        SCHEME_ERROR,
        HOST_ERROR,
        OTHER_ERROR,
    }

    /** Check format of URLs
     *
     * @param input URLs string needed to be checked
     * @return ErrorCode of URLs
     * @see ErrorCode
     * */
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

    /** Check format of URL and fix illegal formats
     *
     * @param input URLs string needed to be checked
     * @return legal URL formats
     * */
    public static String checkAndFix(String input) {
        ErrorCode valid = isValid(input);
        switch (valid) {
            case NO_ERROR:
                return input;
            case SCHEME_ERROR:
                return String.format("%s://%s", Constant.DEFAULT_SCHEME, input);
            // todo
            case HOST_ERROR:
            case OTHER_ERROR:
                return input;
        }
        return input;
    }

    /** Use to check the legality of Url Path
     *
     * @param path customised url path
     * @return Type String, get rid of whitespaces and all special characters in path
     * */
    public static String urlPathCheck(String path) {
        return path.trim().replaceAll("[\\W]", "");
    }

}

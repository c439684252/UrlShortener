package com.cyril.urlshortener.utlTest;

import com.cyril.urlshortener.utils.UrlUtil;
import org.junit.Assert;
import org.junit.Test;

public class UrlUtilTest {
    @Test
    public void testIsValid() {
        String noError = "https://www.google.com";
        UrlUtil.ErrorCode rlt = UrlUtil.isValid(noError);
        Assert.assertEquals(UrlUtil.ErrorCode.NO_ERROR, rlt);

        String schemeError = "www.google.com";
        rlt = UrlUtil.isValid(schemeError);
        Assert.assertEquals(UrlUtil.ErrorCode.SCHEME_ERROR, rlt);

        String hostError = "https://?User=a";
        rlt = UrlUtil.isValid(hostError);
        Assert.assertEquals(UrlUtil.ErrorCode.HOST_ERROR, rlt);
    }

    @Test
    public void testCheckAndFix() {
        String schemeError = "www.google.com";
        String  fixed = UrlUtil.checkAndFix(schemeError);
        Assert.assertEquals("https://www.google.com", fixed);
    }


    @Test
    public void testUrlPathCheck() {
        String path = "abc%f112^&*";
        String rlt = UrlUtil.urlPathCheck(path);
        Assert.assertEquals("abcf112", rlt);
    }

}

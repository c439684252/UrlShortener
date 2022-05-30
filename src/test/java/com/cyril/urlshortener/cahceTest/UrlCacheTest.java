package com.cyril.urlshortener.cahceTest;

import com.cyril.urlshortener.cache.UrlCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UrlCacheTest {
    @Resource
    private UrlCache urlCache;

    @Test
    public void testCache() {
        urlCache.run();
        Map<String, List<String>> cache = urlCache.getCache();
        List<String> short001 = cache.get("short001");
        List<String> short002 = cache.get("short002");
        List<String> short003 = cache.get("short003");
        List<String> short005 = cache.get("short005");
        Assert.assertEquals(1, short001.size());
        Assert.assertEquals(1, short002.size());
        Assert.assertEquals(2, short003.size());
        Assert.assertEquals(1, short005.size());

        Assert.assertEquals("dummyurl0001", short001.get(0));
        Assert.assertEquals("dummyurl0002", short002.get(0));
        Assert.assertEquals("dummyurl0003", short003.get(0));
        Assert.assertEquals("dummyurl0004", short003.get(1));
        Assert.assertEquals("dummyurl0005", short005.get(0));
    }
}

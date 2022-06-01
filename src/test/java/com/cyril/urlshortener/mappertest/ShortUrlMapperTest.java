package com.cyril.urlshortener.mappertest;

import com.cyril.urlshortener.bean.ShortUrl;
import com.cyril.urlshortener.dao.UrlPair;
import com.cyril.urlshortener.mapper.ShortUrlMapper;
import com.cyril.urlshortener.utils.GeneralUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
public class ShortUrlMapperTest {

    @Resource
    ShortUrlMapper shortUrlMapper;

    @Test
    public void testSelectByShortUrl() {
        List<String> longUrls = shortUrlMapper.selectByString("short002");
        Assert.assertEquals(1, longUrls.size());
        Assert.assertEquals("dummyurl0002", longUrls.get(0));
    }

    @Test
    public void testInsert() {
        long ts = System.currentTimeMillis();
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl("longTest");
        shortUrl.setShortUrlKey("shortTest");
        shortUrl.setCreateTimeStamp(ts);
        shortUrlMapper.insert(shortUrl);
        Assert.assertEquals("longTest", shortUrlMapper.selectByString("shortTest").get(0));
    }

    @Test
    public void testTopN() {
        List<UrlPair> topN = shortUrlMapper.getTopN(2);
        Assert.assertEquals(2, topN.size());
        Assert.assertEquals("dummyurl0001", topN.get(0).getLongUrl());
        Assert.assertEquals("dummyurl0002", topN.get(1).getLongUrl());
    }

    @Test
    public void testDeleteByDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.MAY, 15);
        Date time = calendar.getTime();
        shortUrlMapper.deleteByDate(time);
        List<UrlPair> urlPairs = shortUrlMapper.selectAll();
        Assert.assertEquals(4 , urlPairs.size());
    }

    @Test
    public void testDeleteByExistPeriod() {
        int expirePeriodDay = 9;
        shortUrlMapper.deleteByExistPeriodTest(expirePeriodDay);
        List<UrlPair> urlPairs = shortUrlMapper.selectAll();
        Assert.assertEquals(5, urlPairs.size());
    }

}

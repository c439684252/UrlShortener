package com.cyril.urlshortener.listener;

import com.cyril.urlshortener.mapper.ShortUrlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DBCleaner implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DBCleaner.class);

    public static final int CREATE_ACCESS_INTERVAL_IN_DAY = 9;

    public static final double CLEAN_PROCESS_INTERVAL_IN_MINUTE = 0.1;

    public static final long CLEAN_PROCESS_INTERVAL_IN_SECOND = (long) (CLEAN_PROCESS_INTERVAL_IN_MINUTE * 60);

    @Resource
    private ShortUrlMapper shortUrlMapper;

    @Override
    public void run() {
        LOG.info("Delete {} rows in DB", deleteByExistPeriod(CREATE_ACCESS_INTERVAL_IN_DAY));
    }

    private int deleteByExistPeriod(int days) {
        return shortUrlMapper.deleteByExistPeriod(days);
    }
}

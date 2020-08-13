/*
 * File Name:MyElasticJobListener is created on 2020/7/23 10:59 by eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.eric.elasticjob.listener;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

import ch.qos.logback.core.util.TimeUtil;

/**
 * @author eric
 * @Description:
 * @date: 2020/7/23 10:59
 * @since JDK 1.8
 */
public class MyElasticJobListener implements ElasticJobListener {

    private static final Logger logger = LoggerFactory.getLogger(MyElasticJobListener.class);

    private Long beginTime = 0L;

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        beginTime = System.currentTimeMillis();
        logger.info("===>{} JOB BEGIN TIME: {} <===", shardingContexts.getJobName(),
            DateFormatUtils.format(beginTime, "yyyy-mm-dd HH:mm:ss"));
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        logger.info("===>{} JOB END TIME: {},TOTAL CAST: {} <===", shardingContexts.getJobName(),
            DateFormatUtils.format(beginTime, "yyyy-mm-dd HH:mm:ss"), endTime - beginTime);

    }
}

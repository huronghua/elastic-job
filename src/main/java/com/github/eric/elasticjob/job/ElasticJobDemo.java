/*
 * File Name:ElasticJobListener is created on 2020/7/23 10:53 by eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.eric.elasticjob.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author eric
 * @Description:
 * @date: 2020/7/23 10:53
 * @since JDK 1.8
 */
@Component
public class ElasticJobDemo implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("---- Thread ID: %s,任务总片数: %s," + "当前分片項: %s.当前參數: %s," +
                "当前任務名稱: %s.当前任務參數: %s"
            ,
            Thread.currentThread().getId(),
            shardingContext.getShardingTotalCount(),
            shardingContext.getShardingItem(),
            shardingContext.getShardingParameter(),
            shardingContext.getJobName(),
            shardingContext.getJobParameter()));
    }
}

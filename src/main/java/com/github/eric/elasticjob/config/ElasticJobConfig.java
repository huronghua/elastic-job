/*
 * File Name:ElasticJobConfig is created on 2020/7/23 11:18 by eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.eric.elasticjob.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.github.eric.elasticjob.job.ElasticJobDemo;
import com.github.eric.elasticjob.listener.MyElasticJobListener;

/**
 * @author eric
 * @Description:
 * @date: 2020/7/23 11:18
 * @since JDK 1.8
 */
@Configuration
public class ElasticJobConfig {

    @Autowired
    private ZookeeperRegistryCenter registryCenter;


    @Bean
    public ElasticJobListener elasticJobListener(){
        return new MyElasticJobListener();
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final ElasticJobDemo simpleJob,
        @Value("${stockJob.cron}") final String cron,
        @Value("${stockJob.shardingTotalCount}") final int shardingTotalCount,
        @Value("${stockJob.shardingItemParameters}") final String shardingItemParameters) {
        MyElasticJobListener elasticJobListener = new MyElasticJobListener();
        return new SpringJobScheduler(simpleJob, registryCenter,
            getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters),
            elasticJobListener);
    }

    /**
     * 配置任务详细信息
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
        final String cron,
        final int shardingTotalCount,
        final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(
            JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters).build()
            , jobClass.getCanonicalName())
        ).overwrite(true).build();
    }
}

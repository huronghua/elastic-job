/*
 * File Name:ZookepperConfigCenter is created on 2020/7/23 10:43 by eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.eric.elasticjob.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

/**
 * @author eric
 * @Description:
 * @date: 2020/7/23 10:43
 * @since JDK 1.8
 */
@Configuration
public class ZookepperConfigCenter {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter registryCenter(@Value("${registry.center.serverlist}") final String serverList,
        @Value("${registry.center.namespace}") final String nameSpace){
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, nameSpace));
    }

}

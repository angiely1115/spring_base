package com.example.spring.spring_base.demo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.rongly.framework.autoconfiguer.elasticJob.ElasticJobConf;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/26 20:12
 * @Version: 1.0
 * modified by:
 */
@ElasticJobConf(cron="0/5 * * * * ? *")
public class MyJobDemo implements SimpleJob{
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s.当前参数: %s,"+
                        "当前任务名称: %s.当前任务参数: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()

        ));
    }
}

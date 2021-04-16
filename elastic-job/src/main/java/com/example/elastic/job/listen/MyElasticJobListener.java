package com.example.elastic.job.listen;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.example.elastic.job.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyElasticJobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        long beginTime = System.currentTimeMillis();
        log.info(">>> {} job begin time: {} <<<",shardingContexts.getJobName(), TimeUtil.mill2Time(beginTime));
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        long endTime = System.currentTimeMillis();
        log.info(">>> {} job begin time: {} <<<",shardingContexts.getJobName(), TimeUtil.mill2Time(endTime));
    }
}

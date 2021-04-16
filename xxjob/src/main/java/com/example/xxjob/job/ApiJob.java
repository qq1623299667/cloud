package com.example.xxjob.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 基于api任务
 * @author Jia Shi
 * @since 2020/12/4
 */
@Slf4j
@Component
public class ApiJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {

        XxlJobLogger.log("XXL-JOB-API, Hello World.");

        log.info("my job api run, param: {}",param);

        return ReturnT.SUCCESS;
    }

    @PostConstruct
    public void registerHandler(){
        XxlJobExecutor.registJobHandler("myJobApiHandler",new ApiJob());
    }
}

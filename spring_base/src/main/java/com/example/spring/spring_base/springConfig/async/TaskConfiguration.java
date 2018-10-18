package com.example.spring.spring_base.springConfig.async;

import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author peng
 * @version 1.0.0
 * @ClassName ThreadTaskConfiguration.java
 * @Description 线程队列工具
 * @createTime 2018年09月21日 14:12:00
 */
@Slf4j
@Configuration
@EnableAsync
public class TaskConfiguration {

  @Bean(name = "taskService")
  public ThreadPoolTaskExecutor taskService(){
    log.info("start asyncServiceExecutor");
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    //配置核心线程数
    executor.setCorePoolSize(5);
    //配置最大线程数
    executor.setMaxPoolSize(5);
    //配置队列大小
    executor.setQueueCapacity(99999);
    //配置线程池中的线程的名称前缀
    executor.setThreadNamePrefix("async-service-");
    // rejection-policy：当pool已经达到max size的时候，如何处理新任务
    // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    //执行初始化
    executor.initialize();
    return executor;
  }

}
package com.example.spring.spring_base.springConfig.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description: 事件监听器 基于注解
 * @Date: 2018/9/13 9:32
 * @Version: 1.0
 * modified by:
 */
@Component
@Slf4j
public class MyListener {
    /**
     * 监听器处理事件
     *
     * @param myEvent
     */
    @EventListener
    //异步处理
    @Async
    public void processMyEvent(MyEvent myEvent) {
        log.info("开始处理myenvent事件");
        Object o = myEvent.getObject();
        log.info("MyEvent-->object:" + o);
        log.info("MyEvent-->name:" + myEvent.getName());
    }
}

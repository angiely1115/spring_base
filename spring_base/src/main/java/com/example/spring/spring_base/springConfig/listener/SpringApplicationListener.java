package com.example.spring.spring_base.springConfig.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: lvrongzhuan
 * @Description: spring事件监听 监听所有的事件
 * @Date: 2019/2/23 14:11
 * @Version: 1.0
 * modified by:
 */
public class SpringApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("当前事件是："+event);
    }
}

package com.example.spring.spring_base.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/20 21:31
 * @Version: 1.0
 * modified by:
 */
public class MyApplicationAware implements ApplicationContextAware{
    private static ApplicationContext application;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        application = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return application;
    }
}

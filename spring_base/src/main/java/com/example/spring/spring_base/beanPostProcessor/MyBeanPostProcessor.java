package com.example.spring.spring_base.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/8/13 20:23
 * @Version: 1.0
 * modified by:
 */
public class MyBeanPostProcessor implements BeanPostProcessor{
    /**
     * 在bean初始化之前执行 也是在afterSetproreties() 方法之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor，对象" + beanName + "调用初始化方法之前的数据： " + bean.toString());
        return bean;
    }

    /**
     * 在bean初始化之后执行 同时也在afterSetProperties方法之后和init-moth方法执行之后执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor，对象" + beanName + "调用初始化方法之后的数据：" + bean.toString());
        return bean;
    }
}

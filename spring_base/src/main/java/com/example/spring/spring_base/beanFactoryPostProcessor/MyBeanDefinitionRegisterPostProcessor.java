package com.example.spring.spring_base.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @Author: lvrongzhuan
 * @Description: bean定义注册拓展处理 在BeanFactoryPostProcessor 之前执行
 * @Date: 2019/2/23 13:54
 * @Version: 1.0
 * modified by:
 */
public class MyBeanDefinitionRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("是bean将要被加载的时候执行的，可以向容器注入bean");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("bean 已经被加载完成执行的 也可以去注册bean和销毁bean等功能");

    }
}

package com.example.spring.spring_base.beanFactoryPostProcessor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/16 17:09
 * @Version: 1.0
 * modified by:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    //不会执行,如果是用import注解就会执行而且 最先执行
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //BeanDefinition beanDefinition = registry.getBeanDefinition("com.example.spring.spring_base.beanFactoryPostProcessor.MyBeanDefinition");
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(MyBeanDefinition.class);
        registry.registerBeanDefinition("myBeanDefinitian",rootBeanDefinition);
        System.out.println("MyImportBeanDefinitionRegistrar>>>>****执行");
    }
}

package com.example.spring.spring_base.springConfig;

import com.example.spring.spring_base.beanFactoryPostProcessor.*;
import com.example.spring.spring_base.beanPostProcessor.MyBeanPostProcessor;
import org.springframework.context.annotation.*;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/8/13 19:50
 * @Version: 1.0
 * modified by:
 */
@Configuration
@Import({MyImportBeanDefinitionRegistrar.class,MyBeanFactoryPostProcessor.class, MyInstantiationAwareBeanPostProcessor.class})
public class PostProcessorConfig {
    @Bean(initMethod="initMethod")
    @Lazy //在getBean的执行
    //如果不是单例 在getBean的执行 获取一个就会执行 每次获取都会走bean生命周期流程 单例bean只会走一次生命周期流程
   @Scope(value = "prototype")
    public MyJavaBean myJavaBean(){
        return new MyJavaBean("白素贞","白娘子");
    }

//    @Bean
//    public MyBeanFactoryPostProcessor myBeanFactoryPostProcessor(){
//        return  new MyBeanFactoryPostProcessor();
//    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }

    @Bean
    public MyApplicationAware myApplicationAware(){
        return new MyApplicationAware();
    }
}

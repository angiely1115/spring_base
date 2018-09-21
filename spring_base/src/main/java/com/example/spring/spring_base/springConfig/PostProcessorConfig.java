package com.example.spring.spring_base.springConfig;

import com.example.spring.spring_base.beanFactoryPostProcessor.MyBeanFactoryPostProcessor;
import com.example.spring.spring_base.beanFactoryPostProcessor.MyImportBeanDefinitionRegistrar;
import com.example.spring.spring_base.beanFactoryPostProcessor.MyJavaBean;
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
@Import(MyImportBeanDefinitionRegistrar.class)
public class PostProcessorConfig {
    @Bean(initMethod="initMethod")
//    @Lazy //在getBean的执行
    @Scope(value = "prototype")//如果不是单例 在getBean的执行 获取一个就会执行
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
}

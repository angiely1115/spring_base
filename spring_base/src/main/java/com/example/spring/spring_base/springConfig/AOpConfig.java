package com.example.spring.spring_base.springConfig;

import com.example.spring.spring_base.beanPostProcessor.MyInstantiationAwareBeanPortProcessor;
import com.example.spring.spring_base.demo.aop.AOPAspect;
import com.example.spring.spring_base.demo.aop.AOPAspect2;
import com.example.spring.spring_base.demo.aop.AOPDemo;
import com.example.spring.spring_base.demo.aop.TransactionAopDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/16 16:42
 * @Version: 1.0
 * modified by:
 */
@Configuration
//开启aop编程 在用springboot注解启动可以不用加 但是单独使用要加
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import(MyInstantiationAwareBeanPortProcessor.class)
public class AOpConfig {
    @Bean
    public AOPDemo aopDemo(){
        return new AOPDemo();
    }
    @Bean
    public AOPAspect aopAspect(){
        return new AOPAspect();
    }

    @Bean
    public AOPAspect2 aopAspect2(){
        return new AOPAspect2();
    }

    @Bean
    public TransactionAopDemo transactionAopDemo(){
        return new TransactionAopDemo();
    }

}

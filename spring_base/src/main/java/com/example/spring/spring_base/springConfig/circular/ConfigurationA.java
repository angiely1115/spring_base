package com.example.spring.spring_base.springConfig.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * https://mp.weixin.qq.com/s/Vg6ZOYEDRDG6DwZzsikZxg
 * 循环依赖 springIOC对依赖自动调解
 */
public class ConfigurationA {
    @Autowired
    public BeanB beanB;

    @Autowired
    private BeanA beanA;

    /*@Bean
    public BeanA beanA() {
        return new BeanA();
    }*/
}
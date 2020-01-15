package com.example.spring.spring_base.springConfig.circular;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceA {
    @Autowired
    private BeanA beanA;

    @Autowired
    private BeanB beanB;
}
package com.example.spring.spring_base.springConfig.circular;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanB {
    @Autowired
    public BeanA beanA;
}
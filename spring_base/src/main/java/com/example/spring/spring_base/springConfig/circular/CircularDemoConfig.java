package com.example.spring.spring_base.springConfig.circular;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: lvrongzhuan
 * @Description: 循环依赖demo配置类
 * @Date: 2020/1/14 22:01
 * @Version: 1.0
 * modified by:
 */
@Configuration
@Import(value = {ServiceA.class,ConfigurationA.class,BeanB.class,BeanA.class})
public class CircularDemoConfig {
}

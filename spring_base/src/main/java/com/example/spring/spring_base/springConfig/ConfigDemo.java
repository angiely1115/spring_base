package com.example.spring.spring_base.springConfig;

import com.example.spring.spring_base.demo.pojo.PersonPojo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: springboot 属性配置
 * @Date: 2018/8/27 16:57
 * @Version: 1.0
 * modified by:
 */
@ConfigurationProperties(prefix = "lv.config.demo")
@Data
@PropertySource("classpath:application.yml")
@Configuration
public class ConfigDemo {
    private String name;
    private int age;
    private Object[] objects;
    private Map<String,Object> objectMap;
    private PersonPojo personPojo;
}

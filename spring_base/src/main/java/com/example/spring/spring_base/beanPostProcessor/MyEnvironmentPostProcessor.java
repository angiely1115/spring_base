package com.example.spring.spring_base.beanPostProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: 动态扩展属性配置
 * @Date: 2018/11/26 16:42
 * @Version: 1.0
 * modified by:
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("SystemProperties:"+environment.getSystemProperties());
        System.out.println("SystemEnvironment:"+environment.getSystemEnvironment());
        Map<String,Object> map = new HashMap<>();
        map.put("lv.config.demo.env1",222);
        map.put("lv.config.demo.env2","zyz");
        environment.getPropertySources().addLast(new MapPropertySource("ronglySource",map));
    }
}

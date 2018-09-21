package com.example.spring.spring_base.springConfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/18 9:54
 * @Version: 1.0
 * modified by:
 */
@Configuration
@Data
@PropertySource("classpath:application.properties")//导入外部配置文件 但是不能导入yml文件
@Import(ConfigDemo.class)
public class PropertiesConfig {
    @Value("${web_url}")
    private String web_url;
}

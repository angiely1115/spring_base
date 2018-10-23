package com.example.spring.spring_base.springConfig.jdbc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/22 16:45
 * @Version: 1.0
 * modified by:
 */
@Data
public class MultiMysqlProperties {

    private Map<String,MysqlProperties> rongMysql;
}

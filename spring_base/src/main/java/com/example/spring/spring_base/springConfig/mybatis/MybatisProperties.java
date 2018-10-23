package com.example.spring.spring_base.springConfig.mybatis;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: mybatis属性配置文件
 * @Date: 2018/10/22 21:06
 * @Version: 1.0
 * modified by:
 */
@Data
public class MybatisProperties {
    private Map<String,MysqlMybatisConfig> rongMySql;
}

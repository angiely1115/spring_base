package com.example.spring.spring_base.springConfig.jdbc;

import lombok.Data;

/**
 * @Author: lvrongzhuan
 * @Description:mysql 数据源属性文件
 * @Date: 2018/10/22 16:46
 * @Version: 1.0
 * modified by:
 */
@Data
public class MysqlProperties {
    private String username;
    private String password;
    private String url;
    private JdbcPoolConfig pool = new JdbcPoolConfig();
}

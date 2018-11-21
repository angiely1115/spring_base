package com.example.spring.spring_base.mybatis;

import com.example.spring.spring_base.springConfig.jdbc.MysqlDataSourceAutoConfiguration;
import com.example.spring.spring_base.springConfig.mybatis.MybatisAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/23 19:31
 * @Version: 1.0
 * modified by:
 */
public class MybatisTest {
    @Test
    public void mybatisTest1(){
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MybatisAutoConfiguration.class, MysqlDataSourceAutoConfiguration.class);
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) configApplicationContext.getBean("readNodeSf");
        System.out.println(sqlSessionFactory);

    }
}

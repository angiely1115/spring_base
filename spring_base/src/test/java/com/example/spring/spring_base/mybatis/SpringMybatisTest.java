package com.example.spring.spring_base.mybatis;

import com.example.spring.spring_base.demo.dao.read.RolesDao;
import com.example.spring.spring_base.demo.dao.read.UserDao;
import com.example.spring.spring_base.demo.dao.batch.write.UserBatchDao;
import com.example.spring.spring_base.demo.entity.UserEntity;
import com.example.spring.spring_base.springConfig.jdbc.MysqlDataSourceAutoConfiguration;
import com.example.spring.spring_base.springConfig.mybatis.MybatisAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/23 14:52
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={MybatisAutoConfiguration.class, MysqlDataSourceAutoConfiguration.class})
public class SpringMybatisTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDao userDao2;
    @Autowired
    private RolesDao rolesDao;
    @Autowired(required = false)
    private UserBatchDao userBatchDao;
    @Test
    public void muliParamsTest(){
        System.out.println(userDao.selectUserByNameAndAge("a",30));
        System.out.println(userDao.queryUserAccount(1L));
        System.out.println("******************queryUserRoles*************");
        System.out.println(userDao.queryUserRoles(1L));
        System.out.println(userDao.queryUserRoles(1L));
        System.out.println("******************queryUserRoles end*************");
        //没有使用一级缓存
        System.out.println(userDao.queryUserByLikeName("aaa"));
        System.out.println(userDao.queryUserByLikeName("aaa"));
    }

    @Test
    public void paramsTest02(){
        System.out.println("执行前**********");
        userDao.queryUserRoles(1L);
        System.out.println("第二次执行前**********");
        userDao.queryUserRoles(2L);
        System.out.println("第三次执行前>>>>>");
        userDao.queryUserAccount(1L);
    }

    @Test
    public void paramsTest03(){
        //没有调用也会创建dao的代理对象
        System.out.println("不执行执行前**********");
       userDao2.queryUserRoles(1L);
    }
    @Test
    public void batchInsert(){
        UserEntity userEntity = null;
        for(int i=0;i<10000;i++){
            userEntity = new UserEntity();
            userEntity.setName("hnb"+i);
            userEntity.setAge(ThreadLocalRandom.current().nextInt(200));
            userBatchDao.batchInsertUser(userEntity);
        }
    }
}

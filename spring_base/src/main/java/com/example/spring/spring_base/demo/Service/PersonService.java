package com.example.spring.spring_base.demo.Service;

import com.example.spring.spring_base.demo.dao.read.UserDao;
import com.example.spring.spring_base.demo.entity.UserEntity;
import com.example.spring.spring_base.springConfig.mybatis.MybatisPageValue;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/8 15:57
 * @Version: 1.0
 * modified by:
 */
@Service
public class PersonService {
    @Autowired
    private UserDao userDao;
    /**
     *加上只读事务后 可以使用mybatis一级缓存
     */
    @Transactional(value = "readNodeTx",readOnly=true)
    public UserEntity querUserById(Long id){
        userDao.selectUserById(id);
        return userDao.selectUserById(id);
    }
    @Transactional(value = "readNodeTx",readOnly=true)
    public MybatisPageValue<UserEntity> queryUserPage(int pageNum, int pageSize){
        this.querUserById(1L);
        this.oneCache(1L);
        PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> userEntities = userDao.queryUserPage();
        return new MybatisPageValue(userEntities);
    }

    public void oneCache(long id){
        this.querUserById(id);
    }
}

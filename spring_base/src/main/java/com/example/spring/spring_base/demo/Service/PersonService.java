package com.example.spring.spring_base.demo.Service;

import com.example.spring.spring_base.demo.dao.read.UserDao;
import com.example.spring.spring_base.demo.entity.UserEntity;
import com.example.spring.spring_base.springConfig.mybatis.MybatisPageValue;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserEntity querUserById(Long id){
        return userDao.selectUserById(id);
    }

    public MybatisPageValue<UserEntity> queryUserPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> userEntities = userDao.queryUserPage();
        return new MybatisPageValue(userEntities);
    }
}

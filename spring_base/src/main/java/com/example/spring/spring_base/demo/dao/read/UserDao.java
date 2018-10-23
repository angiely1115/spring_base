package com.example.spring.spring_base.demo.dao.read;

import com.example.spring.spring_base.demo.entity.UserEntity;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/23 10:22
 * @Version: 1.0
 * modified by:
 */
@Repository
public interface UserDao {
     UserEntity selectUserById(long id);

     List<UserEntity> queryUserPage();
}

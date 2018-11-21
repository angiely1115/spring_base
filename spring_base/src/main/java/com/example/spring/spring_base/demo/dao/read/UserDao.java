package com.example.spring.spring_base.demo.dao.read;

import com.example.spring.spring_base.demo.entity.UserEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
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

     /**
      * 用了@Param就不需要在标签上显示使用参数类型指定
      * 如果不使用@Param注解会报错 找不到参数
      * @param name
      * @param age
      * @return
      */
     List<UserEntity> selectUserByNameAndAge(@Param("name") String name, @Param("age") int age);

     List<UserEntity> queryUserByLikeName(@Param("name") String name);
     UserEntity queryUserAccount(Long userId);

     UserEntity queryUserRoles(Long userId);
}

package com.example.spring.spring_base.demo.controller;

import com.example.spring.spring_base.demo.Service.PersonService;
import com.example.spring.spring_base.demo.entity.UserEntity;
import com.example.spring.spring_base.springConfig.mybatis.MybatisPageValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/8 15:57
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("user")
public class PersonController {

    @Autowired
    private PersonService personService;
    @GetMapping("query/user/{id}")
    public UserEntity queryUserEntity(@PathVariable("id") Long id){
        return personService.querUserById(id);
    }

    @GetMapping("query/page/{pageNum}/{pageSize}")
    public MybatisPageValue<UserEntity> queryPage(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        return personService.queryUserPage(pageNum,pageSize);
    }
}

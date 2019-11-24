package com.example.spring.spring_base.demo.controller;

import com.example.spring.spring_base.demo.Service.PersonService;
import com.example.spring.spring_base.demo.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("person")
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger("mm.hh");
    @Autowired
    private PersonService personService;
    @GetMapping("query/user/{id}")
    public UserEntity queryUserEntity(@PathVariable("id") Long id){
//        personService.querUserById(id);
        logger.debug("(((((((((kkkkkkk");

        logger.info("info (((((((((kkkkkkk");
        return personService.querUserById(id);
    }

}

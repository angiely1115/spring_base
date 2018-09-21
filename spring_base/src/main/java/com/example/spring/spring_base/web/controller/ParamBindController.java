package com.example.spring.spring_base.web.controller;

import com.example.spring.spring_base.demo.pojo.PersonPojo;
import com.example.spring.spring_base.demo.pojo.UserPojo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: lvrongzhuan
 * @Description: springmvc参数绑定原理测试
 * @Date: 2018/9/1 10:16
 * @Version: 1.0
 * modified by:
 */
@Controller
@RequestMapping("bind/test")
public class ParamBindController {

    /**
     * 接收json格式参数 只能接收一个对象参数 多个会报错不支持
     * @param personPojo
     * @return
     */
    @RequestMapping(value = "testRb")
    @ResponseBody
    public PersonPojo testRb(@RequestBody PersonPojo personPojo){
        return personPojo;
    }

    /**
     * 接收已表单形式提交的参数 支持绑定多个对象
     * produces = MediaType.APPLICATION_JSON_UTF8_VALUE 指定返回json格式
     * @param personPojo
     * @return
     */
    @RequestMapping(value = "testObj")
    @ResponseBody
    public UserPojo testObj(@Valid PersonPojo personPojo, UserPojo userPojo){
        return userPojo;
    }


    /**
     * 接收指定的参数
     * @param personPojo
     * @return
     */
    @RequestMapping(value = "testParam")
    @ResponseBody
    public PersonPojo testParam(PersonPojo personPojo){
        return personPojo;
    }

    /**
     * 接收指定的参数
     * @param personPojo
     * @return
     */
    @RequestMapping(value = "testProtobuf")
    @ResponseBody
    public PersonPojo testParamProtobuf(PersonPojo personPojo){
        return personPojo;
    }

    @RequestMapping("/testDate")
    public Date testDate(Date date) {
        return date;
    }

    @ResponseBody
    @RequestMapping(value = "testAdvice")
    public PersonPojo testAdvice(){
        throw new RuntimeException("故意错误");
    }

    //,produces={"application/xml; charset=UTF-8"}
    @RequestMapping(value = "testXmlObj")
    @ResponseBody
    public UserPojo testObjXml(@RequestBody UserPojo userPojo){
        return userPojo;
    }

    @RequestMapping(value = "testMethodValid")
    @ResponseBody
    public String testMethodValid(@RequestParam String a, @RequestParam String b){
        return a.concat(b);
    }
}

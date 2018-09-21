package com.example.spring.spring_base.web.controller;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/5 20:34
 * @Version: 1.0
 * modified by:
 */
@Controller
public class LoginController {
    @RequestMapping("/user/login1")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(Objects.equals(username,"admin")&&Objects.equals(password,"123456")){
            session.setAttribute("user",username);
            return "redirect:/main";
        }
        map.put("msg","账号或密码错误");
        return "login";
    }

}

package com.example.spring.spring_base.springConfig.web;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: lvrongzhuan
 * @Description: 登陆拦截器
 * @Date: 2018/9/5 20:24
 * @Version: 1.0
 * modified by:
 */
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        System.out.println("*************************请求之前拦截************************"+request.getRequestURL());
      /*  if(StringUtils.isEmpty(httpSession.getAttribute("user"))){
            request.setAttribute("msg","请先登陆");
            response.sendRedirect("/index");
            System.out.println("##############未登陆");
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("*************************页面渲染之前执行************************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("*************************页面渲染之后执行************************");
    }
}

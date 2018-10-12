package com.example.spring.spring_base.springConfig.web;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/23 19:32
 * @Version: 1.0
 * modified by:
 */
@Component
public class MyErrorAttribute extends DefaultErrorAttributes{
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest,includeStackTrace);
        map.put("ziding","错误");
        Object o = webRequest.getAttribute("ext",0);
        System.out.println("webRequest.getAttribute(\"ext\",0):"+o);
        map.put("ext",o);
        return map;
    }
}

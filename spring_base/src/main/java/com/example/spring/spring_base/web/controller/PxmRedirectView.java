package com.example.spring.spring_base.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/23 22:04
 * @Version: 1.0
 * modified by:
 */
@Component("redireView/pxm")
public class PxmRedirectView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(model.get("pxm"));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(model.get("pxm").toString());
    }
}

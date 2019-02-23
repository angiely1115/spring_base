package com.example.spring.spring_base.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/23 21:59
 * @Version: 1.0
 * modified by:
 */
@Controller
@RequestMapping("redireView")
public class RedirectViewController {
    /**
     * redireView 返回string
     * @return
     */
    @RequestMapping("demo1")
    public String redireViewDemo1(Model model){
        model.addAttribute("pxm","大猪头");
        return "redireView/pxm";
    }

    /**
     * 转发接收方取不到值
     * @param model
     * @return
     */
    @RequestMapping("demo2")
    public RedirectView redireViewDemo2(Model model){
        model.addAttribute("pxm","大猪头-redireViewDemo2");
        return new RedirectView("/redireView/pxm2",true);
    }
    @RequestMapping("pxm2")
    public void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(request.getParameter("pxm"));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(model.get("pxm")+"");
    }
}

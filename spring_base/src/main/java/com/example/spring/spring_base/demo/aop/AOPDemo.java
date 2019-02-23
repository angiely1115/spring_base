package com.example.spring.spring_base.demo.aop;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/16 16:13
 * @Version: 1.0
 * modified by:
 */
public class AOPDemo {
    public String aopDemo(){
        System.out.println("aop简单的目标方法");
//        throw new RuntimeException("创意");
        return "aop 动态代理";
    }

    public String aopDemo2(){
        System.out.println("aop2简单的目标方法");
//        throw new RuntimeException("创意");
        return "aop2 动态代理";
    }

    public String aopExceptionDemo(){
        System.out.println("aop异常简单的目标方法");
        throw new RuntimeException("创意");
    }

}

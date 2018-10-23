package com.example.spring.spring_base.demo.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/20 17:42
 * @Version: 1.0
 * modified by:
 */
@Component
public class MethodInterceptorAop implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod());
        System.out.println(invocation.getThis());
        return null;
    }
}

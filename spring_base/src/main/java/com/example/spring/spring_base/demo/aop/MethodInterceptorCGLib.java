package com.example.spring.spring_base.demo.aop;

import org.springframework.cglib.proxy.MethodInterceptor;
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
public class MethodInterceptorCGLib implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Object:"+o);
        System.out.println("method:"+method);
        System.out.println("objects:"+objects);
        System.out.println("methodProxy:"+methodProxy);
        return o;
    }
}

package com.example.spring.spring_base.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/16 16:34
 * @Version: 1.0
 * modified by:
 */
@Aspect
@Slf4j
public class AOPAspect {
    @Pointcut("execution(public * com.example.spring.spring_base.demo.aop.*.*(..))")
    public void pointcut(){}
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("aop前置通知>>>>>");
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        //接收到请求，记录请求内容
   /*     ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();*/
        // 记录下请求内容
        log.info("\n【请求地址】：{}\n【HTTP METHOD】：{}\n【CLASS_METHOD】：{}\n【请求数据】：{}",
                joinPoint.getSignature().getDeclaringTypeName() + "."
                        + joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning="response",pointcut = "pointcut()")
    public void afterReturning(Object response){
        System.out.println("aop AfterReturning通知>>>>>");
        System.out.println("返回结果:"+response);
    }
}

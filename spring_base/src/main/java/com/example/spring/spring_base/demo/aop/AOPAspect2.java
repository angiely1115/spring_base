package com.example.spring.spring_base.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

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
public class AOPAspect2 {
    @Pointcut("execution(public * com.example.spring.spring_base.demo.aop.*.*(..))")
    public void pointcut(){}
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("aop AOPAspect2前置通知>>>>>");
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        //接收到请求，记录请求内容
   /*     ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();*/
        // 记录下请求内容
        log.info("\nAOPAspect2 【请求地址】：{}\n【HTTP METHOD】：{}\n【CLASS_METHOD】：{}\n【请求数据】：{}",
                joinPoint.getSignature().getDeclaringTypeName() + "."
                        + joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 正常返回执行 抛异常不执行
     * @param response
     */
    @AfterReturning(returning="response",pointcut = "pointcut()")
    public void afterReturning(Object response){
        System.out.println("AOPAspect2 aop AfterReturning通知>>>>>");
        System.out.println("AOPAspect2 返回结果:"+response);
    }

    /**
     * final 增强
     * @param joinPoint
     */
    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println(" AOPAspect2 aop After>>>>>");
//        System.out.println("返回结果:"+response);
    }

}

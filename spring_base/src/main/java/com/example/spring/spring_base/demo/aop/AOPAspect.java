package com.example.spring.spring_base.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    /**
     * 1.环绕通知最新执行
     * 2.如果不执行proceedingJoinPoint.proceed(); 此语句，目标方法不会执行，但是后置通知 都会执行
     * 3.如果不返回值 AfterReturning增强器会没有返回值 ，但是不影响其他的切面的增强器
     * @param proceedingJoinPoint
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕前通知");
        Object ob = null;
        try {
             ob = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕后通知");
        return ob;
    }
    /**
     * 正常返回执行 抛异常不执行
     * @param response
     */
    @AfterReturning(returning="response",pointcut = "pointcut()")
    public void afterReturning(Object response){
        System.out.println("aop AfterReturning通知>>>>>");
        System.out.println("返回结果:"+response);
    }

    /**
     * final 增强
     * @param joinPoint
     */
    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint){
        System.out.println("aop After>>>>>");
//        System.out.println("返回结果:"+response);
    }

}

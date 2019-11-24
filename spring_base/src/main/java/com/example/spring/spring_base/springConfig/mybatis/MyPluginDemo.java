package com.example.spring.spring_base.springConfig.mybatis;

import com.example.spring.spring_base.demo.pojo.PersonPojo;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/26 9:35
 * @Version: 1.0
 * modified by:
 */
@Intercepts({
        //对返回结果进行拦截处理
    @Signature(
       type = ResultSetHandler.class,
       method = "handleResultSets",
       args =  {Statement.class}
    )
})
public class MyPluginDemo implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取目标对象
        DefaultResultSetHandler defaultResultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
        System.out.println("拦截的目标执行对象："+defaultResultSetHandler);
        Object[] objects = invocation.getArgs();
        Statement statements = (Statement) objects[0];
        //获取目标对象的元数据
        MetaObject metaObject = SystemMetaObject.forObject(defaultResultSetHandler);
        System.out.println(metaObject);
        //继续执行目标方法
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        /**
         * 包装目标对象 生成目标对象的代理类
         */
        System.out.println("要代理的目标对象>>>>>>"+target);
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("设置要配置的属性，全局配置:"+properties);
    }
}

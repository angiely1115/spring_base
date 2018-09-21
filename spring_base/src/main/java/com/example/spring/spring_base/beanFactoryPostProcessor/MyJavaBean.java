package com.example.spring.spring_base.beanFactoryPostProcessor;

import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/8/13 19:29
 * @Version: 1.0
 * modified by:
 */
public class MyJavaBean implements InitializingBean{
    private String name;
    private String desc;

    public MyJavaBean() {
    }

    public MyJavaBean(String name, String desc) {
        System.out.println("构造方法****");
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("调用setName方法");
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        System.out.println("调用desc方法");
        this.desc = desc;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用afterPropertiesSet方法");
        this.desc = "在初始化方法后修改desc 新白娘子传奇";
//        TimeUnit.SECONDS.sleep(2);
    }

    public void initMethod(){
        System.out.println("执行initMethod方法");
    }
    @Override
    public String toString() {
        return "MyJavaBean{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

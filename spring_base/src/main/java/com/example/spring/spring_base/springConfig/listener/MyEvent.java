package com.example.spring.spring_base.springConfig.listener;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: lvrongzhuan
 * @Description: 事件
 * @Date: 2018/9/13 9:20
 * @Version: 1.0
 * modified by:
 */
public class MyEvent extends ApplicationEvent{
    private Object object;
    private String name;
    public MyEvent(Object source,String name) {
        super(source);
        this.object = source;
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

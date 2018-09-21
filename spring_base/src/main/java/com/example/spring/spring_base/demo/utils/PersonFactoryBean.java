package com.example.spring.spring_base.demo.utils;

import com.example.spring.spring_base.demo.pojo.PersonPojo;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * @Author: lvrongzhuan
 * @Description: 通FactoryBean获取bean
 * @Date: 2018/7/17 16:31
 * @Version: 1.0
 * modified by:
 */
public class PersonFactoryBean implements FactoryBean<PersonPojo> {
    @Nullable
    @Override
    public PersonPojo getObject() throws Exception {
        return new PersonPojo();
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return PersonPojo.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

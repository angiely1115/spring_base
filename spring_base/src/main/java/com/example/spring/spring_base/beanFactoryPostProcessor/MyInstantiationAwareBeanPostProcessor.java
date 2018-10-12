package com.example.spring.spring_base.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/25 11:35
 * @Version: 1.0
 * modified by:
 */
@Component //得加入到容器中
@ConditionalOnMissingBean(MyInstantiationAwareBeanPostProcessor.class)
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{
    @Nullable
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("前：postProcessBeforeInstantiation:"+beanName);
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("后：postProcessAfterInstantiation:"+beanName);
        return true;
    }

    @Nullable
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("postProcessPropertyValues:"+beanName);
        PropertyValue[] propertyValues =  pvs.getPropertyValues();
        for(int i=0;i<propertyValues.length;i++){
            System.out.println("MyInstantiationAwareBeanPostProcessor:PropertyValue:name:"
                    +propertyValues[i].getName()+" value:"+propertyValues[i].getValue());
        }
        return pvs;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization：beanName "+beanName);
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization：beanName "+beanName);
        return bean;
    }
}

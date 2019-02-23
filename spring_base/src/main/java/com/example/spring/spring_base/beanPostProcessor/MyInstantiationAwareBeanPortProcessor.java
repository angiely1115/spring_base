package com.example.spring.spring_base.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description:
 * postProcessBeforeInstantiation postProcessAfterInstantiation
 * 均先发生与BeanPostPostProcessor 中的前后置处理器
 * @Date: 2019/2/23 15:20
 * @Version: 1.0
 * modified by:
 */
@Component
public class MyInstantiationAwareBeanPortProcessor implements InstantiationAwareBeanPostProcessor, PriorityOrdered {

    /**
     * 在bean实例化之前执行，返还对象可以是目标对象的代理类，抑制了bean默认实例化
     * 如果此方法返还非null，则该bean创建过程将会被短路，应用的唯一做法是
     * 这也是springAOP创建过程中AbstractAutoProxyCreator抽象的类的父类
     * 如果不返还null 则中断bean的创建流程
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("MyInstantiationAwareBeanPortProcessor，对象" + beanName + "调用实例化方法之前的数据： " + beanName);
        return null;
    }

    /**
     * 在bean实例化之后 属性设置之前调用，默认返还true，
     * 如果返还false 则会阻止该bean 在调用后续InstantiationAwareBeanPostProcessor实例
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("MyInstantiationAwareBeanPortProcessor，对象" + beanName + "调用实例化方法 之后 的数据： " + beanName);
        return false;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}

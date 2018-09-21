package com.example.spring.spring_base.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/8/13 19:34
 * @Version: 1.0
 * modified by:
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("调用MyBeanFactoryPostProcessor的postProcessBeanFactory");
       /* MyJavaBean myJavaBean = configurableListableBeanFactory.getBean(MyJavaBean.class);
        System.out.println("还没有实例话bean能获取Bean吗》"+myJavaBean);*/
        //获取指定bean的定义
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("myJavaBean");
        System.out.println("Scope:"+beanDefinition.getScope());
        System.out.println("getBeanClassName:"+beanDefinition.getBeanClassName());
        System.out.println("getDescription:"+beanDefinition.getDescription());
        System.out.println("getFactoryBeanName:"+beanDefinition.getFactoryBeanName());
        System.out.println("getResourceDescription:"+beanDefinition.getResourceDescription());
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
        System.out.println("mutablePropertyValues:"+mutablePropertyValues);
        System.out.println(mutablePropertyValues.getPropertyValue("desc"));
        mutablePropertyValues.addPropertyValue("desc","增加了age属性 值为1000");
        PropertyValue propertyValue = mutablePropertyValues.getPropertyValue("desc");
//        System.out.println(propertyValue.getValue());
//        myJavaBean.setName("赵雅芝");
//        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE); 默认单例
    }
}

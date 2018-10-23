package com.example.spring.spring_base.springConfig;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.env.MutablePropertySources;

/**
 * @Author: lvrongzhuan
 * @Description:s spring base处理器
 * @Date: 2018/10/22 16:07
 * @Version: 1.0
 * modified by:
 */
public abstract class SpringBaseHandle {

    /**
     * 根据指定bean名称注册单实例bean
     */
    protected void register(ConfigurableListableBeanFactory listableBeanFactory,Object
                            bean,String beanName,String alias){
        listableBeanFactory.registerSingleton(beanName,bean);
        if(!listableBeanFactory.containsBean(alias)){
            listableBeanFactory.registerAlias(beanName,alias);
        }
    }

    /**
     * 解析指定的属性文件为对象
     * @param tClass
     * @param propertySources
     * @param <T>
     * @return
     */
    protected <T> T resoverProperties(Class<T> tClass, MutablePropertySources propertySources){
       return new Binder(ConfigurationPropertySources.from(propertySources))
                .bind("", Bindable.of(tClass)).get();
    }
}

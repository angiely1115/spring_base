package com.example.spring.spring_base.demo.ImpotDemo;

import com.example.spring.spring_base.demo.Service.PersonService;
import com.example.spring.spring_base.demo.pojo.PersonPojo;
import com.example.spring.spring_base.springConfig.PropertiesConfig;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description: 通过接口方式来实现bean初始化和销毁时调用
 * @Date: 2018/7/17 20:50
 * @Version: 1.0
 * modified by:
 */
@Component
public class BeanInitDemo implements InitializingBean,DisposableBean{
    @Autowired
    PersonService personService;
    @Autowired
    PersonPojo person;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Override
    public void destroy() throws Exception {
        System.out.println("容器销毁调用:"+this);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean 属性设置完成调用"+person);
        System.out.println(propertiesConfig.getWeb_url());

    }
}

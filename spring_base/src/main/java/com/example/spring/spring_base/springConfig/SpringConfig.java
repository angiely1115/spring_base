package com.example.spring.spring_base.springConfig;

import com.example.spring.spring_base.demo.ImpotDemo.ImportDemo;
import com.example.spring.spring_base.demo.Service.PersonService;
import com.example.spring.spring_base.demo.pojo.PersonPojo;
import com.example.spring.spring_base.demo.utils.PersonFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/8 15:34
 * @Version: 1.0
 * modified by:
 * * bean的生命周期：
 * 		bean创建---初始化----销毁的过程
 * 容器管理bean的生命周期；
 * 我们可以自定义初始化和销毁方法 ；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构造（对象创建）
 * 		单实例：在容器启动的时候创建对象
 * 		多实例：在每次获取的时候创建对象
 * 	    懒加载：使用实例的创建对象，第一次使用(获取)Bean创建对象，并初始化；
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 * 		对象创建完成，并赋值好，调用初始化方法。。。
 * BeanPostProcessor.postProcessAfterInitialization
 * 销毁：
 * 		单实例：容器关闭的时候
 * 		多实例：容器不会管理这个bean；容器不会调用销毁方法；
 *
 *
 * 遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
 * 一但返回null，跳出for循环，不会执行后面的BeanPostProcessor.postProcessorsBeforeInitialization
 *
 * BeanPostProcessor原理
 * populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 * initializeBean
 * {
 * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化
 * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *}
 *
 *
 *
 * 1）、指定初始化和销毁方法；
 * 		通过@Bean指定init-method和destroy-method；
 * 2）、通过让Bean实现InitializingBean（定义初始化逻辑），
 * 				DisposableBean（定义销毁逻辑）;
 * 3）、可以使用JSR250；
 * 		@PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
 * 		@PreDestroy：在容器销毁bean之前通知我们进行清理工作
 * 4）、BeanPostProcessor【interface】：bean的后置处理器；
 * 		在bean初始化前后进行一些处理工作；
 * 		postProcessBeforeInitialization:在初始化之前工作
 * 		postProcessAfterInitialization:在初始化之后工作
 *
 * Spring底层对 BeanPostProcessor 的使用；
 * 		bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async,xxx BeanPostProcessor;
 */
@Configuration
//@ComponentScan(value = {"com.example.spring.spring_base"})
//@Import({ImportDemo.class,PersonFactoryBean.class})  不然启动不起来 因为FactoryBean也创建了
public class SpringConfig {

//    @Autowired
    private PersonPojo personService;

    public SpringConfig() {
//        this.personService = personService;
        System.out.println("配置文件构造方法执行>>>>>>>>>>>>>>"+personService);
    }

    @Bean(value="person",initMethod = "init")
    public PersonPojo getPerson(){
        return new PersonPojo();
    }

    @Scope(value = "prototype")
    @Bean(value="person1")
    public PersonPojo getPerson1(){
        return new PersonPojo();
    }

    @Lazy
    @Bean(value="personLazy")
    public PersonPojo getPersonLazy(){
        System.out.println("懒加载初始化");
        return new PersonPojo();
    }
}

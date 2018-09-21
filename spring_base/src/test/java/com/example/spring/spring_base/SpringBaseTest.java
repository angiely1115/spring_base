package com.example.spring.spring_base;

import com.example.spring.spring_base.beanFactoryPostProcessor.MyJavaBean;
import com.example.spring.spring_base.demo.ImpotDemo.BeanInitDemo;
import com.example.spring.spring_base.demo.aop.AOPDemo;
import com.example.spring.spring_base.demo.pojo.PersonPojo;
import com.example.spring.spring_base.demo.utils.PersonFactoryBean;
import com.example.spring.spring_base.springConfig.*;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/8 15:37
 * @Version: 1.0
 * modified by:
 */
public class SpringBaseTest {

    @Test
    public void springTest01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        //获取所有的指定的bean
        Map map = applicationContext.getBeansOfType(PersonPojo.class);
        System.out.println(map);
        PersonPojo personPojo = (PersonPojo) applicationContext.getBean("person");
        PersonPojo personPojo2 = (PersonPojo) applicationContext.getBean("person1");
        System.out.println("personPojo=personPojo2:"+(personPojo==personPojo2));
        System.out.println(personPojo);
        String[] strings = applicationContext.getBeanDefinitionNames();
        Arrays.stream(strings).forEach(str-> System.out.println("BeanDefinitionNames:"+str));
        System.out.println("applicationContext.getId():"+applicationContext.getId());
        String[] beansNametypes = applicationContext.getBeanNamesForType(PersonPojo.class);
        Arrays.stream(beansNametypes).forEach(str-> System.out.println("getBeanNamesForType:"+str));
        System.out.println(applicationContext.getBean("com.example.spring.spring_base.demo.ImpotDemo.ImportDemo"));
    }

    @Test
    public void testFactoryBean(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(applicationContext.getBean(BeanInitDemo.class));
        //获取FactoryBean本身
        PersonFactoryBean personFactoryBean = applicationContext.getBean(PersonFactoryBean.class);
        System.out.println("personFactoryBean:"+personFactoryBean);
        //获取bean对象 这里spring已经执行了getObject()方法
        System.out.println(applicationContext.getBean("com.example.spring.spring_base.demo.utils.PersonFactoryBean"));
        //获取FactoryBean本身
        Object objBean = applicationContext.getBean("&com.example.spring.spring_base.demo.utils.PersonFactoryBean");
        System.out.println(objBean);
        System.out.println("personFactoryBean==objBean:"+(personFactoryBean==objBean));
        try {
           PersonPojo personPojo =  personFactoryBean.getObject();
            PersonFactoryBean factoryBean = (PersonFactoryBean) objBean;
            System.out.println(factoryBean.getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testConfigProperties(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertiesConfig.class);
        printBeans(applicationContext);
        PropertiesConfig propertiesConfig = applicationContext.getBean(PropertiesConfig.class);
        System.out.println("web_url:"+propertiesConfig.getWeb_url());
        ConfigDemo configDemo = applicationContext.getBean(ConfigDemo.class);
        System.out.println("configDemo:"+configDemo.getName() );
    }
    @Test
    public void testProfile(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //设计属性配置
        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        configurableEnvironment.setActiveProfiles("dev","test");
        System.out.println("*************configurableEnvironment:"+configurableEnvironment);
        //注册配置类
        applicationContext.register(ProfileConfig.class);
        //启动刷新容器
        applicationContext.refresh();
        printBeans(applicationContext);
    }

    @Test
  public void testBeanFactoryPostProcessor(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PostProcessorConfig.class);
        this.printBeans(applicationContext);
        MyJavaBean myJavaBean = applicationContext.getBean(MyJavaBean.class);

        myJavaBean = applicationContext.getBean(MyJavaBean.class);
       /* MyJavaBean myJavaBean = applicationContext.getBean(MyJavaBean.class);
        BeanDefinition beanDefinition = applicationContext.getBeanDefinition("myJavaBean");
        System.out.println("******getScope:"+beanDefinition.getScope());
        System.out.println(myJavaBean);
        System.out.println("******************************************************");*/
  }

  @Test
  public void aopTest(){
      AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AOpConfig.class);
      AOPDemo aopDemo = annotationConfigApplicationContext.getBean(AOPDemo.class);
      System.out.println("aopDemo:"+aopDemo);
      aopDemo.aopDemo();
  }



    private void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println("definitionName:"+name);
        }
    }

}

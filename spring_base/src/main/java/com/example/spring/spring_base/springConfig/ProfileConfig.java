package com.example.spring.spring_base.springConfig;

import com.example.spring.spring_base.demo.pojo.PersonPojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author: lvrongzhuan
 * @Description:Profile 配置类
 * @Date: 2018/7/18 11:19
 * @Version: 1.0
 * modified by:
 */
@Configuration
@Profile("test")
public class ProfileConfig {
    @Bean("dev_person")
    @Profile("dev")
    public PersonPojo personPojoDev(){
        PersonPojo personPojo = new PersonPojo();
        personPojo.setName("dev personPojo");
        return personPojo;
    }

    @Profile("test")
    @Bean("test_person")
    public PersonPojo personPojoTest(){
        PersonPojo personPojo = new PersonPojo();
        personPojo.setName("test personPojo");
        return personPojo;
    }

    @Profile("prod")
    @Bean("prod_person")
    public PersonPojo personPojoProd(){
        PersonPojo personPojo = new PersonPojo();
        personPojo.setName("prod personPojo");
        return personPojo;
    }
}

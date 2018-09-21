package com.rongly.framework.autoconfiguer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lvrongzhuan
 * @Description:自动配置类
 * @Date: 2018/9/11 17:02
 * @Version: 1.0
 * modified by:
 */
@EnableConfigurationProperties(HelloProperties.class)
@Configuration
public class HelloDemoStarterAutoConfiguer {
    @Autowired
    private HelloProperties helloProperties;
    @Bean
    @ConditionalOnMissingBean(DemoStarter.class)
    public DemoStarter demoStarter(HelloProperties helloProperties){
        helloProperties = this.helloProperties;
        return new DemoStarter(helloProperties);
    }
}

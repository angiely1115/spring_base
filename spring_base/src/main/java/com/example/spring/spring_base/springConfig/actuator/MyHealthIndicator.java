package com.example.spring.spring_base.springConfig.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description:自定义健康指标
 * @Date: 2018/9/15 13:05
 * @Version: 1.0
 * modified by:
 */
//加入到容器中
@Component
public class MyHealthIndicator extends AbstractHealthIndicator{

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("msg","服务正常").status("200");
    }
}

package com.example.spring.spring_base;

import com.rongly.framework.autoconfiguer.elasticJob.EnableElasticJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
//开启异步
@EnableAsync
//@ServletComponentScan
@EnableElasticJob
//extends SpringBootServletInitializer
// 抛异常自动重试
@EnableRetry
public class SpringBaseApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBaseApplication.class);
		springApplication.run(args);
//		SpringApplication.run(SpringBaseApplication.class, args);
//		System.exit(-1);//jvm退出 容器销毁
	}
}

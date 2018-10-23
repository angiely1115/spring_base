package com.example.spring.spring_base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
//开启异步
@EnableAsync
@ServletComponentScan
public class SpringBaseApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBaseApplication.class);
		springApplication.run(args);
//		SpringApplication.run(SpringBaseApplication.class, args);
//		System.exit(-1);//jvm退出 容器销毁
	}
}

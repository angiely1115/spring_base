package com.example.spring.spring_base.springConfig.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.FrameworkServlet;
@EnableScheduling
public class MyContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

	/**
	 * 在容器刷新完成执行
	 * @param event
	 */
		@Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
			System.out.println("容器刷新事件执行："+event);
		}
	}
package com.example.spring.spring_base;

import com.example.spring.spring_base.springConfig.ConfigDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBaseApplicationTests {
	@Autowired
	private ConfigDemo configDemo;
	@Autowired
	private Environment environment;

	@Test
	public void contextLoads() {
		System.out.println("qqqq:configDemo"+configDemo);
		System.out.println("lv.config.demo.env1:"+environment.getProperty("lv.config.demo.env1"));
	}

}

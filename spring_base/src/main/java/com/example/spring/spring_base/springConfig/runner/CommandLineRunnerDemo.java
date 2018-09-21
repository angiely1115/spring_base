package com.example.spring.spring_base.springConfig.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: lvrongzhuan
 * @Description: 加入到容器才能执行
 * @Date: 2018/9/11 14:33
 * @Version: 1.0
 * modified by:
 */
@Component
public class CommandLineRunnerDemo implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        System.out.println("执行***********CommandLineRunnerDemo************args：");

        Arrays.stream(args).forEach((a-> System.out.println(a)));
    }
}

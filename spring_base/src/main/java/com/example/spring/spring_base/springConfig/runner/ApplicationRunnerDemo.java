package com.example.spring.spring_base.springConfig.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/11 14:29
 * @Version: 1.0
 * modified by:
 */
@Component
public class ApplicationRunnerDemo implements ApplicationRunner{
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("*******执行ApplicationRunnerDemo");
        args.getOptionNames().stream().forEach((e)->{
            System.out.println("getOptionNames:"+e);
            System.out.println("getOptionValues:"+args.getOptionValues(e));
        });
        Arrays.stream(args.getSourceArgs()).forEach(a-> System.out.println("getSourceArgs："+a));
        System.out.println("*******执行ApplicationRunnerDemo************结束");
    }
}

package com.example.spring.spring_base.web.controller;

import com.example.spring.spring_base.demo.aop.AOPAspect;
import com.example.spring.spring_base.demo.aop.AOPDemo;
import com.example.spring.spring_base.springConfig.async.MyAsyncConfigurer;
import com.example.spring.spring_base.springConfig.listener.MyEvent;
import com.rongly.framework.autoconfiguer.demo.DemoStarter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/11 17:18
 * @Version: 1.0
 * modified by:
 */
@RestController
@Slf4j
public class DemoStarterController {
    @Autowired
    private DemoStarter demoStarter;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AOPDemo aopDemo;
    @Autowired
    private MyAsyncConfigurer myAsyncConfigurer;

    @RequestMapping("say/hello")
    public String sayHelloByName(){
        log.info("开始发布事件");
        applicationContext.publishEvent(new MyEvent(this,"许仙"));
        log.info("发布事件完成");
        aopDemo.aopDemo();
        return demoStarter.sayHelloToName("白素贞");
    }

    @GetMapping("/demo/mvc/async")
    public DeferredResult<Map> helloMvcAsync(){
        log.info("主线程开始");
        DeferredResult<Map> mapDeferredResult = new DeferredResult<>();
        myAsyncConfigurer.getAsyncExecutor().execute(()->{
            this.syncDeferred(mapDeferredResult);
        });
        log.info("主线程结束");
        return mapDeferredResult;
    }

   public DeferredResult<Map>  syncDeferred(DeferredResult<Map> mapDeferredResult){
        log.info("子线程开始");
        Map map = new HashMap();
        map.put("like","喜欢");
        map.put("love","love_xb");
       try {
           TimeUnit.SECONDS.sleep(3);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
        mapDeferredResult.setResult(map);
       log.info("子线程结束");
        return mapDeferredResult;
    }
}

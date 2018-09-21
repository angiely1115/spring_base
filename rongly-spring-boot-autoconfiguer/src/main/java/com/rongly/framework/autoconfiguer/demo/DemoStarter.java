package com.rongly.framework.autoconfiguer.demo;

/**
 * @Author: lvrongzhuan
 * @Description:定义一个需要实例话的bean 主要用该bean来操作业务
 * @Date: 2018/9/11 16:57
 * @Version: 1.0
 * modified by:
 */
public class DemoStarter {
    private HelloProperties helloProperties;

    public String sayHelloToName(String name){
        //使用属性配置
        return helloProperties.getPrefix().concat(":"+name).concat(":"+helloProperties.getSuffix());
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public DemoStarter(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}

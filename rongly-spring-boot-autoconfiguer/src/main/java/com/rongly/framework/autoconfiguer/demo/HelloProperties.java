package com.rongly.framework.autoconfiguer.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: lvrongzhuan
 * @Description: 定义属性配置
 * @Date: 2018/9/11 16:49
 * @Version: 1.0
 * modified by:
 */
@ConfigurationProperties(prefix = "rongly.hello")
public class HelloProperties {

    private String prefix;

    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}

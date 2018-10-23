package com.example.spring.spring_base.springConfig.mybatis;

import lombok.Data;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @Author: lvrongzhuan
 * @Description: mybatis基本配置
 * @Date: 2018/10/22 21:05
 * @Version: 1.0
 * modified by:
 */
@Data
public class MysqlMybatisConfig {
    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
    //对应dataSource mybatis-config文件
    private String mybatisConfig;

    //datasource对应的扫描路径
    private String mapperScanner;

    //sql对象使用的bean路径
    private String aliasPackage;

    //对应sql的xml文件地址
    private String[] mapperLocation;

    private String typeHandlersPackage;

    private ExecutorType executorType;

    private Properties configurationProperties;

    @NestedConfigurationProperty
    private Configuration configuration;

    public Resource[] resolveMapperLocations() {
        return Stream.of(Optional.ofNullable(this.mapperLocation).orElse(new String[0]))
                .flatMap(location -> Stream.of(getResources(location)))
                .toArray(Resource[]::new);
    }

    private Resource[] getResources(String location) {
        try {
            return RESOURCE_PATTERN_RESOLVER.getResources(location);
        } catch (IOException e) {
            return new Resource[0];
        }
    }

}

package com.example.spring.spring_base.springConfig.web;

import com.example.spring.spring_base.component.MyLocaleResolver;
import com.example.spring.spring_base.springConfig.async.MyAsyncConfigurer;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: 扩展springmvc配置
 * @Date: 2018/9/4 19:20
 * @Version: 1.0
 * modified by:
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer{
    @Autowired
    private MyAsyncConfigurer myAsyncConfigurer;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //注册一个空的controller maping映射
        registry.addViewController("/33").setViewName("login");
        registry.addViewController("index").setViewName("login");
        registry.addViewController("main").setViewName("dashboard");
        //配置springsecurity
        registry.addViewController("/user/login").setViewName("login");
    }

    /**
     * springmvc异步支持配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //3s超时 对所有异步返回有效果
        configurer.setDefaultTimeout(3*1000);
        //设置线程异步线程池 只对WebAsyncTask 异步有效果
        configurer.setTaskExecutor(myAsyncConfigurer.getAsyncExecutor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截和排除 springboot 2.x版本依赖的是spring5 会拦截静态资源
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/base","/index","/user/login","/asserts/**","/webjars/**","/*.js","/*.css");
    }

    //注入自定义的区域语言解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    /**
     * 快速验证失败，就是只要发现一个验证异常就返回，不会全部验证完毕，默认全部验证
     * @return
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        javax.validation.Validator validator = validatorFactory.getValidator();
        return validator;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0,new ProtobufHttpMessageConverter());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}

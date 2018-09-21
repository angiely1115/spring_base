package com.example.spring.spring_base.demo.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/8 15:34
 * @Version: 1.0
 * modified by:
 */
@Data
public class PersonPojo {
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    private Date outDate = new Date();

    public PersonPojo() {
        System.out.println("PersonPojo构造方法初始化");
    }

    public void init(){
        System.out.println("personPojo init****************");
    }
}

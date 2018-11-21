package com.example.spring.spring_base.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/23 20:23
 * @Version: 1.0
 * modified by:
 */
@Data
public class UserAccount implements Serializable{
    private Long id;
    private String accountName;
    private Long userId;
    private Long amt;

}

package com.example.spring.spring_base.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/23 9:57
 * @Version: 1.0
 * modified by:
 */
@Data
public class UserEntity implements Serializable{
    private Long id;
    private String name;
    private Integer age;
    private UserAccount userAccount;
    private List<RolesEntity> rolesEntityList;
}

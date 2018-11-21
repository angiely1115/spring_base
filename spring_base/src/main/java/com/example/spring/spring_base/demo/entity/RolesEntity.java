package com.example.spring.spring_base.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/23 20:06
 * @Version: 1.0
 * modified by:
 */
@Data
public class RolesEntity implements Serializable{
    private Long roleId;
    private String roleName;
    private Long userId;
}

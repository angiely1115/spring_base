package com.example.spring.spring_base.demo.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/1 12:51
 * @Version: 1.0
 * modified by:
 */
@Data
//@JacksonXmlRootElement
public class UserPojo implements Serializable{
    @XmlElement(name = "address")
    private String address;
}

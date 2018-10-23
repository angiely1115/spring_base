package com.example.spring.spring_base.demo.aop;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/21 16:33
 * @Version: 1.0
 * modified by:
 */

public class TransactionAopDemo {


    @Transactional
    public void transactionAop01(){
        System.out.println("transactionAop01");

    }


    public void notTransactionAop01(){
        System.out.println("notTransactionAop01");

    }
}

package com.example.spring.spring_base.demo.Service;

import com.example.spring.spring_base.demo.dao.read.UserDao;
import com.example.spring.spring_base.demo.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/8 15:57
 * @Version: 1.0
 * modified by:
 */
@Service
@Slf4j
public class PersonService {
    @Autowired
    private UserDao userDao;
    /**
     *加上只读事务后 可以使用mybatis一级缓存
     */
    @Transactional(value = "readNodeTx",readOnly=true)
    public UserEntity querUserById(Long id){
        userDao.selectUserById(id);
        return userDao.selectUserById(id);
    }

    public void oneCache(long id){
        this.querUserById(id);
    }

    @Retryable(backoff = @Backoff(delay = 1000L,multiplier = 2))
     public void springResrtDemo() {
      log.info("spring 重试");
     }
}

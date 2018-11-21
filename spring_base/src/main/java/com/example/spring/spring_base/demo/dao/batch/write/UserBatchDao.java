package com.example.spring.spring_base.demo.dao.batch.write;

import com.example.spring.spring_base.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: lvrongzhuan
 * @Description 用户批量操作
 * @Date: 2018/10/26 10:49
 * @Version: 1.0
 * modified by:
 */
public interface UserBatchDao {
    int batchInsertUser(@Param("userEntity") UserEntity userEntity);
}

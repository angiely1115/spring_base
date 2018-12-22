package com.rongly.framework.autoconfiguer.elasticJob;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/26 20:02
 * @Version: 1.0
 * modified by:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ElasticJobAutoConfiguer.class)
public @interface EnableElasticJob {
}

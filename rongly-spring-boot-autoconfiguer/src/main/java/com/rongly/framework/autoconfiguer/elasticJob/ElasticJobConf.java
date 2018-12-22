package com.rongly.framework.autoconfiguer.elasticJob;

import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Hikaru on 17/9/6.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ElasticJobConf {

  String cron();

  int shardingTotalCount() default  1;

  String shardingItemParameters() default "";

  boolean overwrite() default true;

  boolean disabled() default false;

  boolean streamingProcess() default false;

  String scriptCommandLine() default "";

  Class<? extends ElasticJobListener> [] elasticJobListeners() default {};

  String jobShardingStrategyClass() default "";

}

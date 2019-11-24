package com.rongly.framework.autoconfiguer.elasticJob;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.script.ScriptJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.JobEventListener;
import com.dangdang.ddframe.job.event.JobEventListenerConfigurationException;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.rongly.framework.autoconfiguer.demo.HelloProperties;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/26 16:48
 * @Version: 1.0
 * modified by:
 */
@EnableConfigurationProperties(ElasticJobProperties.class)
@Configuration
@AutoConfigureAfter(name="${after_datasource}")
public class ElasticJobAutoConfiguer implements InitializingBean{
    private static final String cron_pre = "${";
    private static final String cron_end = "}";
    private ElasticJobProperties elasticJobProperties;

    private ApplicationContext applicationContext;

    public ElasticJobAutoConfiguer(ElasticJobProperties elasticJobProperties, ApplicationContext applicationContext) {
        this.elasticJobProperties = elasticJobProperties;
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //获取该注解下所有bean名称
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(ElasticJobConf.class);
        if(beanNames!=null){
            ZookeeperRegistryCenter zookeeperRegistryCenter = this.registryZookeeperCenter();
            List<Object> list = Lists.newArrayListWithCapacity(5);
            Stream.of(beanNames).forEach(beanName->{

                //根据bean名称获取所有任务的bean
                ElasticJob elasticJob = applicationContext.getBean(beanName,ElasticJob.class);
                //获取该类的注解
                ElasticJobConf elasticJobConf = elasticJob.getClass().getAnnotation(ElasticJobConf.class);
                LiteJobConfiguration liteJobConfiguration;
                if(elasticJob instanceof SimpleJob){
                    liteJobConfiguration =  this.createSimpleJobLiteJobConfiguration(elasticJob,elasticJobConf);
                    list.add(elasticJob);
                }else if(elasticJob instanceof DataflowJob){
                    liteJobConfiguration = this.createDataFlowJobConfiguration(elasticJob,elasticJobConf);
                    list.add(elasticJob);
                }else if(elasticJob instanceof ScriptJob){
                    liteJobConfiguration = this.createScriptJobConfiguration(elasticJob,elasticJobConf);
                    list.add(null);
                }else {
                    throw new IllegalArgumentException("error elasticJob type");
                }
                list.add(zookeeperRegistryCenter);
                list.add(liteJobConfiguration);
                JobEventConfiguration jobEventConfiguration =  this.jobEventConfiguration();
                Optional.ofNullable(jobEventConfiguration).ifPresent(jobe->list.add(jobe));
                ElasticJobListener[] elasticJobListeners = transElasticJobListeners(elasticJobConf.elasticJobListeners());
                list.add(elasticJobListeners);
                createSpringJobScheduler(elasticJob.getClass().getSimpleName()+"elaticjob",list);
            });
        }
    }

    /**
     * 创建job实例bean
     * @param name
     * @param list
     */
    public void createSpringJobScheduler(String name, List list){
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext;
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SpringJobScheduler.class);
        beanDefinitionBuilder.setInitMethodName("init");
        int size = list.size();
        for (int i = 0;i<size;i++){
            beanDefinitionBuilder.addConstructorArgValue(list.get(i));
        }
        beanDefinitionRegistry.registerBeanDefinition(name,beanDefinitionBuilder.getBeanDefinition());
        applicationContext.getBean(name);
    }
    /**
     * 初始化任务zookeeper注册配置中心
     * @return
     */
    protected ZookeeperRegistryCenter registryZookeeperCenter(){
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(elasticJobProperties.getServerList(),elasticJobProperties.getNamespace());
        BeanUtils.copyProperties(elasticJobProperties,zookeeperConfiguration,"serverList","namespace");
        ZookeeperRegistryCenter zookeeperRegistryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        zookeeperRegistryCenter.init();
        return zookeeperRegistryCenter;
    }

    private JobEventConfiguration jobEventConfiguration(){
        //dataSource bean的名称
       String dataSoucrce =  elasticJobProperties.getDataSource();
       if(null!=dataSoucrce){
           DataSource dataSource = applicationContext.getBean(dataSoucrce, DataSource.class);
           return new JobEventRdbConfiguration(dataSource);
       }
       return null;
    }

    /**
     * 解析监听类
     */
    protected ElasticJobListener[] transElasticJobListeners(Class<? extends ElasticJobListener> [] classes){
        ElasticJobListener[] elasticJobListeners = new ElasticJobListener[]{};
        if(classes!=null){
            Stream.of(classes).forEach(classzz->{
                ArrayUtils.add(elasticJobListeners,classzz);
            });
        }
        return elasticJobListeners;
    }
    /**
     * 创建SimpleJob配置
     * @param elasticJob
     * @param ElasticJobConf
     * @return
     */
    protected LiteJobConfiguration createSimpleJobLiteJobConfiguration(ElasticJob elasticJob,
                                                                       ElasticJobConf ElasticJobConf) {
        return LiteJobConfiguration
                .newBuilder(new SimpleJobConfiguration(
                        JobCoreConfiguration
                                .newBuilder(elasticJob.getClass().getName(), cronValue(ElasticJobConf.cron()),
                                        ElasticJobConf.shardingTotalCount())
                                .shardingItemParameters(Strings.emptyToNull(ElasticJobConf.shardingItemParameters()))
                                .build(),
                        elasticJob.getClass().getCanonicalName()
                )).overwrite(ElasticJobConf.overwrite()).disabled(ElasticJobConf.disabled())
                .jobShardingStrategyClass(Strings.emptyToNull(ElasticJobConf.jobShardingStrategyClass()))
                .build();
    }

    protected LiteJobConfiguration createDataFlowJobConfiguration(ElasticJob elasticJob,
                                                                  ElasticJobConf ElasticJobConf) {
        return LiteJobConfiguration
                .newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder(
                        elasticJob.getClass().getName(), cronValue(ElasticJobConf.cron()),
                        ElasticJobConf.shardingTotalCount())
                        .shardingItemParameters(Strings.emptyToNull(ElasticJobConf.shardingItemParameters()))
                        .build(),
                        elasticJob.getClass().getCanonicalName(), ElasticJobConf.streamingProcess()))
                .overwrite(ElasticJobConf.overwrite()).disabled(ElasticJobConf.disabled())
                .jobShardingStrategyClass(Strings.emptyToNull(ElasticJobConf.jobShardingStrategyClass()))
                .build();
    }

    protected LiteJobConfiguration createScriptJobConfiguration(ElasticJob elasticJob,
                                                                ElasticJobConf ElasticJobConf) {
        return LiteJobConfiguration
                .newBuilder(new ScriptJobConfiguration(JobCoreConfiguration.newBuilder(
                        elasticJob.getClass().getName(), cronValue(ElasticJobConf.cron()),
                        ElasticJobConf.shardingTotalCount())
                        .shardingItemParameters(Strings.emptyToNull(ElasticJobConf.shardingItemParameters()))
                        .build(),
                        ElasticJobConf.scriptCommandLine()))
                .overwrite(ElasticJobConf.overwrite()).disabled(ElasticJobConf.disabled())
                .jobShardingStrategyClass(Strings.emptyToNull(ElasticJobConf.jobShardingStrategyClass()))
                .build();
    }

    private static String cronValue(String cron){
        if(cron.startsWith(cron_pre)&&cron.endsWith(cron_end)){
            cron = cron.replace(cron_pre,"").replace(cron_end,"");
        }
        return cron;

    }

}

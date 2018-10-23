package com.example.spring.spring_base.springConfig.jdbc;

import ch.qos.logback.core.db.dialect.SQLDialect;
import com.example.spring.spring_base.springConfig.SpringBaseHandle;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @Author: lvrongzhuan
 * @Description:mysql 多数据元配置
 * @Date: 2018/10/22 16:36
 * @Version: 1.0
 * modified by:
 */
@Configuration
//@EnableConfigurationProperties(MultiMysqlProperties.class)
@ConditionalOnClass({DataSource.class, HikariDataSource.class, DataSourceSpy.class})
@Slf4j
public class MysqlDataSourceAutoConfiguration extends SpringBaseHandle implements BeanFactoryPostProcessor,EnvironmentAware,Ordered{

    private ConfigurableEnvironment environment;
    private MultiMysqlProperties multiMysqlProperties;
    /**
     * 为了打印Sql语言配置
     */
    private static final String[] PROPERTIES_TO_COPY = {
            "log4jdbc.log4j2.properties.file",
            "log4jdbc.debug.stack.prefix",
            "log4jdbc.sqltiming.warn.threshold",
            "log4jdbc.sqltiming.error.threshold",
            "log4jdbc.dump.booleanastruefalse",
            "log4jdbc.dump.fulldebugstacktrace",
            "log4jdbc.dump.sql.maxlinelength",
            "log4jdbc.statement.warn",
            "log4jdbc.dump.sql.select",
            "log4jdbc.dump.sql.insert",
            "log4jdbc.dump.sql.update",
            "log4jdbc.dump.sql.delete",
            "log4jdbc.dump.sql.create",
            "log4jdbc.dump.sql.addsemicolon",
            "log4jdbc.auto.load.popular.drivers",
            "log4jdbc.drivers",
            "log4jdbc.trim.sql",
            "log4jdbc.trim.sql.extrablanklines",
            "log4jdbc.suppress.generated.keys.exception",
            "log4jdbc.log4j2.properties.file",
    };

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        MultiMysqlProperties multiMysqlProperties = super.resoverProperties(
                MultiMysqlProperties.class, this.environment.getPropertySources());
        this.initLog4Jdbc();
        multiMysqlProperties.getRongMysql().forEach((name,properties)->{
            this.createBean(beanFactory,name,properties);
        });
    }
    private void createBean(ConfigurableListableBeanFactory configurableListableBeanFactory,
                            String prefixName, MysqlProperties mysqlProperties) {
        String url = mysqlProperties.getUrl();
        checkArgument(StringUtils.hasLength(url), prefixName + " url is null or empty");
        log.info("prefixName is {}, url is {}, jdbc properties is {}", prefixName, StringUtils.substringMatch(url,0,"?"),
                mysqlProperties);
        if (configurableListableBeanFactory.containsBean("hnSleuthMysqlBean")) {
            log.info("prefixName is {}, url {} to add mysql sleuth", prefixName, url);
          /*先注释吧 看不懂
          HnSleuthMysqlBean hnSleuthMysqlBean = configurableListableBeanFactory
                    .getBean(HnSleuthMysqlBean.class);
            mysqlProperties.setUrl(hnSleuthMysqlBean.getSleuthMysql(mysqlProperties.getUrl()));*/
        }
        HikariDataSource hikariDataSource = createHikariDataSource(mysqlProperties);
        DataSourceSpy dataSource = new DataSourceSpy(hikariDataSource);
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        AnnotationTransactionAspect.aspectOf().setTransactionManager(transactionManager);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        register(configurableListableBeanFactory, dataSource, prefixName + "DataSource",
                prefixName + "Ds");
        register(configurableListableBeanFactory, jdbcTemplate, prefixName + "JdbcTemplate",
                prefixName + "Jt");
        register(configurableListableBeanFactory, transactionManager, prefixName + "TransactionManager",
                prefixName + "Tx");
      /*  DSLContext dslContext = new DefaultDSLContext(dataSource, SQLDialect.MYSQL);
        register(configurableListableBeanFactory, dslContext, prefixName + "DslContext",
                prefixName + "Dc");*/
    }
    private HikariDataSource createHikariDataSource(MysqlProperties mysqlProperties) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(mysqlProperties.getUrl());
        hikariDataSource.setUsername(mysqlProperties.getUsername());
        hikariDataSource.setPassword(mysqlProperties.getPassword());

        JdbcPoolConfig jdbcPoolConfig = mysqlProperties.getPool();
        hikariDataSource.setAutoCommit(jdbcPoolConfig.isAutoCommit());
        hikariDataSource.setConnectionTimeout(jdbcPoolConfig.getConnectionTimeout());
        hikariDataSource.setIdleTimeout(jdbcPoolConfig.getIdleTimeout());
        hikariDataSource.setMaxLifetime(jdbcPoolConfig.getMaxLifetime());
        hikariDataSource.setMaximumPoolSize(jdbcPoolConfig.getMaximumPoolSize());
        hikariDataSource.setMinimumIdle(jdbcPoolConfig.getMinimumIdle());
        hikariDataSource
                .setInitializationFailTimeout(jdbcPoolConfig.getInitializationFailTimeout());
        hikariDataSource.setIsolateInternalQueries(jdbcPoolConfig.isIsolateInternalQueries());
        hikariDataSource.setReadOnly(jdbcPoolConfig.isReadOnly());
        hikariDataSource.setRegisterMbeans(jdbcPoolConfig.isRegisterMbeans());
        Optional.ofNullable(jdbcPoolConfig.getDriverClassName())
                .ifPresent(hikariDataSource::setDriverClassName);
        hikariDataSource.setValidationTimeout(jdbcPoolConfig.getValidationTimeout());
        hikariDataSource.setLeakDetectionThreshold(jdbcPoolConfig.getLeakDetectionThreshold());
        hikariDataSource.setConnectionInitSql(jdbcPoolConfig.getConnectionInitSql());
        return hikariDataSource;
    }

    private void initLog4Jdbc() {
        for (final String property : PROPERTIES_TO_COPY) {
            if (this.environment.containsProperty(property)) {
                System.setProperty(property, this.environment.getProperty(property));
            }
        }
        System.setProperty("log4jdbc.spylogdelegator.name", this.environment
                .getProperty("log4jdbc.spylogdelegator.name", Slf4jSpyLogDelegator.class.getName()));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE-1;
    }
}

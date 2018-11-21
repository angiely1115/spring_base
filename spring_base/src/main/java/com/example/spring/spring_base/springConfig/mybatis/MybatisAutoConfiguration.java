package com.example.spring.spring_base.springConfig.mybatis;

import com.example.spring.spring_base.springConfig.SpringBaseHandle;
import com.example.spring.spring_base.springConfig.jdbc.MysqlDataSourceAutoConfiguration;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * Created on 2018/1/13.
 */
@Slf4j
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
//@ConditionalOnMapProperty(value = MybatisProperties.class)
@AutoConfigureAfter(MysqlDataSourceAutoConfiguration.class)
public class MybatisAutoConfiguration extends SpringBaseHandle implements
        BeanFactoryPostProcessor, ResourceLoaderAware, BeanFactoryAware, EnvironmentAware, Ordered {

  private ConfigurableEnvironment environment;

  private ResourceLoader resourceLoader;

  private BeanFactory beanFactory;

  @Override
  public void postProcessBeanFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    MybatisProperties mybatisProperties = super.resoverProperties(MybatisProperties.class,
        this.environment.getPropertySources());
    mybatisProperties.getRongMySql().forEach(
        (name, properties) -> createBean(configurableListableBeanFactory, name, properties));
  }

  private void createBean(ConfigurableListableBeanFactory configurableListableBeanFactory,
      String prefixName, MysqlMybatisConfig mysqlMybatisConfig) {
    SqlSessionFactory sqlSessionFactory = createSqlSessionFactory(configurableListableBeanFactory,
        prefixName, mysqlMybatisConfig);
    if (sqlSessionFactory == null) {
      log.info("mybatis {} sql session factory register failed!", prefixName);
      return;
    }

    log.info("mybatis {} sql session factory register success!", prefixName);

    createSqlSessionTemplate(configurableListableBeanFactory, prefixName, mysqlMybatisConfig,
        sqlSessionFactory);

    log.info("mybatis {} sql session template register success!", prefixName);
  }
  @Nullable
  private  SqlSessionFactory createSqlSessionFactory(
      ConfigurableListableBeanFactory configurableListableBeanFactory,
      String prefixName, MysqlMybatisConfig mysqlMybatisConfig) {
    DataSource dataSource = configurableListableBeanFactory
        .getBean(prefixName + "Ds", DataSource.class);

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setVfs(MySpringBootVFS.class);
    Optional.ofNullable(mysqlMybatisConfig.getMybatisConfig()).map(this.resourceLoader::getResource)
        .ifPresent(sqlSessionFactoryBean::setConfigLocation);

    org.apache.ibatis.session.Configuration configuration = mysqlMybatisConfig.getConfiguration();
    if (configuration == null && !StringUtils.hasText(mysqlMybatisConfig.getMybatisConfig())) {
      configuration = new org.apache.ibatis.session.Configuration();
    }
    sqlSessionFactoryBean.setConfiguration(configuration);
    Optional.ofNullable(mysqlMybatisConfig.getConfigurationProperties())
        .ifPresent(sqlSessionFactoryBean::setConfigurationProperties);
    Optional.ofNullable(mysqlMybatisConfig.getAliasPackage())
        .ifPresent(sqlSessionFactoryBean::setTypeAliasesPackage);
    Optional.ofNullable(mysqlMybatisConfig.getTypeHandlersPackage())
        .ifPresent(sqlSessionFactoryBean::setTypeHandlersPackage);
    if (!ObjectUtils.isEmpty(mysqlMybatisConfig.resolveMapperLocations())) {
      sqlSessionFactoryBean.setMapperLocations(mysqlMybatisConfig.resolveMapperLocations());
    }

    try {
      SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
      if (sqlSessionFactory == null) {
        log.error("sqlSessionFactoryBean get object is null");
        return null;
      }
      register(configurableListableBeanFactory, sqlSessionFactoryBean, prefixName + "SessionFactory",
          prefixName + "Sf");

      if (!Strings.isNullOrEmpty(mysqlMybatisConfig.getMapperScanner())) {
        createBasePackageScanner((BeanDefinitionRegistry) configurableListableBeanFactory,
            mysqlMybatisConfig.getMapperScanner(), prefixName);
      } else {
        createClassPathMapperScanner((BeanDefinitionRegistry) configurableListableBeanFactory,
            prefixName);
      }
      return sqlSessionFactory;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  private void createSqlSessionTemplate(
          ConfigurableListableBeanFactory configurableListableBeanFactory, String prefixName,
          MysqlMybatisConfig mysqlMybatisConfig, SqlSessionFactory sqlSessionFactory) {
    ExecutorType executorType = mysqlMybatisConfig.getExecutorType();
    SqlSessionTemplate sqlSessionTemplate;
    if (executorType != null) {
      sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, executorType);
    } else {
      sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    }
    register(configurableListableBeanFactory, sqlSessionTemplate, prefixName + "SessionTemplate",
        prefixName + "St");
  }

  private void createBasePackageScanner(BeanDefinitionRegistry registry, String basePackage,
                                        String prefixName) {
    MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
    scannerConfigurer.setBasePackage(basePackage);
    scannerConfigurer.setSqlSessionFactoryBeanName(prefixName + "SessionFactory");
    scannerConfigurer.postProcessBeanDefinitionRegistry(registry);
  }

  private void createClassPathMapperScanner(BeanDefinitionRegistry registry, String prefixName) {
    ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);

    try {
      if (this.resourceLoader != null) {
        scanner.setResourceLoader(this.resourceLoader);
      }

      List<String> packages = AutoConfigurationPackages.get(beanFactory);
      packages.forEach(pkg -> log.info("Using auto-configuration base package '{}'", pkg));

      scanner.setAnnotationClass(Mapper.class);
      scanner.setSqlSessionFactoryBeanName(prefixName + "SessionFactory");
      scanner.registerFilters();
      scanner.doScan(StringUtils.toStringArray(packages));
    } catch (IllegalStateException ex) {
      log.info("Could not determine auto-configuration package", ex);
    }
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = (ConfigurableEnvironment) environment;
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @Override
  public int getOrder() {
    return Integer.MAX_VALUE;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }
}

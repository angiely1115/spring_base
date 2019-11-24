package com.rongly.framework.autoconfiguer.elasticJob;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Hikaru on 17/9/6.
 */
@ConfigurationProperties(prefix = "rongly.elastic.job")
public class ElasticJobProperties {

  private String serverList;//zookeeper服务器列表
  private String namespace;;//命名空间
  /***
   *
   */
  private String dataSource;
  private int baseSleepTimeMilliseconds = 1000;
  private int maxSleepTimeMilliseconds = 3000;
  private int maxRetries = 3;
  private int sessionTimeoutMilliseconds = 60000;
  private int connectionTimeoutMilliseconds = 15000;
  private String digest = "";

  public String getServerList() {
    return serverList;
  }

  public void setServerList(String serverList) {
    this.serverList = serverList;
  }

  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public int getBaseSleepTimeMilliseconds() {
    return baseSleepTimeMilliseconds;
  }

  public void setBaseSleepTimeMilliseconds(int baseSleepTimeMilliseconds) {
    this.baseSleepTimeMilliseconds = baseSleepTimeMilliseconds;
  }

  public int getMaxSleepTimeMilliseconds() {
    return maxSleepTimeMilliseconds;
  }

  public void setMaxSleepTimeMilliseconds(int maxSleepTimeMilliseconds) {
    this.maxSleepTimeMilliseconds = maxSleepTimeMilliseconds;
  }

  public int getMaxRetries() {
    return maxRetries;
  }

  public void setMaxRetries(int maxRetries) {
    this.maxRetries = maxRetries;
  }

  public int getSessionTimeoutMilliseconds() {
    return sessionTimeoutMilliseconds;
  }

  public void setSessionTimeoutMilliseconds(int sessionTimeoutMilliseconds) {
    this.sessionTimeoutMilliseconds = sessionTimeoutMilliseconds;
  }

  public int getConnectionTimeoutMilliseconds() {
    return connectionTimeoutMilliseconds;
  }

  public void setConnectionTimeoutMilliseconds(int connectionTimeoutMilliseconds) {
    this.connectionTimeoutMilliseconds = connectionTimeoutMilliseconds;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }
}

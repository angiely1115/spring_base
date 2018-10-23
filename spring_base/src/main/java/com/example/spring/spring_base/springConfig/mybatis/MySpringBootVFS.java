package com.example.spring.spring_base.springConfig.mybatis;

import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 2018/1/13.
 */
public class MySpringBootVFS extends VFS {

  private final ResourcePatternResolver resourceResolver;

  public MySpringBootVFS() {
    this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  protected List<String> list(URL url, String path) throws IOException {
    Resource[] resources = resourceResolver.getResources("classpath*:" + path + "/**/*.class");
    return Stream.of(resources).map(resource -> preserveSubpackageName(resource, path))
        .collect(Collectors.toList());
  }

  private static String preserveSubpackageName(final Resource resource, final String rootPath) {
    try {
      final String uriStr = resource.getURI().toString();
      final int start = uriStr.indexOf(rootPath);
      return uriStr.substring(start);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}

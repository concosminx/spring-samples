package com.nimsoc.spring.config;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  private final int maxUploadSizeInMb = 5 * 1024 * 1024;

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // application context created by ContextLoaderListener
    return null;
  }

  @Override
  protected Filter[] getServletFilters() {
    return null;
  }

  @Override
  protected void customizeRegistration(Dynamic registration) {
    File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
    MultipartConfigElement multipartConfigElement
            = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                    maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
    registration.setMultipartConfig(multipartConfigElement);
  }
}

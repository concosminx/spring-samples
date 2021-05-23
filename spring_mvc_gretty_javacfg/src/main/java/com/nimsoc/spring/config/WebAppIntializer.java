package com.nimsoc.spring.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.support.*;

public class WebAppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(WebAppIntializer.class);

  @Override
  protected Class<?>[] getServletConfigClasses() {
    LOG.debug("return -> servlet config classes here ");
    return new Class<?>[]{WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    LOG.debug("return -> servlet mappings ");
    return new String[]{"/"};
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    LOG.debug("return -> root web application context created by ContextLoaderListener");
    return null;
  }

  @Override
  protected Filter[] getServletFilters() {
    //return your filter array
    return null;
  }

  @Override
  protected void customizeRegistration(Dynamic registration) {
    //Do Something
  }

}

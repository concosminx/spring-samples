package com.nimsoc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // application context created by DispatcherServlet by loading web components
    return new Class<?>[]{WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    // TODO Auto-generated method stub
    return new String[]{"/"};
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // application context created by ContextLoaderListener
    return null;
  }
}

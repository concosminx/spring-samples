package com.nimsoc.spring.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;

import com.nimsoc.spring.web.ExampleFilter;
import com.nimsoc.spring.web.ExampleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomInitializer implements WebApplicationInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(CustomInitializer.class);

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    LOG.debug("startup -> register custom servlets and filters ");

    ServletRegistration.Dynamic reg = servletContext.addServlet("exampleServlet", ExampleServlet.class);
    reg.addMapping("/exampleReq/**");

    FilterRegistration.Dynamic fReg = servletContext.addFilter("exampleFilter", ExampleFilter.class);
    fReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/exampleReq/**");

    //servletContext.addListener(new ContextLoaderListener("applicationContext.xml"));
  }

}

package com.nimsoc.rtv.config;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

public class WebAppIntializer extends AbstractReactiveWebInitializer {

  @Override
  protected Class<?>[] getConfigClasses() {
    return new Class<?>[]{WebConfig.class, RootConfig.class};
  }
}

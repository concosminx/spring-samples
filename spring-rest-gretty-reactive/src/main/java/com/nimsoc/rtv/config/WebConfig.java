package com.nimsoc.rtv.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "com.nimsoc")
public class WebConfig implements WebFluxConfigurer {

}

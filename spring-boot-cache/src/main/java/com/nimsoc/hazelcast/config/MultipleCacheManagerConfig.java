package com.nimsoc.hazelcast.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

@Configuration
@EnableCaching
public class MultipleCacheManagerConfig {

  @Bean
  @Primary
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager("users", "orders");
    cacheManager.setCaffeine(Caffeine.newBuilder()
        .initialCapacity(200)
        .maximumSize(500)
        .weakKeys()
        .recordStats());
    return cacheManager;
  }

  @Bean
  public CacheManager simpleCacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    Cache booksCache = new ConcurrentMapCache("books");
    cacheManager.setCaches(Arrays.asList(booksCache));
    return cacheManager;
  }

  @Bean("customKeyGenerator")
  public KeyGenerator keyGenerator() {
    return new CustomKeyGenerator();
  }
}
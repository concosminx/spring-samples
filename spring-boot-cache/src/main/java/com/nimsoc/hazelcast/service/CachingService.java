package com.nimsoc.hazelcast.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CachingService {
  @Autowired
  CacheManager cacheManager;

  public void evictAllCaches() {
    log.info("cacheNames: {}", cacheManager.getCacheNames());

    cacheManager.getCacheNames()
        .parallelStream()
        .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
  }

  @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 10)
  public void evictAllcachesAtIntervals() {
    evictAllCaches();
  }
}

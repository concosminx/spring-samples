package com.nimsoc.i18n.aspect;

import com.nimsoc.i18n.config.RateLimitConfig;
import com.nimsoc.i18n.service.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RateLimitAspect {
  @Autowired
  private RateLimitConfig rateLimitConfig;

  @Autowired
  private RateLimiter rateLimiter;

  @Around("@annotation(RateLimited)")
  public Object enforceRateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
    //String key = getKey(joinPoint);

    // Get the current request from the JoinPoint
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = requestAttributes.getRequest();
    String key = getKey(request);
    if (!rateLimiter.tryAcquire(key, rateLimitConfig.getRequests(), rateLimitConfig.getSeconds())) {
      throw new RuntimeException("Rate limit exceeded");
    }
    return joinPoint.proceed();
  }

  //private String getKey(ProceedingJoinPoint joinPoint) {
    // Generate a unique key for the method being called
    // Example: method signature, user ID, IP address, etc.
    // You can customize this based on your requirements
  //}

  private String getKey(HttpServletRequest request) {
    // Get the IP address of the client making the request
    String ipAddress = request.getRemoteAddr();
    return ipAddress; // Use IP address as the key
  }
}

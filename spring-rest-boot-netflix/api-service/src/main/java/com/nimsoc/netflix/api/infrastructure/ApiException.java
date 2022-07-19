package com.nimsoc.netflix.api.infrastructure;

public class ApiException extends RuntimeException {

  public ApiException(String message) {
    super(message);
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }
}

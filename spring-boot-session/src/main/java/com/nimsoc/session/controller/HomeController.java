package com.nimsoc.session.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

  private final String HOME_VIEW_COUNT = "home_view_count";

  @GetMapping("/")
  public String home(Principal principal, HttpSession session) {
    incrementCount(session,HOME_VIEW_COUNT);
    return "Hello, " + principal.getName();
  }

  @GetMapping("/count")
  public String count(HttpSession session) {
    return "HOME_VIEW_COUNT: " + session.getAttribute(HOME_VIEW_COUNT);
  }

  private void incrementCount(HttpSession session, String attr) {
    var homeViewCount = session.getAttribute(HOME_VIEW_COUNT) == null ? 0 : (Integer) session.getAttribute(HOME_VIEW_COUNT);
    session.setAttribute(attr,homeViewCount + 1);
  }
}
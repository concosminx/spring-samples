package com.nimsoc.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExampleController {
  @RequestMapping(value = {"/", "/example"}, method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView visitHome() {
    ModelAndView mav = new ModelAndView("example");
    return mav;
  }

}

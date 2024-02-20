package cgg.springboot.springbootproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TestController
 */
@Controller
@ResponseBody
public class TestController {

  @GetMapping("/test")
  public String test() {
    return "testing...";
  }
}

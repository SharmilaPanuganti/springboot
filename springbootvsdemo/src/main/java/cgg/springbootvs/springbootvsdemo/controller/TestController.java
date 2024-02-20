package cgg.springbootvs.springbootvsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TestController {

  @GetMapping("/start")
  public String test() {
    return "tesing...";
  }
}

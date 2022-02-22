package com.hanselone.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @GetMapping("hi")
    public String test(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello/hello";
    }
}

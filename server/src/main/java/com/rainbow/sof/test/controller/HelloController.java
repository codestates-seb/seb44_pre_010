package com.rainbow.sof.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String test(){
        return "자동 배포 성공!";
    }
}

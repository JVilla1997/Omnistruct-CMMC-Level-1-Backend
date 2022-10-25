package com.altf4omni.omnicmmc.controller;

import com.altf4omni.omnicmmc.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    private final HelloService helloService;

    public helloController(HelloService helloService){
        this.helloService = helloService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        System.out.println("Welcome to testing");
        return helloService.appendTest("Test for Omnistruct");
    }
}


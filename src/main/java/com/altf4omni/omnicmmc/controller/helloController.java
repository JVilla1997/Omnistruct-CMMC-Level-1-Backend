package com.altf4omni.omnicmmc.controller;

//import com.altf4omni.omnicmmc.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//Handle incoming rest requests to the Spring Boot app.
@RestController
public class helloController {

//    private final HelloService helloService;
//
//    public helloController(HelloService helloService){
//        this.helloService = helloService;
//    }
//
    @GetMapping("/helloWorld")
    public String helloWorld() {
        System.out.println("Welcome to testing");
        return "hello World!";
    }

    @PostMapping("/uploadPdf")
    public String uploadPdf(@RequestBody(required = true) String body) {
        return "Hello " + body;
    }
}


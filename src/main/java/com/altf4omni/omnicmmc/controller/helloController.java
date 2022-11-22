package com.altf4omni.omnicmmc.controller;

//import com.altf4omni.omnicmmc.service.HelloService;
import com.altf4omni.omnicmmc.dto.AnswerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//Handle incoming rest requests to the Spring Boot app.
@RestController
public class helloController {

//    private final HelloService helloService;
//
//    public helloController(HelloService helloService){
//        this.helloService = helloService;
//    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        System.out.println("Welcome to testing");
        return "hello World!";
    }

    @PostMapping("/uploadPdf")
    public String uploadPdf(@RequestBody(required = true) String body) {
        return "Hello " + body;
    }

    @PostMapping("/process")
    public void processFileAndEmail(
            @RequestBody AnswerRequest answerRequest) {
        // 1. Which email provider should I use? (Yahoo, Gmail, Outlook, etc)
        // 2. Use appropriate email service implementation
        // 3. What to return (?)
        answerRequest.getQuestions().forEach(a -> {
            System.out.println(a.getQuestionName());
            System.out.println(a.getQuestionAnswer());
        });
    }


}


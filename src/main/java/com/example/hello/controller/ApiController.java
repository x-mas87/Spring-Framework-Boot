package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //해당 class 는 rest api를 처리하는 controller로 등록됨
@RequestMapping("/api") // requestmapping은 uri을 지정해주는 annotation
public class ApiController {

    @GetMapping("hello") //http:localhost:8080/api/hello
    public String hello() {
        return "hello spring world";

    }

}

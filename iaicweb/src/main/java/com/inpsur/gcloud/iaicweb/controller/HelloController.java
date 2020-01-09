package com.inpsur.gcloud.iaicweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
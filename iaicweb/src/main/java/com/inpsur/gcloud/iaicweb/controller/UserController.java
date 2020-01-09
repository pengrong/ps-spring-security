package com.inpsur.gcloud.iaicweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/user")
    public Principal userinfo(Principal principal) {
        return principal;
    }

    @RequestMapping("/user/info")
    public Authentication user(Authentication authentication) {
        return authentication;
    }
}
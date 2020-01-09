package com.inspur.gcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
    public class TestController{
        @Autowired
        OAuth2RestTemplate oAuth2RestTemplate;

        @RequestMapping("/")
        public Principal home(Principal user) {
            return user;
        }
        @RequestMapping("/hello")
        public String hello() {
            return "hello world";
        }
        @RequestMapping("/me")
        public String me() {
            return "me " + oAuth2RestTemplate.getForEntity("http://localhost:8080/api/me", String.class).getBody();
        }

        @RequestMapping("/callback")
        public String callback(Principal user) {
            return "callback " + user.getName();
        }

    }
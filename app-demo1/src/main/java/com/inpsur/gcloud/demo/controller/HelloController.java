package com.inpsur.gcloud.demo.controller;

import com.inpsur.gcloud.ps.core.userdetails.PsUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }


    @RequestMapping("/user")
    //http://localhost:8082/user
    public PsUserDetails userinfo(String token) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("inspur:gcloud:session:sessions:" + token);
        if(!entries.isEmpty()){
            SecurityContextImpl securityContext = (SecurityContextImpl)entries.get("sessionAttr:SPRING_SECURITY_CONTEXT");
            PsUserDetails userDetails = (PsUserDetails)securityContext.getAuthentication().getPrincipal();

            return userDetails;
        }
        return null;
    }

}
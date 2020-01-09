package com.inpsur.gcloud.ps.controller;

import com.inpsur.gcloud.ps.core.userdetails.PsUserDetails;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CommonsLog
@RestController
public class HelloController {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @RequestMapping("/api/hello")
    public String apihello() {
        return "hello user";
    }

    @RequestMapping("/api/me")
    public PsUserDetails apiuser(Principal principal) {
        log.info(principal);

        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)principal;

        return (PsUserDetails)oAuth2Authentication.getUserAuthentication().getPrincipal();
    }


    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }


    @RequestMapping("/user/info")
    public Authentication userinfo(Authentication authentication) {
        return authentication;
    }


    @RequestMapping("/user/me")
    public Object user(SecurityContext securityContext) {
        OAuth2Authentication requestingUser = (OAuth2Authentication) securityContext.getAuthentication();
        Object principal = requestingUser.getUserAuthentication().getPrincipal();

        return principal;
    }
}
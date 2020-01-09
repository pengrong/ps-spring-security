package com.inpsur.gcloud.ps.core.controller;

import com.inpsur.gcloud.ps.core.properties.LoginType;
import com.inpsur.gcloud.ps.core.properties.PsSecurityProperties;
import com.inpsur.gcloud.ps.core.support.AuthenticationResponse;
import com.inpsur.gcloud.ps.core.support.ResponseCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthenticationController {
    private Logger log = LoggerFactory.getLogger(getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private PsSecurityProperties securityProperties;

    @RequestMapping("/authentication/requier")
    public AuthenticationResponse authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (LoginType.HTML.equals(securityProperties.getBrowser().getLoginType())) {
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
        }
        // 如果访问的rest接口，返回错误信息json
        return AuthenticationResponse.failed(ResponseCodeEnum.UNAUTHORIZED_ERROR);
    }
}

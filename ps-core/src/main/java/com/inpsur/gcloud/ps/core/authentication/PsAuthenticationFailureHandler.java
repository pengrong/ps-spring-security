package com.inpsur.gcloud.ps.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inpsur.gcloud.ps.core.properties.LoginType;
import com.inpsur.gcloud.ps.core.properties.PsSecurityProperties;
import com.inpsur.gcloud.ps.core.support.AuthenticationResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败后的处理器
 * 
 * @author pengrong
 *
 */
@CommonsLog
@Component("psAuthenticationFailureHandler")
public class PsAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Autowired
    private PsSecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        log.info("登录失败:" + exception.getMessage());
        // 根据application.properties中配置的登录类型决定返回类型
        if (LoginType.JSON == securityProperties.getBrowser().getLoginType()) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(AuthenticationResponse.failed("9999", exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }

}

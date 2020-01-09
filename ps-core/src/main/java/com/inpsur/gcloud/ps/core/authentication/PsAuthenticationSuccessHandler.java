package com.inpsur.gcloud.ps.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inpsur.gcloud.ps.core.properties.LoginType;
import com.inpsur.gcloud.ps.core.properties.PsSecurityProperties;
import com.inpsur.gcloud.ps.core.support.AuthenticationResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证成功后的处理器
 * 
 * @author pengrong
 *
 */
@CommonsLog
@Component("psAuthenticationSuccessHandler")
public class PsAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private PsSecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        log.info("登录成功");
        // 根据application.properties中配置的登录类型决定返回类型
        if (LoginType.JSON == securityProperties.getBrowser().getLoginType()) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(AuthenticationResponse.success(authentication)));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}

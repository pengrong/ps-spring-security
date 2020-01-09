package com.inpsur.gcloud.ps.sso.jis;

import com.inpsur.gcloud.ps.sso.util.JISUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CommonsLog
public class JISAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SECURITY_FORM_JIS_KEY = "ticket";

    private String jisParameter = SECURITY_FORM_JIS_KEY;
    private boolean postOnly = false;

    private JISUtil jisUtil;
    // ~ Constructors
    // ===================================================================================================

    public JISAuthenticationFilter() {
        super(new AntPathRequestMatcher("/sso/jis"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.debug("---------jis request method : " + request.getMethod());
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String ticket = obtainTicket(request);
        Map<String, Object> tokenInfo = jisUtil.getToken(ticket);
        // uuid	用户uuid
        String uuid = (String) tokenInfo.get("uuid");
        request.setAttribute("uuid", uuid);
        JISAuthenticationToken authRequest = new JISAuthenticationToken(uuid);
        // token	令牌
        authRequest.setToken((String) tokenInfo.get("token"));
        // usertype	用户类型，1:个人，2：法人，通过该值，第三方分别对应调用个人法人用户信息获取接口
        authRequest.setUsertype((String) tokenInfo.get("usertype"));
        // refreshtoken	刷新token（用于令牌失效时，进行刷新）
        authRequest.setRefreshtoken((String) tokenInfo.get("refreshtoken"));
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected String obtainTicket(HttpServletRequest request) {
        return request.getParameter(jisParameter);
    }

    protected void setDetails(HttpServletRequest request, JISAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setJisParameter(String jisParameter) {
        Assert.hasText(jisParameter, "Mobile parameter must not be empty or null");
        this.jisParameter = jisParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return jisParameter;
    }

    public void setJISUtil(JISUtil jisUtil) {
        this.jisUtil = jisUtil;
    }


}
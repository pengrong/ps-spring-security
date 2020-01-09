package com.inpsur.gcloud.ps.sso.jis;

import com.inpsur.gcloud.ps.core.userdetails.PsUserDetails;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//ProviderManager会查找所有的AuthenticationProvider，根据supports判断是否调用
@CommonsLog
public class JISAuthenticationProvider implements AuthenticationProvider {
    private JISUserDetailsService userDetailsService;


    public JISUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(JISUserDetailsService jisUserDetailsService) {
        this.userDetailsService = jisUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JISAuthenticationToken jisAuthenticationToken = (JISAuthenticationToken) authentication;
        String uuid = (String) jisAuthenticationToken.getPrincipal();
        log.info("根据 uuid=" + uuid + "验证用户信息");
        PsUserDetails user = userDetailsService.loadUserByUuid(uuid);
        if (user == null) {
            throw new InternalAuthenticationServiceException("找不到 uuid[" + uuid + "]用户");
        }
        user.setRefreshtoken(jisAuthenticationToken.getRefreshtoken());
        user.setToken(jisAuthenticationToken.getToken());
        user.setUsertype(jisAuthenticationToken.getUsertype());
        JISAuthenticationToken authRequest = new JISAuthenticationToken(user, user.getAuthorities());
        authRequest.setDetails(authentication.getDetails());
        return authRequest;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
        return JISAuthenticationToken.class.isAssignableFrom(authentication);
    }


}

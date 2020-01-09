package com.inpsur.gcloud.ps.sso.jis;

import com.inpsur.gcloud.ps.sso.util.JISUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JISCodeSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private JISUserDetailsService jisUserDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    JISUtil jisUtil;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JISAuthenticationFilter jisAuthenticationFilter = new JISAuthenticationFilter();
        jisAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        jisAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        jisAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        jisAuthenticationFilter.setJISUtil(jisUtil);
        JISAuthenticationProvider jisAuthenticationProvider = new JISAuthenticationProvider();
        jisAuthenticationProvider.setUserDetailsService(jisUserDetailsService);

        http.authenticationProvider(jisAuthenticationProvider)// 注册provide
                .addFilterAfter(jisAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

package com.inpsur.gcloud.ps.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 安全验证配置信息
 *
 * @author pengrong
 */
@Configuration
public class PsSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    @ConditionalOnBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
            http
                .formLogin()// 表单登录
                    .loginPage("/authentication/requier")// 登录页面，由controller转发到用户配置的登录页面
                    .successHandler(successHandler)// 认证成功后处理器
                    .failureHandler(authenticationFailureHandler)// 认证失败的处理器
                    .and()
                .authorizeRequests()// 所有请求都需要验证
                    .antMatchers("/authentication/requier").permitAll()// 不需要验证的
                    .anyRequest().authenticated()// 所有请求
                    .and()
                .csrf()
                    .disable()// 暂时关闭csrf验证
        ;
         // @formatter:on
    }
}

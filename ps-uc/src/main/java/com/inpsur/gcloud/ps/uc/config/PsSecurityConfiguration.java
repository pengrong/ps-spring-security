package com.inpsur.gcloud.ps.uc.config;

import com.inpsur.gcloud.ps.core.properties.PsSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private PsSecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permiturls = securityProperties.getIgnoringUrl().split(";");
        for (String url : permiturls) {
            http.authorizeRequests().antMatchers(url).permitAll();
        }
        // @formatter:off
            http
                .sessionManagement()
                    .sessionFixation()
                    .none()
                    .and()
                .userDetailsService(userDetailsService)
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .and()
                .formLogin()// 表单登录
                    .loginPage("/signin.html")// 登录页面，可以直接写htm
                    .loginPage("/authentication/requier")// 登录页面，由controller转发到用户配置的登录页面
                    .loginProcessingUrl("/authentication/form")// 登录页面提交的url
                    .successHandler(successHandler)// 认证成功后处理器
                    .failureHandler(authenticationFailureHandler)// 认证失败的处理器
                    .and()
                .authorizeRequests()// 所有请求都需要验证
                    .antMatchers("/", "/oauth/**","/authentication/requier", securityProperties.getBrowser().getLoginPage())
                    .permitAll()// 登录页面不需要
                    .anyRequest().authenticated()// 所有请求
                    .and().csrf().ignoringAntMatchers("/authentication/form")
                //.csrf().disable() // 暂时关闭csrf验证
        ;

         // @formatter:on
    }

}

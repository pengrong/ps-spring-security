package com.inspur.gcloud.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@Configuration
@EnableOAuth2Sso
//当WebSecurityConfigurerAdapter中加入@EnableOAuth2Sso注解，这个配置最后生成的过滤器链中会加入 oauth2 的过滤器 OAuth2ClientAuthenticationProcessingFilter
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
										  OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oauth2ClientContext);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
    	   http
			  //.antMatcher("/**")
			  .authorizeRequests()
				.antMatchers("/login","/login.jsp","/hello")
				.permitAll()
			  .anyRequest()
				.authenticated()
				.and().csrf().disable()
		   ;
    	// @formatter:on
	}

}
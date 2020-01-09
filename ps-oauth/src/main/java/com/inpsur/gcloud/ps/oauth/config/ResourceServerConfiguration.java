package com.inpsur.gcloud.ps.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String PS_RESOURCE_ID = "psout-api";


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(PS_RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
			http
				// Since we want the protected resources to be accessible in the UI as well we need
				// session creation to be allowed (it's disabled by default in 2.0.6)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.and()
                .requestMatchers().antMatchers("/api/**")
            .and()
				.authorizeRequests()
                    .antMatchers("/api/**")
                    .authenticated();

			// @formatter:on
    }

}
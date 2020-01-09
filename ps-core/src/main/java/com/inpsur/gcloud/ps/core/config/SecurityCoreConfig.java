package com.inpsur.gcloud.ps.core.config;

import com.inpsur.gcloud.ps.core.properties.PsSecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PsSecurityProperties.class)
public class SecurityCoreConfig {

}

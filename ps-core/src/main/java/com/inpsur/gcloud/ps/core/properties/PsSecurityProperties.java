package com.inpsur.gcloud.ps.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "inspur.gcloud.psout.security")
public class PsSecurityProperties {
    private String ignoringUrl = "";
    private BrowserProperties browser = new BrowserProperties();

}

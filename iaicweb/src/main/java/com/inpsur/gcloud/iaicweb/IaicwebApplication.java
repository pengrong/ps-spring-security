package com.inpsur.gcloud.iaicweb;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@CommonsLog
@SpringBootApplication(scanBasePackages = {"com.inpsur.gcloud"})
public class IaicwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaicwebApplication.class, args);
    }


}

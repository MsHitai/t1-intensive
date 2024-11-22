package com.t1.intensive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class IntensiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntensiveApplication.class, args);
    }

}

package com.speedybrand.rebate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(Application.PLS_BASE_PACKAGE)
public class Application {
    public static final String PLS_BASE_PACKAGE = "com.speedybrand.rebate";

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

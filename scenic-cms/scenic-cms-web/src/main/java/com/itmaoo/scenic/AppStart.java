package com.itmaoo.scenic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
@SpringBootApplication
@ImportResource(locations = {"classpath*:spring-basic.xml"})
public class AppStart extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppStart.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppStart.class);
    }
}

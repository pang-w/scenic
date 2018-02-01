package com.itmaoo.scenic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@ImportResource(locations = { "classpath*:spring-basic.xml" })
@EnableJpaRepositories(basePackages = "com.itmaoo.scenic.robot")
@EntityScan(basePackages = "com.itmaoo.scenic.robot.entity.po")
public class AppStart extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppStart.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppStart.class);
    } 
}

package com.itmaoo.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author mario
 *
 */
@SpringBootApplication
@ImportResource(locations = { "classpath*:spring-basic.xml" })
public class App extends SpringBootServletInitializer {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(App.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(App.class);
  }
  

}

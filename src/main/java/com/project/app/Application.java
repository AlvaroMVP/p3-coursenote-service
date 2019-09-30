package com.project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2WebFlux
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}

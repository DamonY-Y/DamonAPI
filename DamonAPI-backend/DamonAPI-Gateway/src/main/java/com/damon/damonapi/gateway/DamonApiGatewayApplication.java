package com.damon.damonapi.gateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication
public class DamonApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DamonApiGatewayApplication.class, args);
    }

}

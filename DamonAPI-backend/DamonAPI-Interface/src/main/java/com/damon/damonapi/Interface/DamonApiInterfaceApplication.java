package com.damon.damonapi.Interface;

import com.damon.damonapi.clientsdk.DamonAPIClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({DamonAPIClientConfig.class})
public class DamonApiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DamonApiInterfaceApplication.class, args);
    }

}

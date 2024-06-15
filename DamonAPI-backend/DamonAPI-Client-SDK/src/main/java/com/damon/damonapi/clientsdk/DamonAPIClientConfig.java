package com.damon.damonapi.clientsdk;

import com.damon.damonapi.clientsdk.client.DamonAPIClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("damonapi.client")
@Data
@ComponentScan
public class DamonAPIClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean(value = "damonAPI",name = "damonAPI")
    public DamonAPIClient damonAPIClient(){
        return new DamonAPIClient(accessKey,secretKey);
    }
}

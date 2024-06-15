package com.damon.damonapi.Interface;

import com.damon.damonapi.clientsdk.client.DamonAPIClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DamonApiInterfaceApplicationTests {

    @Resource
    private DamonAPIClient damonAPIClient;

    @Test
    void contextLoads() {
        String test = damonAPIClient.getNameByGet("test");
        System.out.println(test);
    }

}

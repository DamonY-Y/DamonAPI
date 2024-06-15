package com.damon.damonapi.Interface.controller;

import com.damon.damonapi.clientsdk.client.DamonAPIClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NameControllerTest {

    @Resource(type = DamonAPIClient.class)
    private DamonAPIClient damonAPIClient;

    @Test
    void Test(){
        String test = damonAPIClient.getNameByGet("test");
        System.out.println(test);
    }
}
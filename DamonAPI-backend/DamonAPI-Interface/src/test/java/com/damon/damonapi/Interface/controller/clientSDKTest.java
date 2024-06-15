package com.damon.damonapi.Interface.controller;

import com.damon.damonapi.Interface.DamonApiInterfaceApplication;
import com.damon.damonapi.clientsdk.client.DamonAPIClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {DamonApiInterfaceApplication.class})
public class clientSDKTest {

//    @Resource
//    private DamonAPIClient damonAPIClient;
//
//    @Test
//    void Test(){
//        String test = damonAPIClient.getNameByGet("test");
//        System.out.println(test);
//    }


    @Test
    void JustTest(){
        System.out.println("test");
    }
}

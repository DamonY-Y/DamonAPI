package com.damon.damonapi.Interface.controller;

import com.damon.damonapi.clientsdk.model.Dog;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name){
        return "Get: your name:" + name;
    }

    @GetMapping("/test")
    public String getTest(){
        return "test";
    }

    @PostMapping("/post")
    public String getNameByPost(String name){
        return "Post: your name:" + name;
    }

    @PostMapping("/json")
    public String getNameByJson(@RequestParam String name){
        return "Post Json: your name: " + name;
    }

    @PostMapping("/dog")
    public Dog getDog(@RequestBody Dog dog){
        System.out.println(dog);
        return dog;
    }
}

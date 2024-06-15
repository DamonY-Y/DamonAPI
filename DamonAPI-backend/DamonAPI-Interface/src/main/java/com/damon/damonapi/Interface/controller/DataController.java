package com.damon.damonapi.Interface.controller;

import com.damonapi.common.model.entity.SDG;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Description
 * @Author damon
 * @Date 2024/6/14 16:08
 */

@RestController
@RequestMapping("/SDG")
public class DataController {

    @PostMapping("/post")
    public HashMap testData(@RequestBody SDG sdg){
        System.out.println(sdg);
        HashMap<String, Object> res = new HashMap<>();
        res.put("level", sdg.getLevel());
        res.put("city", sdg.getCity());
        res.put("data","暫未公開");
        return res;
    }
}

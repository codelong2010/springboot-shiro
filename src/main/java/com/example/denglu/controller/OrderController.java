package com.example.denglu.controller;

import com.example.denglu.bean.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolong
 * @create 2020-07-08 20:19
 * @description
 */
@RestController
@RequestMapping("/authc")
public class OrderController {

    @RequestMapping("/video/playrecord")
    public JsonData playrecord(){
        Map<String,Object> map=new HashMap<>();
        map.put("火影忍者","第一集");
        map.put("海贼王","第四十八集");
        return JsonData.buildSucess(map);
    }
}

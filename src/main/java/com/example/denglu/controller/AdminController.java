package com.example.denglu.controller;

import com.example.denglu.bean.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolong
 * @create 2020-07-08 20:39
 * @description
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/findorder")
    public JsonData findorder(){
        Map<String,Object> map=new HashMap<>();
        map.put("购买红米手机",1000);
        map.put("购买苹果手机",4000);
        return JsonData.buildSucess(map);
    }
}

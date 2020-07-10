package com.example.denglu.controller;

import com.example.denglu.bean.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaolong
 * @create 2020-07-08 20:53
 * @description
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @RequestMapping("/update")
    public JsonData update(){
        return JsonData.buildSucess("更新成功");
    }

}

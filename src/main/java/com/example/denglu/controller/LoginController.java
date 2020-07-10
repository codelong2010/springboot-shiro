package com.example.denglu.controller;

import com.alibaba.fastjson.JSON;
import com.example.denglu.bean.JsonUtils;
import com.example.denglu.bean.Login;
import com.example.denglu.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaolong
 * @create 2020-07-01 10:38
 * @description
 */
@RestController
public class LoginController {
    @Autowired
    LoginMapper loginMapper;

    @RequestMapping("/denglu")
    public Object login(String name,String pwd){
        Login login=loginMapper.findone(name);
        if(login!=null){
            if(pwd.equals(login.getPassword())){
                return JSON.toJSONString(new JsonUtils(0,"登录成功"));
            }
            else{
                return JSON.toJSONString(new JsonUtils(-1,"密码错误"));
            }
        }
        else{
            return JSON.toJSONString(new JsonUtils(-1,"用户名不存在"));
        }
    }
}

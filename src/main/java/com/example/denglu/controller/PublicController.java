package com.example.denglu.controller;

import com.example.denglu.bean.JsonData;
import com.example.denglu.bean.UserQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaolong
 * @create 2020-07-08 16:08
 * @description
 */
@RestController
@RequestMapping("/pub")
public class PublicController {
    @RequestMapping("/need_login")
    public JsonData needlogin(){
        return JsonData.buildSucess(-2,"请先登录");
    }

    @RequestMapping("/not_permit")
    public JsonData notpermit(){
        return JsonData.buildSucess(-3,"没有权限");
    }

    @RequestMapping("/index")
    public JsonData index(){
        List<String> videolist=new ArrayList<>();
        videolist.add("假面骑士");
        videolist.add("奥特曼");
        return JsonData.buildSucess(videolist);
    }

    @PostMapping("/login")
    public JsonData login(@RequestBody UserQuery userQuery, HttpServletRequest request, HttpServletResponse response){
        //获取subject操作主体
        Subject subject= SecurityUtils.getSubject();
        Map<String,Object> map=new HashMap<>();
        //获取登录输入的账号密码
        UsernamePasswordToken token=new UsernamePasswordToken(userQuery.getUsername(),userQuery.getPassword());
        try{
            //使用subject主体的login方法登录
            subject.login(token);
            map.put("msg","登录成功");
            map.put("session_id",request.getSession().getId());
            return JsonData.buildSucess(map);
        }catch (UnknownAccountException e){
            map.put("msg","用户名不存在");
            return JsonData.buildError(map);
        }catch (IncorrectCredentialsException e){
            map.put("msg","密码错误");
            return JsonData.buildError(map);
        }
    }
}

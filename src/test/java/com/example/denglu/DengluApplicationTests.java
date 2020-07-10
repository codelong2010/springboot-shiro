package com.example.denglu;

import com.example.denglu.bean.Permission;
import com.example.denglu.bean.Role;
import com.example.denglu.dao.LoginMapper;
import com.example.denglu.dao.PermissionMapper;
import com.example.denglu.dao.RoleMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@MapperScan("com.example.denglu.dao")
class DengluApplicationTests {

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RoleMapper roleMapper;

    @Test
    void contextLoads() {
//        System.out.println(loginMapper.findone("zhangsan"));
        System.out.println(permissionMapper.findPermissionListByRoleId(3));
        System.out.println(roleMapper.findRoleListByid(5));
    }

    @Test
    void test(){
        String name="md5";
        String pwd="123456";
        Object result=new SimpleHash(name,pwd,null,2);
        System.out.println(result);
    }

}

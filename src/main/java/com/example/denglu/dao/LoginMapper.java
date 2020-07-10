package com.example.denglu.dao;

import com.example.denglu.bean.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author xiaolong
 * @create 2020-07-01 10:28
 * @description
 */

@Repository
public interface LoginMapper {

    @Select("select * from login where username = #{name}")
    public Login findone(String name);

    @Select("select * from login where id = #{id}")
    public Login findbyid(int id);

    @Select("select * from login where username = #{name} and password = #{pwd}")
    public Login findbynameandpwd(String name,String pwd);
}

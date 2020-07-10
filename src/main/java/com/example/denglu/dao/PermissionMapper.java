package com.example.denglu.dao;

import com.example.denglu.bean.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaolong
 * @create 2020-07-07 23:03
 * @description
 */
@Repository
public interface PermissionMapper {

    @Select("SELECT permission.id,permission.`name`,permission.url " +
            "FROM role_permission LEFT JOIN permission ON role_permission.p_id=permission.id " +
            "WHERE role_permission.r_id= #{roleid}")
    public List<Permission> findPermissionListByRoleId(int roleid);
}

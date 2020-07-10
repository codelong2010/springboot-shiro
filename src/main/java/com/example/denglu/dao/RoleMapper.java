package com.example.denglu.dao;

import com.example.denglu.bean.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaolong
 * @create 2020-07-07 23:14
 * @description
 */
@Repository
public interface RoleMapper {

    @Select("SELECT role.id,role.name,role.description " +
            "FROM user_role LEFT JOIN role on user_role.role_id=role.id WHERE user_role.user_id= #{userid}")
    @Results(
            value = {
                    @Result(id = true,property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "permissionList",column = "id",
                            many = @Many(select = "com.example.denglu.dao.PermissionMapper.findPermissionListByRoleId",fetchType = FetchType.DEFAULT))
            }
    )
    public List<Role> findRoleListByid(int userid);
}

package com.example.denglu.bean;

import com.example.denglu.dao.LoginMapper;
import com.example.denglu.dao.PermissionMapper;
import com.example.denglu.dao.RoleMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolong
 * @create 2020-07-06 16:17
 * @description shiro自定义Realm
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得登录账号的账号名称
        String name=(String)principalCollection.getPrimaryPrincipal();
        //根据账号查找账号对应的角色和权限
        Login login=loginMapper.findone(name);
        List<Role> rolelist=new ArrayList<>();
        List<Permission> permissionList=new ArrayList<>();
        List<String> roles=new ArrayList<>();
        List<String> permissions=new ArrayList<>();
        //通过mybatis接口获取角色和权限
        rolelist=roleMapper.findRoleListByid(login.getId());
        for(Role role:rolelist){
            permissionList=permissionMapper.findPermissionListByRoleId((int)role.getId());
            for(Permission permission:permissionList){
                permissions.add(permission.getName());
            }
            roles.add(role.getName());
        }
        //将登录账号对应的角色和权限返回给customrealm
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        System.out.println("roles"+roles);
        System.out.println("permissions"+permissions);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获得登录账号的账号名称
        String username=(String)authenticationToken.getPrincipal();
        Login login=loginMapper.findone(username);
        String password=login.getPassword();
        //判断密码是否为空
        if(password==null||"".equals(password)){
            return null;
        }
        //把正确的账号密码返回给customrealm
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(username,password,getName());
        return simpleAuthenticationInfo;
    }
}

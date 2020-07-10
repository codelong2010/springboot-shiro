package com.example.denglu.bean;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiaolong
 * @create 2020-07-08 10:43
 * @description shiroconfig工具类
 */
@Configuration
public class ShiroConfig {

    //设置shiro拦截器
    @Bean
    public ShiroFilterFactoryBean ShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //绑定securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录不成功时的返回路径
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");
        //设置登录成功时的返回路径
        shiroFilterFactoryBean.setSuccessUrl("/");
        //设置权限不足时的返回路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");
        //构建一个map集合存放拦截器规则
        //必须使用LinkedHashMap，因为这是有序的，过滤规则是从上到下匹配。
        Map<String,String> filterChainDefintionMap=new LinkedHashMap<>();
        //用户退出
        filterChainDefintionMap.put("/logout","logout");
        //游客模式，表示这里的请求任何人都能访问
        filterChainDefintionMap.put("/pub/**","anon");
        //需要先登录，只有登录了才有资格访问这里的请求
        filterChainDefintionMap.put("/authc/**","authc");
        //需要符合角色，只有拥有这个角色身份的人才可以访问下列路径
        filterChainDefintionMap.put("/admin/**","roles[admin]");
        //需要符合权限,只有拥有这个权限的人可以访问下列路径
        filterChainDefintionMap.put("/video/update","perms[video_update]");
        //防止有路径遗漏
        filterChainDefintionMap.put("/**","authc");
        //绑定过滤器规则的map
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefintionMap);
        return shiroFilterFactoryBean;
    }

    //设置shiro的securitymanager环境
    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager defaultSecurityManager=new DefaultWebSecurityManager();
        //绑定sessionmanager
        defaultSecurityManager.setSessionManager(sessionManager());
        //绑定realm
        defaultSecurityManager.setRealm(customRealm());
        return defaultSecurityManager;
    }

    //设置realm
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm=new CustomRealm();
        //绑定密码使用的加密方式
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    //设置sessionmanager
    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager=new CustomSessionManager();
        //设置token过期时间为20s
        customSessionManager.setGlobalSessionTimeout(200000);
        return customSessionManager;
    }

    //设置密码使用的加密方式
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        //设置使用的加密方式是md5
        credentialsMatcher.setHashAlgorithmName("md5");
        //加密2次
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }
}

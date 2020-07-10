package com.example.denglu.bean;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author xiaolong
 * @create 2020-07-08 11:15
 * @description shiro自定义的sessionmanager
 */
public class CustomSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION="token";
    public CustomSessionManager(){
        super();
    }

    //重写获得sessionid的方法
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //前端请求头必须传入token值
        String sessionid=WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有token值，则把token值设置为sessionid
        if(!StringUtils.isEmpty(sessionid)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,sessionid);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return sessionid;
        }else {
            //如果没有，则按照原来的从cookie中读取sessionid
            return super.getSessionId(request,response);
        }
    }

}

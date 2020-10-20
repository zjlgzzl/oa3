/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.interceptor.UserValidInterceptor
 *  com.opensymphony.xwork2.ActionInvocation
 *  com.opensymphony.xwork2.interceptor.Interceptor
 *  javax.servlet.http.HttpServletRequest
 *  org.apache.struts2.ServletActionContext
 */
package com.common.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class UserValidInterceptor
implements Interceptor {
    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = (String)request.getSession().getAttribute("userName");
        String password = (String)request.getSession().getAttribute("passWord");
        if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
            return "login";
        }
        return invocation.invoke();
    }
}


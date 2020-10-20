/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.CookieAction
 *  javax.servlet.http.Cookie
 *  javax.servlet.http.HttpServletRequest
 *  org.apache.struts2.ServletActionContext
 */
package com.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class CookieAction {
    HttpServletRequest request = ServletActionContext.getRequest();

    public void getCookie() {
        Cookie[] cookies = this.request.getCookies();
        boolean found = false;
        String userName = null;
        if (cookies != null) {
            Cookie[] arrcookie = cookies;
            int n = cookies.length;
            for (int i = 0; i < n; ++i) {
                Cookie cookie = arrcookie[i];
                if (!"cookie_uname".equals(cookie.getName())) continue;
                userName = cookie.getValue();
                found = true;
                break;
            }
        }
        if (found) {
            this.request.setAttribute("cookName", userName);
        }
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpSession
 *  org.apache.struts2.ServletActionContext
 */
package com.common.auth.action;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class UserInfo
extends ActionSupport {
    public static int getUserId() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return Integer.parseInt(session.getAttribute("userID").toString());
    }

    public static String getUserName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return session.getAttribute("userName").toString();
    }

    public static int getUserAdminflg() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return Integer.parseInt(session.getAttribute("adminFlg").toString());
    }

    public static int getUppId() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return Integer.parseInt(session.getAttribute("uppId").toString());
    }
}


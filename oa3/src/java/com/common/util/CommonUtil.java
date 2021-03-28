/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.CommonUtil
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpSession
 *  org.apache.struts2.ServletActionContext
 */
package com.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class CommonUtil {
    public static Timestamp getDatetime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(formatter.format(new Date()));
    }

    public static int getUserId() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return Integer.parseInt(session.getAttribute("userID").toString());
    }

    public static short getFun226() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return Short.parseShort(session.getAttribute("fun226").toString());
    }

    public static int getEmpId() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return Integer.parseInt(session.getAttribute("empId").toString());
    }

    public static int getFun(int funId) {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        return Integer.parseInt(session.getAttribute("fun" + String.valueOf(funId)).toString());
    }
}


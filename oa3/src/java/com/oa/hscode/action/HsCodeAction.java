/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.THscode
 *  com.common.util.PageBean
 *  com.oa.hscode.action.HsCodeAction
 *  com.oa.hscode.service.HsCodeService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.hscode.action;

import com.common.entity.THscode;
import com.common.util.PageBean;
import com.oa.hscode.service.HsCodeService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class HsCodeAction
extends ActionSupport {
    private HsCodeService hsCodeService;
    private THscode entity;
    private THscode query;
    private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.hsCodeService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.hsCodeService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String getList2() {
        List list = this.hsCodeService.getList2(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.hsCodeService.getPages2(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addHscode() {
        return "success";
    }

    public String saveHscode() {
        int count = this.hsCodeService.checkDup(this.entity.getCDescription(), this.entity.getCId());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"Description÷ÿ∏¥");
            return "input";
        }
        try {
            this.hsCodeService.saveEntity(this.entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String editHscode() {
        this.entity = this.hsCodeService.getEntityById((Serializable)Integer.valueOf(this.id));
        return "success";
    }

    public String deleteHscode() {
        try {
            this.hsCodeService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public void setHsCodeService(HsCodeService hsCodeService) {
        this.hsCodeService = hsCodeService;
    }

    public int getPageNow() {
        return this.pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public THscode getEntity() {
        return this.entity;
    }

    public void setEntity(THscode entity) {
        this.entity = entity;
    }

    public THscode getQuery() {
        return this.query;
    }

    public void setQuery(THscode query) {
        this.query = query;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


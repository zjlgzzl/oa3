/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TSaler
 *  com.common.util.PageBean
 *  com.oa.base.action.SalerAction
 *  com.oa.base.service.SalerService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.base.action;

import com.common.entity.TSaler;
import com.common.util.PageBean;
import com.oa.base.service.SalerService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class SalerAction
extends ActionSupport {
    private SalerService salerService;
    private TSaler saler;
    private TSaler query;
    private Integer id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.salerService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("salerList", (Object)list);
        PageBean pagebean = this.salerService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addSaler() {
        return "success";
    }

    public String saveSaler() {
        if (this.saler.getCName() == null || "".equals(this.saler.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"业务员姓名不能为空");
            return "input";
        }
        int count = this.salerService.checkName((Serializable)this.saler.getCId(), this.saler.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"业务员姓名重复");
            return "input";
        }
        try {
            this.salerService.saveEntity(this.saler);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editSaler() {
        this.saler = this.salerService.getEntityById((Serializable)this.id);
        return "success";
    }

    public String deleteSaler() {
        int count = this.salerService.checkRef((Serializable)this.id);
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"业务员已经被使用，不允许删除");
            return "input";
        }
        try {
            this.salerService.deleteEntity((Serializable)this.id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TSaler getSaler() {
        return this.saler;
    }

    public void setSaler(TSaler saler) {
        this.saler = saler;
    }

    public TSaler getQuery() {
        return this.query;
    }

    public void setQuery(TSaler query) {
        this.query = query;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setSalerService(SalerService salerService) {
        this.salerService = salerService;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerModel
 *  com.common.util.PageBean
 *  com.oa.container.action.ConModelAction
 *  com.oa.container.service.ConModelService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.container.action;

import com.common.entity.TContainerModel;
import com.common.util.PageBean;
import com.oa.container.service.ConModelService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ConModelAction
extends ActionSupport {
    private ConModelService conModelService;
    private TContainerModel query;
    private TContainerModel conModel;
    private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.conModelService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("conModelList", (Object)list);
        PageBean pagebean = this.conModelService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addConModel() {
        return "success";
    }

    public String saveConModel() {
        if (this.conModel.getCCode() == null || "".equals(this.conModel.getCCode().trim())) {
            this.request.setAttribute("errInfo", (Object)"货柜型号不能为空");
            return "input";
        }
        int count = this.conModelService.checkCode((Serializable)this.conModel.getCId(), this.conModel.getCCode());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"货柜型号重复");
            return "input";
        }
        try {
            this.conModelService.saveEntity(this.conModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editConModel() {
        this.conModel = this.conModelService.getEntityById((Serializable)Integer.valueOf(this.id));
        return "success";
    }

    public String deleteConModel() {
        int count = this.conModelService.checkRef((Serializable)Integer.valueOf(this.id));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"货柜型号已经被货柜编号引用，不允许删除");
            return "input";
        }
        try {
            this.conModelService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TContainerModel getQuery() {
        return this.query;
    }

    public void setQuery(TContainerModel query) {
        this.query = query;
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

    public void setConModelService(ConModelService conModelService) {
        this.conModelService = conModelService;
    }

    public TContainerModel getConModel() {
        return this.conModel;
    }

    public void setConModel(TContainerModel conModel) {
        this.conModel = conModel;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


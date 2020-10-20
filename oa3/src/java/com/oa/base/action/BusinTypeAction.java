/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusinType
 *  com.common.util.PageBean
 *  com.oa.base.action.BusinTypeAction
 *  com.oa.base.service.BusinTypeService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.base.action;

import com.common.entity.TBusinType;
import com.common.util.PageBean;
import com.oa.base.service.BusinTypeService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class BusinTypeAction
extends ActionSupport {
    private BusinTypeService businTypeService;
    private TBusinType query;
    private TBusinType businType;
    private int typeId;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.businTypeService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("businTypeList", (Object)list);
        PageBean pagebean = this.businTypeService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addBusinType() {
        return "success";
    }

    public String saveBusinType() {
        if (this.businType.getCName() == null || "".equals(this.businType.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"服务单类型名称不能为空");
            return "input";
        }
        int count = this.businTypeService.checkName((Serializable)this.businType.getCId(), this.businType.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"服务单类型名称重复");
            return "input";
        }
        try {
            this.businTypeService.saveEntity(this.businType);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editBusinType() {
        this.businType = this.businTypeService.getEntityById((Serializable)Integer.valueOf(this.typeId));
        return "success";
    }

    public String deleteBusinType() {
        int count = this.businTypeService.checkRef((Serializable)Integer.valueOf(this.typeId));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"服务单类型已经被使用，不允许删除");
            return "input";
        }
        try {
            this.businTypeService.deleteEntity((Serializable)Integer.valueOf(this.typeId));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidBusinType() {
        try {
            this.businTypeService.updateEntityState((Serializable)Integer.valueOf(this.typeId), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TBusinType getQuery() {
        return this.query;
    }

    public void setQuery(TBusinType query) {
        this.query = query;
    }

    public TBusinType getBusinType() {
        return this.businType;
    }

    public void setBusinType(TBusinType businType) {
        this.businType = businType;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public void setBusinTypeService(BusinTypeService businTypeService) {
        this.businTypeService = businTypeService;
    }
}


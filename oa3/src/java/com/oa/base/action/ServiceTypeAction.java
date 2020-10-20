/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TServiceType
 *  com.common.util.PageBean
 *  com.oa.base.action.ServiceTypeAction
 *  com.oa.base.service.ServiceTypeService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.base.action;

import com.common.entity.TServiceType;
import com.common.util.PageBean;
import com.oa.base.service.ServiceTypeService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ServiceTypeAction
extends ActionSupport {
    private ServiceTypeService serviceTypeService;
    private TServiceType query;
    private TServiceType stype;
    private int stypeId;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.serviceTypeService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("stypeList", (Object)list);
        PageBean pagebean = this.serviceTypeService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addServiceType() {
        return "success";
    }

    public String saveServiceType() {
        if (this.stype.getCName() == null || "".equals(this.stype.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"服务类型名称不能为空");
            return "input";
        }
        int count = this.serviceTypeService.checkName((Serializable)this.stype.getCId(), this.stype.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"服务类型名称重复");
            return "input";
        }
        try {
            this.serviceTypeService.saveEntity(this.stype);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editServiceType() {
        this.stype = this.serviceTypeService.getEntityById((Serializable)Integer.valueOf(this.stypeId));
        return "success";
    }

    public String deleteServiceType() {
        int count = this.serviceTypeService.checkRef((Serializable)Integer.valueOf(this.stypeId));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"服务类型下已经包含服务项目，不允许删除");
            return "input";
        }
        try {
            this.serviceTypeService.deleteEntity((Serializable)Integer.valueOf(this.stypeId));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidServiceType() {
        try {
            this.serviceTypeService.updateEntityState((Serializable)Integer.valueOf(this.stypeId), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
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

    public void setServiceTypeService(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    public TServiceType getQuery() {
        return this.query;
    }

    public void setQuery(TServiceType query) {
        this.query = query;
    }

    public TServiceType getStype() {
        return this.stype;
    }

    public void setStype(TServiceType stype) {
        this.stype = stype;
    }

    public int getStypeId() {
        return this.stypeId;
    }

    public void setStypeId(int stypeId) {
        this.stypeId = stypeId;
    }
}


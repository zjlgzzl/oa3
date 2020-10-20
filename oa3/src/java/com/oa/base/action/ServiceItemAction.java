/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TServiceItem
 *  com.common.entity.TServiceType
 *  com.common.util.PageBean
 *  com.oa.base.action.ServiceItemAction
 *  com.oa.base.service.ServiceItemService
 *  com.oa.base.service.ServiceTypeService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.base.action;

import com.common.entity.TServiceItem;
import com.common.entity.TServiceType;
import com.common.util.PageBean;
import com.oa.base.service.ServiceItemService;
import com.oa.base.service.ServiceTypeService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ServiceItemAction
extends ActionSupport {
    private ServiceTypeService serviceTypeService;
    private ServiceItemService serviceItemService;
    private TServiceItem query;
    private TServiceItem sitem;
    private int sitemId;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.serviceItemService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("sitemList", (Object)list);
        PageBean pagebean = this.serviceItemService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public List<TServiceType> getTypeList() {
        return this.serviceTypeService.getValidList();
    }

    public String addServiceItem() {
        this.request.setAttribute("typeList", (Object)this.getTypeList());
        return "success";
    }

    public String saveServiceItem() {
        if (this.sitem.getTServiceType().getCId() == 0) {
            this.request.setAttribute("errInfo", (Object)"服务类型名称不能为空");
            this.request.setAttribute("typeList", (Object)this.getTypeList());
            return "input";
        }
        if (this.sitem.getCName() == null || "".equals(this.sitem.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"服务项目名称不能为空");
            this.request.setAttribute("typeList", (Object)this.getTypeList());
            return "input";
        }
        int count = this.serviceItemService.checkName((Serializable)this.sitem.getTServiceType().getCId(), (Serializable)this.sitem.getCId(), this.sitem.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"服务项目名称重复");
            this.request.setAttribute("typeList", (Object)this.getTypeList());
            return "input";
        }
        try {
            this.serviceItemService.saveEntity(this.sitem);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editServiceItem() {
        this.sitem = this.serviceItemService.getEntityById((Serializable)Integer.valueOf(this.sitemId));
        return "success";
    }

    public String deleteServiceItem() {
        int count = this.serviceItemService.checkRef((Serializable)Integer.valueOf(this.sitemId));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"服务项目已经被使用，不允许删除");
            return "input";
        }
        try {
            this.serviceItemService.deleteEntity((Serializable)Integer.valueOf(this.sitemId));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidServiceItem() {
        try {
            this.serviceItemService.updateEntityState((Serializable)Integer.valueOf(this.sitemId), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TServiceItem getQuery() {
        return this.query;
    }

    public void setQuery(TServiceItem query) {
        this.query = query;
    }

    public TServiceItem getSitem() {
        return this.sitem;
    }

    public void setSitem(TServiceItem sitem) {
        this.sitem = sitem;
    }

    public int getSitemId() {
        return this.sitemId;
    }

    public void setSitemId(int sitemId) {
        this.sitemId = sitemId;
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

    public void setServiceItemService(ServiceItemService serviceItemService) {
        this.serviceItemService = serviceItemService;
    }

    public void setServiceTypeService(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }
}


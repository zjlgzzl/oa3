/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerInfo
 *  com.common.util.PageBean
 *  com.oa.container.action.ConInfoAction
 *  com.oa.container.service.ConInfoService
 *  com.oa.container.service.ConModelService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.container.action;

import com.common.entity.TContainerInfo;
import com.common.util.PageBean;
import com.oa.container.service.ConInfoService;
import com.oa.container.service.ConModelService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class ConInfoAction
extends ActionSupport {
    private ConInfoService conInfoService;
    private ConModelService conModelService;
    private TContainerInfo query;
    private TContainerInfo conInfo;
    private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.conInfoService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("conInfoList", (Object)list);
        PageBean pagebean = this.conInfoService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public void getConModelList() {
        List conModelList = this.conModelService.getValidList();
        this.request.setAttribute("conModelList", (Object)conModelList);
    }

    public String addConInfo() {
        this.getConModelList();
        return "success";
    }

    public String saveConInfo() {
        if (this.conInfo.getCCode() == null || "".equals(this.conInfo.getCCode().trim())) {
            this.getConModelList();
            this.request.setAttribute("errInfo", (Object)"货柜编号不能为空");
            return "input";
        }
        if (this.conInfo.getTContainerModel().getCId() == -1) {
            this.getConModelList();
            this.request.setAttribute("errInfo", (Object)"货柜型号不能为空");
            return "input";
        }
        if (this.conInfo.getCPrice() == null || this.conInfo.getCPrice() <= 0.0) {
            this.getConModelList();
            this.request.setAttribute("errInfo", (Object)"成本价格不能为空");
            return "input";
        }
        int count = this.conInfoService.checkCode((Serializable)this.conInfo.getCId(), this.conInfo.getCCode());
        if (count > 0) {
            this.getConModelList();
            this.request.setAttribute("errInfo", (Object)"货柜编号重复");
            return "input";
        }
        try {
            this.conInfoService.saveEntity(this.conInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editConInfo() {
        this.getConModelList();
        this.conInfo = this.conInfoService.getEntityById((Serializable)Integer.valueOf(this.id));
        return "success";
    }

    public String deleteConInfo() {
        int count = this.conInfoService.checkRef((Serializable)Integer.valueOf(this.id));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"货柜已经产生进出登记，不允许删除");
            return "input";
        }
        try {
            this.conInfoService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TContainerInfo getQuery() {
        return this.query;
    }

    public void setQuery(TContainerInfo query) {
        this.query = query;
    }

    public TContainerInfo getConInfo() {
        return this.conInfo;
    }

    public void setConInfo(TContainerInfo conInfo) {
        this.conInfo = conInfo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public void setConInfoService(ConInfoService conInfoService) {
        this.conInfoService = conInfoService;
    }

    public void setConModelService(ConModelService conModelService) {
        this.conModelService = conModelService;
    }
}


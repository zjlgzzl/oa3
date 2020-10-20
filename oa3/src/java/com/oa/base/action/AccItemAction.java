/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TAccItem
 *  com.common.util.PageBean
 *  com.oa.base.action.AccItemAction
 *  com.oa.base.service.AccItemService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.base.action;

import com.common.entity.TAccItem;
import com.common.util.PageBean;
import com.oa.base.service.AccItemService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class AccItemAction
extends ActionSupport {
    private AccItemService accItemService;
    private TAccItem query;
    private TAccItem acc;
    private int accId;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.accItemService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("accList", (Object)list);
        PageBean pagebean = this.accItemService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addAccItem() {
        return "success";
    }

    public String saveAccItem() {
        if (this.acc.getCName() == null || "".equals(this.acc.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"财务科目名称不能为空");
            return "input";
        }
        int count = this.accItemService.checkName((Serializable)this.acc.getCId(), this.acc.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"财务科目名称重复");
            return "input";
        }
        try {
            this.accItemService.saveEntity(this.acc);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editAccItem() {
        this.acc = this.accItemService.getEntityById((Serializable)Integer.valueOf(this.accId));
        return "success";
    }

    public String deleteAccItem() {
        int count = this.accItemService.checkRef((Serializable)Integer.valueOf(this.accId));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"财务科目已经被使用，不允许删除");
            return "input";
        }
        try {
            this.accItemService.deleteEntity((Serializable)Integer.valueOf(this.accId));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidAccItem() {
        try {
            this.accItemService.updateEntityState((Serializable)Integer.valueOf(this.accId), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TAccItem getQuery() {
        return this.query;
    }

    public void setQuery(TAccItem query) {
        this.query = query;
    }

    public TAccItem getAcc() {
        return this.acc;
    }

    public void setAcc(TAccItem acc) {
        this.acc = acc;
    }

    public int getAccId() {
        return this.accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
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

    public void setAccItemService(AccItemService accItemService) {
        this.accItemService = accItemService;
    }
}


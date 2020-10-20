/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TRate
 *  com.common.util.PageBean
 *  com.oa.rate.action.RateAction
 *  com.oa.rate.service.RateService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.rate.action;

import com.common.entity.TRate;
import com.common.util.PageBean;
import com.oa.rate.service.RateService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class RateAction
extends ActionSupport {
    private RateService rateService;
    private TRate rate;
    private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.rateService.getList(null, this.pageNow, this.pageSize);
        this.request.setAttribute("rateList", (Object)list);
        PageBean pagebean = this.rateService.getPages(null, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addRate() {
        return "success";
    }

    public String saveRate() {
        if (this.rate.getCRate() == null) {
            this.request.setAttribute("errInfo", (Object)"税率不能为空");
            return "input";
        }
        int count = this.rateService.checkName((Serializable)this.rate.getCId(), this.rate.getCRate().doubleValue());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"税率重复");
            return "input";
        }
        try {
            this.rateService.saveEntity(this.rate);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editRate() {
        this.rate = this.rateService.getEntityById((Serializable)Integer.valueOf(this.id));
        return "success";
    }

    public String deleteRate() {
        int count = this.rateService.checkRef((Serializable)Integer.valueOf(this.id));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"费率已经使用，不允许删除！");
            return "input";
        }
        try {
            this.rateService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TRate getRate() {
        return this.rate;
    }

    public void setRate(TRate rate) {
        this.rate = rate;
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

    public void setRateService(RateService rateService) {
        this.rateService = rateService;
    }
}


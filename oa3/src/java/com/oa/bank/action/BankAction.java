/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBank
 *  com.common.util.PageBean
 *  com.oa.bank.action.BankAction
 *  com.oa.bank.service.BankService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.bank.action;

import com.common.entity.TBank;
import com.common.util.PageBean;
import com.oa.bank.service.BankService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class BankAction
extends ActionSupport {
    private BankService bankService;
    private TBank bank;
    private TBank query;
    private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.bankService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("bankList", (Object)list);
        PageBean pagebean = this.bankService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addBank() {
        return "success";
    }

    public String saveBank() {
        if (this.bank.getCName() == null || "".equals(this.bank.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"银行名称不能为空");
            return "input";
        }
        int count = this.bankService.checkName((Serializable)this.bank.getCId(), this.bank.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"银行名称重复");
            return "input";
        }
        try {
            this.bankService.saveEntity(this.bank);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editBank() {
        this.bank = this.bankService.getEntityById((Serializable)Integer.valueOf(this.id));
        return "success";
    }

    public String deleteBank() {
        int count = this.bankService.checkRef((Serializable)Integer.valueOf(this.id));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"银行已经发生进出帐，不允许删除！");
            return "input";
        }
        try {
            this.bankService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TBank getBank() {
        return this.bank;
    }

    public void setBank(TBank bank) {
        this.bank = bank;
    }

    public TBank getQuery() {
        return this.query;
    }

    public void setQuery(TBank query) {
        this.query = query;
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

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }
}


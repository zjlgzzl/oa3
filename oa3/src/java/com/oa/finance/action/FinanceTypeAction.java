/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.emp.service.EmpService
 *  com.common.entity.TFinancetype
 *  com.common.util.PageBean
 *  com.oa.finance.action.FinanceTypeAction
 *  com.oa.finance.service.FinanceTypeService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.finance.action;

import com.common.emp.service.EmpService;
import com.common.entity.TFinancetype;
import com.common.util.PageBean;
import com.oa.finance.service.FinanceTypeService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class FinanceTypeAction
extends ActionSupport {
    private FinanceTypeService financeTypeService;
    private EmpService empService;
    private TFinancetype query;
    private TFinancetype financeType;
    private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    private Integer[] userIds;

    public String getList() {
        List list = this.financeTypeService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("financeTypeList", (Object)list);
        PageBean pagebean = this.financeTypeService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public void init() {
        this.request.setAttribute("empList", (Object)this.empService.getValidAllList());
        if (this.financeType != null && this.financeType.getCId() != null && this.financeType.getCId() > 0) {
            List userIdList = this.financeTypeService.getUserList(this.financeType.getCId().intValue());
            this.request.setAttribute("userIdList", (Object)userIdList);
        }
    }

    public String addFinanceType() {
        this.init();
        return "success";
    }

    public String saveFinanceType() {
        if (this.financeType.getCName() == null || "".equals(this.financeType.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"支出类型名称不能为空");
            this.init();
            return "input";
        }
        int count = this.financeTypeService.checkName((Serializable)this.financeType.getCId(), this.financeType.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"支出类型名称重复");
            this.init();
            return "input";
        }
        try {
            this.financeTypeService.saveEntity(this.financeType, this.userIds);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.init();
            return "error";
        }
        return "success";
    }

    public String editFinanceType() {
        this.financeType = this.financeTypeService.getEntityById((Serializable)Integer.valueOf(this.id));
        this.init();
        return "success";
    }

    public String deleteFinanceType() {
        int count = this.financeTypeService.checkRef((Serializable)Integer.valueOf(this.id));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"支出类型已经被引用，不允许删除！");
            return "input";
        }
        try {
            this.financeTypeService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TFinancetype getQuery() {
        return this.query;
    }

    public void setQuery(TFinancetype query) {
        this.query = query;
    }

    public TFinancetype getFinanceType() {
        return this.financeType;
    }

    public void setFinanceType(TFinancetype financeType) {
        this.financeType = financeType;
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

    public void setFinanceTypeService(FinanceTypeService financeTypeService) {
        this.financeTypeService = financeTypeService;
    }

    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    public Integer[] getUserIds() {
        return this.userIds;
    }

    public void setUserIds(Integer[] userIds) {
        this.userIds = userIds;
    }
}


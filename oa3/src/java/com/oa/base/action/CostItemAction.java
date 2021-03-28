/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.emp.service.EmpService
 *  com.common.entity.TCostItem
 *  com.common.util.PageBean
 *  com.oa.base.action.CostItemAction
 *  com.oa.base.service.CostItemService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.base.action;

import com.common.emp.service.EmpService;
import com.common.entity.TCostItem;
import com.common.util.PageBean;
import com.oa.base.service.CostItemService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class CostItemAction
extends ActionSupport {
    private CostItemService costItemService;
    private EmpService empService;
    private TCostItem query;
    private TCostItem cost;
    private int costId;
    private Integer[] userIds;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.costItemService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("costList", (Object)list);
        PageBean pagebean = this.costItemService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public void init() {
        this.request.setAttribute("empList", (Object)this.empService.getValidAllList());
        if (this.cost != null && this.cost.getCId() != null && this.cost.getCId() > 0) {
            List userIdList = this.costItemService.getUserList(this.cost.getCId().intValue());
            this.request.setAttribute("userIdList", (Object)userIdList);
        }
    }

    public String addCostItem() {
        this.init();
        return "success";
    }

    public String saveCostItem() {
        if (this.cost.getCName() == null || "".equals(this.cost.getCName().trim())) {
            this.init();
            this.request.setAttribute("errInfo", (Object)"成本项目名称不能为空");
            return "input";
        }
        int count = this.costItemService.checkName((int)this.cost.getCType().shortValue(), (Serializable)this.cost.getCId(), this.cost.getCName());
        if (count > 0) {
            if (this.cost.getCType() == 1) {
                this.request.setAttribute("errInfo", (Object)"成本项目名称重复");
            } else {
                this.request.setAttribute("errInfo", (Object)"回款项目名称重复");
            }
            this.init();
            return "input";
        }
        try {
            this.costItemService.saveEntity(this.cost, this.userIds);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.init();
            return "error";
        }
        return "success";
    }

    public String editCostItem() {
        this.cost = this.costItemService.getEntityById((Serializable)Integer.valueOf(this.costId));
        this.init();
        return "success";
    }

    public String deleteCostItem() {
        int count = this.costItemService.checkRef((Serializable)Integer.valueOf(this.costId));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"成本项目已经被使用，不允许删除");
            return "input";
        }
        try {
            this.costItemService.deleteEntity((Serializable)Integer.valueOf(this.costId));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String deleteCashItem() {
        int count = this.costItemService.checkRef2((Serializable)Integer.valueOf(this.costId));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"回款项目已经被使用，不允许删除");
            return "input";
        }
        try {
            this.costItemService.deleteEntity((Serializable)Integer.valueOf(this.costId));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidCostItem() {
        try {
            this.costItemService.updateEntityState((Serializable)Integer.valueOf(this.costId), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public TCostItem getQuery() {
        return this.query;
    }

    public void setQuery(TCostItem query) {
        this.query = query;
    }

    public TCostItem getCost() {
        return this.cost;
    }

    public void setCost(TCostItem cost) {
        this.cost = cost;
    }

    public int getCostId() {
        return this.costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
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

    public void setCostItemService(CostItemService costItemService) {
        this.costItemService = costItemService;
    }

    public Integer[] getUserIds() {
        return this.userIds;
    }

    public void setUserIds(Integer[] userIds) {
        this.userIds = userIds;
    }

    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }
}


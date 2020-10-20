/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.dept.action.DeptAction
 *  com.common.dept.service.DeptService
 *  com.common.entity.TDept
 *  com.common.util.PageBean
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.common.dept.action;

import com.common.dept.service.DeptService;
import com.common.entity.TDept;
import com.common.util.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class DeptAction
extends ActionSupport {
    private DeptService deptService;
    private TDept query;
    private TDept dept;
    private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
        List list = this.deptService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("deptList", (Object)list);
        PageBean pagebean = this.deptService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addDept() {
        return "success";
    }

    public String saveDept() {
        if (this.dept.getCCode() == null || "".equals(this.dept.getCCode().trim())) {
            this.request.setAttribute("errInfo", (Object)"部门编号不能为空");
            return "input";
        }
        if (this.dept.getCName() == null || "".equals(this.dept.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"部门名称不能为空");
            return "input";
        }
        int count = this.deptService.checkCode((Serializable)this.dept.getCId(), this.dept.getCCode());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"部门编号重复");
            return "input";
        }
        count = this.deptService.checkName((Serializable)this.dept.getCId(), this.dept.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"部门名称重复");
            return "input";
        }
        try {
            this.deptService.saveEntity(this.dept);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editDept() {
        this.dept = this.deptService.getEntityById((Serializable)Integer.valueOf(this.id));
        return "success";
    }

    public String deleteDept() {
        int count = this.deptService.checkRef((Serializable)Integer.valueOf(this.id));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"部门下已包含员工，不允许删除");
            return "input";
        }
        try {
            this.deptService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidDept() {
        try {
            this.deptService.updateEntityState((Serializable)Integer.valueOf(this.id), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public TDept getQuery() {
        return this.query;
    }

    public void setQuery(TDept query) {
        this.query = query;
    }

    public TDept getDept() {
        return this.dept;
    }

    public void setDept(TDept dept) {
        this.dept = dept;
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
}


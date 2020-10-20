/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.service.RoleService
 *  com.common.auth.service.UserService
 *  com.common.dept.service.DeptService
 *  com.common.emp.action.EmpAction
 *  com.common.emp.service.EmpService
 *  com.common.entity.TDept
 *  com.common.entity.TEmp
 *  com.common.entity.TUser
 *  com.common.entity.VEmp
 *  com.common.util.MD5
 *  com.common.util.PageBean
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.common.emp.action;

import com.common.auth.service.RoleService;
import com.common.auth.service.UserService;
import com.common.dept.service.DeptService;
import com.common.emp.service.EmpService;
import com.common.entity.TDept;
import com.common.entity.TEmp;
import com.common.entity.TUser;
import com.common.entity.VEmp;
import com.common.util.MD5;
import com.common.util.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class EmpAction
extends ActionSupport {
    private DeptService deptService;
    private EmpService empService;
    private UserService userService;
    private RoleService roleService;
    private VEmp query;
    private TEmp emp;
    private TUser user;
    private int id;
    private int[] role;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public List<TDept> getDeptList() {
        return this.deptService.getValidList();
    }

    public List<Integer> getEmpRoleList() {
        return this.roleService.getList(this.id);
    }

    public String getList() {
        List list = this.empService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("empList", (Object)list);
        PageBean pagebean = this.empService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addEmp() {
        this.request.setAttribute("deptList", (Object)this.getDeptList());
        return "success";
    }

    public void validateSaveEmp() {
        List<Integer> roleList = null;
        if (this.emp.getCId() == null) {
            roleList = new ArrayList();
            for (int i = 0; i < this.role.length; ++i) {
                roleList.add(this.role[i]);
            }
        } else {
            roleList = this.roleService.getList(this.user.getCId().intValue());
        }
        this.request.setAttribute("roleList", roleList);
        this.request.setAttribute("deptList", (Object)this.getDeptList());
    }

    public String saveEmp() {
        if (this.emp.getTDept().getCId() == 0) {
            this.request.setAttribute("errInfo", (Object)"所属部门不能为空");
            return "input";
        }
        if (this.emp.getCCode() == null || "".equals(this.emp.getCCode().trim())) {
            this.request.setAttribute("errInfo", (Object)"员工编号不能为空");
            return "input";
        }
        if (this.emp.getCName() == null || "".equals(this.emp.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"员工名称不能为空");
            return "input";
        }
        if (this.user.getCUsername() == null || "".equals(this.user.getCUsername().trim())) {
            this.request.setAttribute("errInfo", (Object)"登陆账号不能为空");
            return "input";
        }
        int count = this.userService.checkDup((Serializable)this.user.getCId(), this.user.getCUsername());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"登陆账号重复");
            return "input";
        }
        int count2 = this.empService.checkCode((Serializable)this.emp.getCId(), this.emp.getCCode());
        if (count2 > 0) {
            this.request.setAttribute("errInfo", (Object)"员工编号重复");
            return "input";
        }
        try {
            this.empService.saveEntity(this.emp, this.user, this.role);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editEmp() {
        this.emp = this.empService.getEntityById((Serializable)Integer.valueOf(this.id));
        this.request.setAttribute("deptList", (Object)this.getDeptList());
        this.user = this.userService.findByEmpid((Serializable)Integer.valueOf(this.id));
        List roleList = this.roleService.getList(this.user.getCId().intValue());
        this.request.setAttribute("roleList", (Object)roleList);
        return "success";
    }

    public String deleteEmp() {
        TUser tmp = this.userService.findByEmpid((Serializable)Integer.valueOf(this.id));
        int count = this.empService.checkRef((Serializable)tmp.getCId());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"员工已经产生业务数据，不允许删除");
            return "input";
        }
        try {
            this.empService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidEmp() {
        try {
            this.empService.updateEntityState((Serializable)Integer.valueOf(this.id), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String resetPwd() {
        this.user = this.userService.findByEmpid((Serializable)Integer.valueOf(this.id));
        this.user.setCPassword(MD5.getStr((String)"123456"));
        try {
            this.empService.updatePwd(this.user);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        this.request.setAttribute("errInfo", (Object)"重置密码成功！");
        return "success";
    }

    public VEmp getQuery() {
        return this.query;
    }

    public void setQuery(VEmp query) {
        this.query = query;
    }

    public TEmp getEmp() {
        return this.emp;
    }

    public void setEmp(TEmp emp) {
        this.emp = emp;
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

    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    public TUser getUser() {
        return this.user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public int[] getRole() {
        return this.role;
    }

    public void setRole(int[] role) {
        this.role = role;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}


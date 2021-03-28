/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.auth.dao.RoleDAO
 *  com.common.auth.dao.UserDAO
 *  com.common.emp.dao.EmpDAO
 *  com.common.emp.service.EmpService
 *  com.common.emp.service.EmpServiceImpl
 *  com.common.entity.TEmp
 *  com.common.entity.TRole
 *  com.common.entity.TUser
 *  com.common.entity.VEmp
 *  com.common.util.CommonUtil
 *  com.common.util.MD5
 *  com.common.util.PageBean
 */
package com.common.emp.service;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.RoleDAO;
import com.common.auth.dao.UserDAO;
import com.common.emp.dao.EmpDAO;
import com.common.emp.service.EmpService;
import com.common.entity.TEmp;
import com.common.entity.TRole;
import com.common.entity.TUser;
import com.common.entity.VEmp;
import com.common.util.CommonUtil;
import com.common.util.MD5;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public class EmpServiceImpl
implements EmpService {
    private EmpDAO empDAO;
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    public List<VEmp> getList(VEmp query, int pageNow, int pageSize) {
        return this.empDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(VEmp query, int pageNow, int pageSize) {
        return this.empDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TEmp emp, TUser user, int[] role) throws Exception {
        if (emp.getCId() == null) {
            emp.setCState(Short.valueOf((short)1));
            emp.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            emp.setCCreateDate(CommonUtil.getDatetime());
        } else {
            emp.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            emp.setCLastDate(CommonUtil.getDatetime());
        }
        this.empDAO.saveEntity(emp);
        if (user.getCId() == null) {
            user.setCState(Short.valueOf((short)1));
            user.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            user.setCCreateDate(CommonUtil.getDatetime());
            user.setCPassword(MD5.getStr((String)"123456"));
            user.setCEmpid(emp.getCId());
        } else {
            user.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            user.setCLastDate(CommonUtil.getDatetime());
        }
        this.userDAO.saveEntity(user);
        if (user.getCId() != null && user.getCId() > 0) {
            this.roleDAO.deleteRole(user.getCId().intValue());
        }
        if (role != null && role.length > 0) {
            for (int i = 0; i < role.length; ++i) {
                TRole r = new TRole();
                r.setCUserid(user.getCId());
                r.setCRoleid(Integer.valueOf(role[i]));
                this.roleDAO.saveRole(r);
                r = null;
                if (role[i] != 201) continue;
                TRole r2 = new TRole();
                r2.setCUserid(user.getCId());
                r2.setCRoleid(Integer.valueOf(215));
                this.roleDAO.saveRole(r2);
                r2 = null;
            }
        }
    }

    public TEmp getEntityById(Serializable id) {
        return this.empDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        TUser user = this.userDAO.findByEmpid(id);
        this.roleDAO.deleteRole(user.getCId().intValue());
        this.userDAO.deleteEntity((Serializable)user.getCId());
        this.empDAO.deleteEntity(id);
    }

    public int checkCode(Serializable id, String code) {
        return this.empDAO.checkCode(id, code);
    }

    public int checkRef(Serializable id) {
        return this.empDAO.checkRef(id);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        TUser user = this.userDAO.findByEmpid(id);
        this.userDAO.updateState((Serializable)user.getCId(), state);
        this.empDAO.updateState(id, state);
    }

    public List<VEmp> getValidList() {
        return this.empDAO.getValidList();
    }

    public List<VEmp> getValidAllList() {
        return this.empDAO.getValidAllList();
    }

    public void updatePwd(TUser user) throws Exception {
        this.userDAO.saveEntity(user);
    }

    public void setEmpDAO(EmpDAO empDAO) {
        this.empDAO = empDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
}


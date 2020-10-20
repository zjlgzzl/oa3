/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.dept.dao.DeptDAO
 *  com.common.dept.service.DeptService
 *  com.common.dept.service.DeptServiceImpl
 *  com.common.entity.TDept
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 */
package com.common.dept.service;

import com.common.auth.action.UserInfo;
import com.common.dept.dao.DeptDAO;
import com.common.dept.service.DeptService;
import com.common.entity.TDept;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public class DeptServiceImpl
implements DeptService {
    private DeptDAO deptDAO;

    public List<TDept> getList(TDept query, int pageNow, int pageSize) {
        return this.deptDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TDept query, int pageNow, int pageSize) {
        return this.deptDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TDept entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCCreateDate(CommonUtil.getDatetime());
        } else {
            entity.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCLastDate(CommonUtil.getDatetime());
        }
        this.deptDAO.saveEntity(entity);
    }

    public TDept getEntityById(Serializable id) {
        return this.deptDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.deptDAO.deleteEntity(id);
    }

    public int checkCode(Serializable id, String code) {
        return this.deptDAO.checkCode(id, code);
    }

    public int checkName(Serializable id, String name) {
        return this.deptDAO.checkName(id, name);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        this.deptDAO.updateEntityState(id, state);
    }

    public List<TDept> getValidList() {
        return this.deptDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.deptDAO.checkRef(id);
    }

    public void setDeptDAO(DeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }
}


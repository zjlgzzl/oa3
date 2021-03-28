/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.auth.dao.UserDAO
 *  com.common.entity.THscode
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.hscode.dao.HsCodeDAO
 *  com.oa.hscode.service.HsCodeService
 *  com.oa.hscode.service.HsCodeServiceImpl
 */
package com.oa.hscode.service;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.UserDAO;
import com.common.entity.THscode;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.hscode.dao.HsCodeDAO;
import com.oa.hscode.service.HsCodeService;
import java.io.Serializable;
import java.util.List;

public class HsCodeServiceImpl
implements HsCodeService {
    private HsCodeDAO hsCodeDAO;
    private UserDAO userDAO;

    public List<THscode> getList(THscode query, int pageNow, int pageSize) {
        return this.hsCodeDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(THscode query, int pageNow, int pageSize) {
        return this.hsCodeDAO.getPages(query, pageNow, pageSize);
    }

    public List<THscode> getList2(THscode query, int pageNow, int pageSize) {
        return this.hsCodeDAO.getList2(query, pageNow, pageSize);
    }

    public PageBean getPages2(THscode query, int pageNow, int pageSize) {
        return this.hsCodeDAO.getPages2(query, pageNow, pageSize);
    }

    public void saveEntity(THscode entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
            entity.setCCreateDate(CommonUtil.getDatetime());
        }
        this.hsCodeDAO.saveEntity(entity);
    }

    public THscode getEntityById(Serializable id) {
        return this.hsCodeDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.hsCodeDAO.deleteEntity(id);
    }

    public int checkDup(String des, Integer id) {
        return this.hsCodeDAO.checkDup(des, id);
    }

    public void setHsCodeDAO(HsCodeDAO hsCodeDAO) {
        this.hsCodeDAO = hsCodeDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}


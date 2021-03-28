/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TContainerModel
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConModelDAO
 *  com.oa.container.service.ConModelService
 *  com.oa.container.service.ConModelServiceImpl
 */
package com.oa.container.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TContainerModel;
import com.common.util.PageBean;
import com.oa.container.dao.ConModelDAO;
import com.oa.container.service.ConModelService;
import java.io.Serializable;
import java.util.List;

public class ConModelServiceImpl
implements ConModelService {
    private ConModelDAO conModelDAO;

    public List<TContainerModel> getList(TContainerModel query, int pageNow, int pageSize) {
        return this.conModelDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TContainerModel query, int pageNow, int pageSize) {
        return this.conModelDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TContainerModel entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCState(Short.valueOf((short)1));
        }
        this.conModelDAO.saveEntity(entity);
    }

    public TContainerModel getEntityById(Serializable id) {
        return this.conModelDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.conModelDAO.deleteEntity(id);
    }

    public int checkCode(Serializable id, String code) {
        return this.conModelDAO.checkCode(id, code);
    }

    public List<TContainerModel> getValidList() {
        return this.conModelDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.conModelDAO.checkRef(id);
    }

    public void setConModelDAO(ConModelDAO conModelDAO) {
        this.conModelDAO = conModelDAO;
    }
}


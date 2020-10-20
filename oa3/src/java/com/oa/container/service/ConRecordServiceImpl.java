/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.auth.dao.UserDAO
 *  com.common.entity.TContainerRecord
 *  com.common.entity.VConcompute
 *  com.common.entity.VContainerRecord
 *  com.common.entity.VConview
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConRecordDAO
 *  com.oa.container.service.ConRecordService
 *  com.oa.container.service.ConRecordServiceImpl
 */
package com.oa.container.service;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.UserDAO;
import com.common.entity.TContainerRecord;
import com.common.entity.VConcompute;
import com.common.entity.VContainerRecord;
import com.common.entity.VConview;
import com.common.util.PageBean;
import com.oa.container.dao.ConRecordDAO;
import com.oa.container.service.ConRecordService;
import java.io.Serializable;
import java.util.List;

public class ConRecordServiceImpl
implements ConRecordService {
    private ConRecordDAO conRecordDAO;
    private UserDAO userDAO;

    public List<VContainerRecord> getList(VContainerRecord query, Object[] obj, int pageNow, int pageSize) {
        return this.conRecordDAO.getList(query, obj, pageNow, pageSize);
    }

    public PageBean getPages(VContainerRecord query, Object[] obj, int pageNow, int pageSize) {
        return this.conRecordDAO.getPages(query, obj, pageNow, pageSize);
    }

    public void saveEntity(TContainerRecord entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
            entity.setCState(Short.valueOf((short)1));
        }
        this.conRecordDAO.saveEntity(entity);
    }

    public TContainerRecord getEntityById(Serializable id) {
        return this.conRecordDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.conRecordDAO.deleteEntity(id);
    }

    public int checkCode(Serializable id, String code) {
        return this.conRecordDAO.checkCode(id, code);
    }

    public List<TContainerRecord> getValidList() {
        return this.conRecordDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.conRecordDAO.checkRef(id);
    }

    public List<VConview> getReportList(VConview query, int pageNow, int pageSize) {
        return this.conRecordDAO.getReportList(query, pageNow, pageSize);
    }

    public PageBean getReportPages(VConview query, int pageNow, int pageSize) {
        return this.conRecordDAO.getReportPages(query, pageNow, pageSize);
    }

    public List<VConcompute> getComputeList(VConcompute query, Object[] obj, int pageNow, int pageSize) {
        return this.conRecordDAO.getComputeList(query, obj, pageNow, pageSize);
    }

    public PageBean getComputePages(VConcompute query, Object[] obj, int pageNow, int pageSize) {
        return this.conRecordDAO.getComputePages(query, obj, pageNow, pageSize);
    }

    public void updateState(int id, short state) throws Exception {
        this.conRecordDAO.updateState(id, state);
    }

    public double getConCost(VConcompute query, Object[] obj) {
        return this.conRecordDAO.getConCost(query, obj);
    }

    public void setConRecordDAO(ConRecordDAO conRecordDAO) {
        this.conRecordDAO = conRecordDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}


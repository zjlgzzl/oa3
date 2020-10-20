/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TServiceType
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.dao.ServiceTypeDAO
 *  com.oa.base.service.ServiceTypeService
 *  com.oa.base.service.ServiceTypeServiceImpl
 */
package com.oa.base.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TServiceType;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.ServiceTypeDAO;
import com.oa.base.service.ServiceTypeService;
import java.io.Serializable;
import java.util.List;

public class ServiceTypeServiceImpl
implements ServiceTypeService {
    private ServiceTypeDAO serviceTypeDAO;

    public List<TServiceType> getList(TServiceType query, int pageNow, int pageSize) {
        return this.serviceTypeDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TServiceType query, int pageNow, int pageSize) {
        return this.serviceTypeDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TServiceType entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCCreateDate(CommonUtil.getDatetime());
        } else {
            entity.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCLastDate(CommonUtil.getDatetime());
        }
        this.serviceTypeDAO.saveEntity(entity);
    }

    public TServiceType getEntityById(Serializable id) {
        return this.serviceTypeDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.serviceTypeDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, String name) {
        return this.serviceTypeDAO.checkName(id, name);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        this.serviceTypeDAO.updateEntityState(id, state);
    }

    public List<TServiceType> getValidList() {
        return this.serviceTypeDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.serviceTypeDAO.checkRef(id);
    }

    public void setServiceTypeDAO(ServiceTypeDAO serviceTypeDAO) {
        this.serviceTypeDAO = serviceTypeDAO;
    }
}


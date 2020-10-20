/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TServiceItem
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.dao.ServiceItemDAO
 *  com.oa.base.service.ServiceItemService
 *  com.oa.base.service.ServiceItemServiceImpl
 */
package com.oa.base.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TServiceItem;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.ServiceItemDAO;
import com.oa.base.service.ServiceItemService;
import java.io.Serializable;
import java.util.List;

public class ServiceItemServiceImpl
implements ServiceItemService {
    private ServiceItemDAO serviceItemDAO;

    public List<TServiceItem> getList(TServiceItem query, int pageNow, int pageSize) {
        return this.serviceItemDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TServiceItem query, int pageNow, int pageSize) {
        return this.serviceItemDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TServiceItem entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCCreateDate(CommonUtil.getDatetime());
        } else {
            entity.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCLastDate(CommonUtil.getDatetime());
        }
        this.serviceItemDAO.saveEntity(entity);
    }

    public TServiceItem getEntityById(Serializable id) {
        return this.serviceItemDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.serviceItemDAO.deleteEntity(id);
    }

    public int checkName(Serializable typeId, Serializable id, String name) {
        return this.serviceItemDAO.checkName(typeId, id, name);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        this.serviceItemDAO.updateEntityState(id, state);
    }

    public List<TServiceItem> getValidList(int typeId) {
        return this.serviceItemDAO.getValidList(typeId);
    }

    public int checkRef(Serializable id) {
        return this.serviceItemDAO.checkRef(id);
    }

    public void setServiceItemDAO(ServiceItemDAO serviceItemDAO) {
        this.serviceItemDAO = serviceItemDAO;
    }
}


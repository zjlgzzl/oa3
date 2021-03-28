/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TCustomer
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.dao.CusDAO
 *  com.oa.base.service.CusService
 *  com.oa.base.service.CusServiceImpl
 */
package com.oa.base.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TCustomer;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.CusDAO;
import com.oa.base.service.CusService;
import java.io.Serializable;
import java.util.List;

public class CusServiceImpl
implements CusService {
    private CusDAO cusDAO;

    public List<TCustomer> getList(TCustomer query, int pageNow, int pageSize) throws Exception {
        return this.cusDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TCustomer query, int pageNow, int pageSize) throws Exception {
        return this.cusDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TCustomer entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCCreateDate(CommonUtil.getDatetime());
        }
        this.cusDAO.saveEntity(entity);
    }

    public TCustomer getEntityById(Serializable id) {
        return this.cusDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.cusDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, String name) {
        return this.cusDAO.checkName(id, name);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        this.cusDAO.updateEntityState(id, state);
    }

    public List<TCustomer> getValidList() {
        return this.cusDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.cusDAO.checkRef(id);
    }

    public void setCusDAO(CusDAO cusDAO) {
        this.cusDAO = cusDAO;
    }
}


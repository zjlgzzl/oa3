/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TBusinType
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.dao.BusinTypeDAO
 *  com.oa.base.service.BusinTypeService
 *  com.oa.base.service.BusinTypeServiceImpl
 */
package com.oa.base.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TBusinType;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.BusinTypeDAO;
import com.oa.base.service.BusinTypeService;
import java.io.Serializable;
import java.util.List;

public class BusinTypeServiceImpl
implements BusinTypeService {
    private BusinTypeDAO businTypeDAO;

    public List<TBusinType> getList(TBusinType query, int pageNow, int pageSize) {
        return this.businTypeDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TBusinType query, int pageNow, int pageSize) {
        return this.businTypeDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TBusinType entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCCreateDate(CommonUtil.getDatetime());
        } else {
            entity.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCLastDate(CommonUtil.getDatetime());
        }
        this.businTypeDAO.saveEntity(entity);
    }

    public TBusinType getEntityById(Serializable id) {
        return this.businTypeDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.businTypeDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, String name) {
        return this.businTypeDAO.checkName(id, name);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        this.businTypeDAO.updateEntityState(id, state);
    }

    public List<TBusinType> getValidList() {
        return this.businTypeDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.businTypeDAO.checkRef(id);
    }

    public void setBusinTypeDAO(BusinTypeDAO businTypeDAO) {
        this.businTypeDAO = businTypeDAO;
    }
}


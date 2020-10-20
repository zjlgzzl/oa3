/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TAccItem
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.dao.AccItemDAO
 *  com.oa.base.service.AccItemService
 *  com.oa.base.service.AccItemServiceImpl
 */
package com.oa.base.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TAccItem;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.AccItemDAO;
import com.oa.base.service.AccItemService;
import java.io.Serializable;
import java.util.List;

public class AccItemServiceImpl
implements AccItemService {
    private AccItemDAO accItemDAO;

    public List<TAccItem> getList(TAccItem query, int pageNow, int pageSize) {
        return this.accItemDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TAccItem query, int pageNow, int pageSize) {
        return this.accItemDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TAccItem entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCCreateDate(CommonUtil.getDatetime());
        } else {
            entity.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCLastDate(CommonUtil.getDatetime());
        }
        this.accItemDAO.saveEntity(entity);
    }

    public TAccItem getEntityById(Serializable id) {
        return this.accItemDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.accItemDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, String name) {
        return this.accItemDAO.checkName(id, name);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        this.accItemDAO.updateEntityState(id, state);
    }

    public List<TAccItem> getValidList() {
        return this.accItemDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.accItemDAO.checkRef(id);
    }

    public void setAccItemDAO(AccItemDAO accItemDAO) {
        this.accItemDAO = accItemDAO;
    }
}


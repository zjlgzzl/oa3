/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TContainerInfo
 *  com.common.entity.VConview
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConInfoDAO
 *  com.oa.container.service.ConInfoService
 *  com.oa.container.service.ConInfoServiceImpl
 */
package com.oa.container.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TContainerInfo;
import com.common.entity.VConview;
import com.common.util.PageBean;
import com.oa.container.dao.ConInfoDAO;
import com.oa.container.service.ConInfoService;
import java.io.Serializable;
import java.util.List;

public class ConInfoServiceImpl
implements ConInfoService {
    private ConInfoDAO conInfoDAO;

    public List<TContainerInfo> getList(TContainerInfo query, int pageNow, int pageSize) {
        return this.conInfoDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TContainerInfo query, int pageNow, int pageSize) {
        return this.conInfoDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TContainerInfo entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCState(Short.valueOf((short)1));
        }
        this.conInfoDAO.saveEntity(entity);
    }

    public TContainerInfo getEntityById(Serializable id) {
        return this.conInfoDAO.getEntityById(id);
    }

    public TContainerInfo getEntityById2(Serializable id) {
        return this.conInfoDAO.getEntityById2(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.conInfoDAO.deleteEntity(id);
    }

    public int checkCode(Serializable id, String code) {
        return this.conInfoDAO.checkCode(id, code);
    }

    public List<VConview> getValidList(Serializable id) {
        return this.conInfoDAO.getValidList(id);
    }

    public int checkRef(Serializable id) {
        return this.conInfoDAO.checkRef(id);
    }

    public void setConInfoDAO(ConInfoDAO conInfoDAO) {
        this.conInfoDAO = conInfoDAO;
    }
}


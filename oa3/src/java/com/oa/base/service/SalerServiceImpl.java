/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TSaler
 *  com.common.util.PageBean
 *  com.oa.base.dao.SalerDAO
 *  com.oa.base.service.SalerService
 *  com.oa.base.service.SalerServiceImpl
 */
package com.oa.base.service;

import com.common.entity.TSaler;
import com.common.util.PageBean;
import com.oa.base.dao.SalerDAO;
import com.oa.base.service.SalerService;
import java.io.Serializable;
import java.util.List;

public class SalerServiceImpl
implements SalerService {
    private SalerDAO salerDAO;

    public List<TSaler> getList(TSaler query, int pageNow, int pageSize) {
        return this.salerDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TSaler query, int pageNow, int pageSize) {
        return this.salerDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TSaler entity) throws Exception {
        this.salerDAO.saveEntity(entity);
    }

    public TSaler getEntityById(Serializable id) {
        return this.salerDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.salerDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, String name) {
        return this.salerDAO.checkName(id, name);
    }

    public List<TSaler> getValidList() {
        return this.salerDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.salerDAO.checkRef(id);
    }

    public void setSalerDAO(SalerDAO salerDAO) {
        this.salerDAO = salerDAO;
    }
}


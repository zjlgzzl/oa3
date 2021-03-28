/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TRate
 *  com.common.util.PageBean
 *  com.oa.rate.dao.RateDAO
 *  com.oa.rate.service.RateService
 *  com.oa.rate.service.RateServiceImpl
 */
package com.oa.rate.service;

import com.common.entity.TRate;
import com.common.util.PageBean;
import com.oa.rate.dao.RateDAO;
import com.oa.rate.service.RateService;
import java.io.Serializable;
import java.util.List;

public class RateServiceImpl
implements RateService {
    private RateDAO rateDAO;

    public List<TRate> getList(TRate query, int pageNow, int pageSize) {
        return this.rateDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TRate query, int pageNow, int pageSize) {
        return this.rateDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TRate entity) throws Exception {
        this.rateDAO.saveEntity(entity);
    }

    public TRate getEntityById(Serializable id) {
        return this.rateDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.rateDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, double name) {
        return this.rateDAO.checkName(id, name);
    }

    public List<TRate> getValidList() {
        return this.rateDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.rateDAO.checkRef(id);
    }

    public void setRateDAO(RateDAO rateDAO) {
        this.rateDAO = rateDAO;
    }
}


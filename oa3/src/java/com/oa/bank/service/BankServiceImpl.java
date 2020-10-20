/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TBank
 *  com.common.util.PageBean
 *  com.oa.bank.dao.BankDAO
 *  com.oa.bank.service.BankService
 *  com.oa.bank.service.BankServiceImpl
 */
package com.oa.bank.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TBank;
import com.common.util.PageBean;
import com.oa.bank.dao.BankDAO;
import com.oa.bank.service.BankService;
import java.io.Serializable;
import java.util.List;

public class BankServiceImpl
implements BankService {
    private BankDAO bankDAO;

    public List<TBank> getList(TBank query, int pageNow, int pageSize) {
        return this.bankDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TBank query, int pageNow, int pageSize) {
        return this.bankDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TBank entity) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
        }
        this.bankDAO.saveEntity(entity);
    }

    public TBank getEntityById(Serializable id) {
        return this.bankDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.bankDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, String name) {
        return this.bankDAO.checkName(id, name);
    }

    public List<TBank> getValidList() {
        return this.bankDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.bankDAO.checkRef(id);
    }

    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }
}


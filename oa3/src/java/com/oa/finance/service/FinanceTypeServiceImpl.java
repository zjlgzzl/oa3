/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TFinancetype
 *  com.common.entity.TFinancetypeUser
 *  com.common.util.PageBean
 *  com.oa.finance.dao.FinanceTypeDAO
 *  com.oa.finance.service.FinanceTypeService
 *  com.oa.finance.service.FinanceTypeServiceImpl
 */
package com.oa.finance.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TFinancetype;
import com.common.entity.TFinancetypeUser;
import com.common.util.PageBean;
import com.oa.finance.dao.FinanceTypeDAO;
import com.oa.finance.service.FinanceTypeService;
import java.io.Serializable;
import java.util.List;

public class FinanceTypeServiceImpl
implements FinanceTypeService {
    private FinanceTypeDAO financeTypeDAO;

    public List<TFinancetype> getList(TFinancetype query, int pageNow, int pageSize) {
        return this.financeTypeDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TFinancetype query, int pageNow, int pageSize) {
        return this.financeTypeDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TFinancetype entity, Integer[] userIds) throws Exception {
        Integer cid = entity.getCId();
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
        } else if (entity.getCType() == 2) {
            this.financeTypeDAO.deleteUser(entity.getCId().intValue());
        }
        this.financeTypeDAO.saveEntity(entity);
        this.financeTypeDAO.deleteUser(entity.getCId().intValue());
        if (userIds != null && userIds.length > 0) {
            for (int i = 0; i < userIds.length; ++i) {
                TFinancetypeUser uu = new TFinancetypeUser();
                uu.setCTypeid(entity.getCId());
                uu.setCUserid(userIds[i]);
                uu.setCType(entity.getCType());
                this.financeTypeDAO.saveUser(uu);
                uu = null;
            }
        }
    }

    public TFinancetype getEntityById(Serializable id) {
        return this.financeTypeDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.financeTypeDAO.deleteEntity(id);
    }

    public int checkName(Serializable id, String name) {
        return this.financeTypeDAO.checkName(id, name);
    }

    public List<TFinancetype> getValidList(int utype) {
        return this.financeTypeDAO.getValidList(utype);
    }

    public int checkRef(Serializable id) {
        return this.financeTypeDAO.checkRef(id);
    }

    public List<Integer> getUserList(int typeid) {
        return this.financeTypeDAO.getUserList(typeid);
    }

    public void setFinanceTypeDAO(FinanceTypeDAO financeTypeDAO) {
        this.financeTypeDAO = financeTypeDAO;
    }
}


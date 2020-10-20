/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinancetype
 *  com.common.entity.TFinancetypeUser
 *  com.common.util.PageBean
 *  com.oa.finance.dao.FinanceTypeDAO
 */
package com.oa.finance.dao;

import com.common.entity.TFinancetype;
import com.common.entity.TFinancetypeUser;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface FinanceTypeDAO {
    public List<TFinancetype> getList(TFinancetype var1, int var2, int var3);

    public PageBean getPages(TFinancetype var1, int var2, int var3);

    public void saveEntity(TFinancetype var1) throws Exception;

    public TFinancetype getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, String var2);

    public List<TFinancetype> getValidList(int var1);

    public int checkRef(Serializable var1);

    public List<Integer> getUserList(int var1);

    public void deleteUser(int var1) throws Exception;

    public void saveUser(TFinancetypeUser var1) throws Exception;
}


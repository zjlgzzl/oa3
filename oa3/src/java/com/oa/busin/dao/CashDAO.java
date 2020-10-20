/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCash
 *  com.oa.busin.dao.CashDAO
 */
package com.oa.busin.dao;

import com.common.entity.TCash;
import java.io.Serializable;
import java.util.List;

public interface CashDAO {
    public List<TCash> getList(int var1, short var2);

    public void saveCash(TCash var1) throws Exception;

    public void deleteCash(Serializable var1) throws Exception;

    public void deleteCashByBusinid(int var1) throws Exception;

    public void updateCashDetailState(Serializable var1, short var2) throws Exception;

    public Object[] getCashInfo(int var1);
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCashRate
 *  com.oa.busin.dao.CashRateDAO
 */
package com.oa.busin.dao;

import com.common.entity.TCashRate;
import java.io.Serializable;
import java.util.List;

public interface CashRateDAO {
    public List<TCashRate> getList(int var1, short var2);

    public void saveRate(TCashRate var1) throws Exception;

    public void deleteRate(Serializable var1) throws Exception;

    public void deleteRateByBusinid(int var1) throws Exception;

    public Object[] getCashInfo(int var1);
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TRate
 *  com.common.util.PageBean
 *  com.oa.rate.dao.RateDAO
 */
package com.oa.rate.dao;

import com.common.entity.TRate;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface RateDAO {
    public List<TRate> getList(TRate var1, int var2, int var3);

    public PageBean getPages(TRate var1, int var2, int var3);

    public void saveEntity(TRate var1) throws Exception;

    public TRate getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, double var2);

    public List<TRate> getValidList();

    public int checkRef(Serializable var1);
}


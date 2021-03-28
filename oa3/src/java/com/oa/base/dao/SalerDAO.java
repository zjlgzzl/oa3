/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TSaler
 *  com.common.util.PageBean
 *  com.oa.base.dao.SalerDAO
 */
package com.oa.base.dao;

import com.common.entity.TSaler;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface SalerDAO {
    public List<TSaler> getList(TSaler var1, int var2, int var3);

    public PageBean getPages(TSaler var1, int var2, int var3);

    public void saveEntity(TSaler var1) throws Exception;

    public TSaler getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, String var2);

    public List<TSaler> getValidList();

    public int checkRef(Serializable var1);
}


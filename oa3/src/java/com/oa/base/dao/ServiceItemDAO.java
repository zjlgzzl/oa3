/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TServiceItem
 *  com.common.util.PageBean
 *  com.oa.base.dao.ServiceItemDAO
 */
package com.oa.base.dao;

import com.common.entity.TServiceItem;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface ServiceItemDAO {
    public List<TServiceItem> getList(TServiceItem var1, int var2, int var3);

    public PageBean getPages(TServiceItem var1, int var2, int var3);

    public void saveEntity(TServiceItem var1) throws Exception;

    public TServiceItem getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, Serializable var2, String var3);

    public void updateEntityState(Serializable var1, short var2) throws Exception;

    public List<TServiceItem> getValidList(int var1);

    public int checkRef(Serializable var1);
}


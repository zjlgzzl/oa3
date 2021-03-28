/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TServiceType
 *  com.common.util.PageBean
 *  com.oa.base.dao.ServiceTypeDAO
 */
package com.oa.base.dao;

import com.common.entity.TServiceType;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface ServiceTypeDAO {
    public List<TServiceType> getList(TServiceType var1, int var2, int var3);

    public PageBean getPages(TServiceType var1, int var2, int var3);

    public void saveEntity(TServiceType var1) throws Exception;

    public TServiceType getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, String var2);

    public void updateEntityState(Serializable var1, short var2) throws Exception;

    public List<TServiceType> getValidList();

    public int checkRef(Serializable var1);
}


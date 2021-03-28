/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCustomer
 *  com.common.util.PageBean
 *  com.oa.base.service.CusService
 */
package com.oa.base.service;

import com.common.entity.TCustomer;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface CusService {
    public List<TCustomer> getList(TCustomer var1, int var2, int var3) throws Exception;

    public PageBean getPages(TCustomer var1, int var2, int var3) throws Exception;

    public void saveEntity(TCustomer var1) throws Exception;

    public TCustomer getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, String var2);

    public void updateEntityState(Serializable var1, short var2) throws Exception;

    public List<TCustomer> getValidList();

    public int checkRef(Serializable var1);
}


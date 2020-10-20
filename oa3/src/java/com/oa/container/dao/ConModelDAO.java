/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerModel
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConModelDAO
 */
package com.oa.container.dao;

import com.common.entity.TContainerModel;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface ConModelDAO {
    public List<TContainerModel> getList(TContainerModel var1, int var2, int var3);

    public PageBean getPages(TContainerModel var1, int var2, int var3);

    public void saveEntity(TContainerModel var1) throws Exception;

    public TContainerModel getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkCode(Serializable var1, String var2);

    public List<TContainerModel> getValidList();

    public int checkRef(Serializable var1);
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TAccItem
 *  com.common.util.PageBean
 *  com.oa.base.dao.AccItemDAO
 */
package com.oa.base.dao;

import com.common.entity.TAccItem;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface AccItemDAO {
    public List<TAccItem> getList(TAccItem var1, int var2, int var3);

    public PageBean getPages(TAccItem var1, int var2, int var3);

    public void saveEntity(TAccItem var1) throws Exception;

    public TAccItem getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, String var2);

    public void updateEntityState(Serializable var1, short var2) throws Exception;

    public List<TAccItem> getValidList();

    public int checkRef(Serializable var1);
}


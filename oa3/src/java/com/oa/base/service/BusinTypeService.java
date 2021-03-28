/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusinType
 *  com.common.util.PageBean
 *  com.oa.base.service.BusinTypeService
 */
package com.oa.base.service;

import com.common.entity.TBusinType;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface BusinTypeService {
    public List<TBusinType> getList(TBusinType var1, int var2, int var3);

    public PageBean getPages(TBusinType var1, int var2, int var3);

    public void saveEntity(TBusinType var1) throws Exception;

    public TBusinType getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, String var2);

    public void updateEntityState(Serializable var1, short var2) throws Exception;

    public List<TBusinType> getValidList();

    public int checkRef(Serializable var1);
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerInfo
 *  com.common.entity.VConview
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConInfoDAO
 */
package com.oa.container.dao;

import com.common.entity.TContainerInfo;
import com.common.entity.VConview;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface ConInfoDAO {
    public List<TContainerInfo> getList(TContainerInfo var1, int var2, int var3);

    public PageBean getPages(TContainerInfo var1, int var2, int var3);

    public void saveEntity(TContainerInfo var1) throws Exception;

    public TContainerInfo getEntityById(Serializable var1);

    public TContainerInfo getEntityById2(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkCode(Serializable var1, String var2);

    public List<VConview> getValidList(Serializable var1);

    public int checkRef(Serializable var1);
}


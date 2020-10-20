/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerRecord
 *  com.common.entity.VConcompute
 *  com.common.entity.VContainerRecord
 *  com.common.entity.VConview
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConRecordDAO
 */
package com.oa.container.dao;

import com.common.entity.TContainerRecord;
import com.common.entity.VConcompute;
import com.common.entity.VContainerRecord;
import com.common.entity.VConview;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface ConRecordDAO {
    public List<VContainerRecord> getList(VContainerRecord var1, Object[] var2, int var3, int var4);

    public PageBean getPages(VContainerRecord var1, Object[] var2, int var3, int var4);

    public void saveEntity(TContainerRecord var1) throws Exception;

    public TContainerRecord getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkCode(Serializable var1, String var2);

    public List<TContainerRecord> getValidList();

    public int checkRef(Serializable var1);

    public List<VConview> getReportList(VConview var1, int var2, int var3);

    public PageBean getReportPages(VConview var1, int var2, int var3);

    public List<VConcompute> getComputeList(VConcompute var1, Object[] var2, int var3, int var4);

    public PageBean getComputePages(VConcompute var1, Object[] var2, int var3, int var4);

    public double getConCost(VConcompute var1, Object[] var2);

    public void updateState(int var1, short var2) throws Exception;
}


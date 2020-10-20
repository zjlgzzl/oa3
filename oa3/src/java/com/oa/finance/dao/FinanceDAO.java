/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinance
 *  com.common.entity.TFinanceDetail
 *  com.common.entity.VFinance
 *  com.common.entity.VOutReport
 *  com.common.util.PageBean
 *  com.oa.finance.dao.FinanceDAO
 */
package com.oa.finance.dao;

import com.common.entity.TFinance;
import com.common.entity.TFinanceDetail;
import com.common.entity.VFinance;
import com.common.entity.VOutReport;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface FinanceDAO {
    public List<VFinance> getList(VFinance var1, Object[] var2, int var3, int var4);

    public PageBean getPages(VFinance var1, Object[] var2, int var3, int var4);

    public Double getSumMoney(VFinance var1, Object[] var2);

    public void saveEntity(TFinance var1) throws Exception;

    public TFinance getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public String getMaxBillNo(String var1);

    public List<TFinanceDetail> getDetailList(int var1);

    public void saveDetailEntity(TFinanceDetail var1) throws Exception;

    public void deleteDetailEntity(int var1) throws Exception;

    public void deleteAllDetailEntity(int var1) throws Exception;

    public int getNoticeCount();

    public void updateRemarks(int var1, String var2) throws Exception;

    public void updateRemarks2(int var1, String var2) throws Exception;

    public void updateArchivingState(int var1, short var2) throws Exception;

    public List<VOutReport> getOutReportList(VOutReport var1, Object[] var2);
}


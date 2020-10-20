/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBiaoji
 *  com.common.entity.TBusin
 *  com.common.entity.TCostGroup
 *  com.common.entity.TCostGroupInfo
 *  com.common.entity.TCostGroupItem
 *  com.common.entity.TDelDn
 *  com.common.entity.TFinanceGroup
 *  com.common.entity.TFinanceGroupItem
 *  com.common.entity.VBusin
 *  com.common.entity.VBusinAsk
 *  com.common.entity.VBusinInvUser
 *  com.common.entity.VBusinSumReport
 *  com.common.entity.VBusincusreport
 *  com.common.util.PageBean
 *  com.oa.busin.dao.BusinDAO
 */
package com.oa.busin.dao;

import com.common.entity.TBiaoji;
import com.common.entity.TBusin;
import com.common.entity.TBusinHis;
import com.common.entity.TCostGroup;
import com.common.entity.TCostGroupInfo;
import com.common.entity.TCostGroupItem;
import com.common.entity.TDelDn;
import com.common.entity.TFinanceGroup;
import com.common.entity.TFinanceGroupItem;
import com.common.entity.VBusin;
import com.common.entity.VBusinAsk;
import com.common.entity.VBusinInvUser;
import com.common.entity.VBusinSumReport;
import com.common.entity.VBusincusreport;
import com.common.util.PageBean;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface BusinDAO {
    public List<VBusin> getList(VBusin var1, Object[] var2, int var3, int var4);

    public PageBean getPages(VBusin var1, Object[] var2, int var3, int var4);

    public void saveEntity(TBusin var1) throws Exception;

    public void saveMergeEntity(TBusin var1) throws Exception;

    public String getMaxBillNo(String var1);

    public TBusin getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public void updateEntityState(Serializable var1, short var2, String var3) throws Exception;

    public void updatePrintState(Serializable var1) throws Exception;

    public void updateOrder(int var1, short var2) throws Exception;

    public void updateCostRate(int var1, double var2) throws Exception;

    public void updateCostRate2(int var1, double var2) throws Exception;

    public void updateRecieveMoney(int var1, double var2, int var4) throws Exception;

    public void updateScheduleArchive(int var1, short var2, double var3) throws Exception;

    public void updateRateArchive(int var1, short var2) throws Exception;

    public void updateLock(int var1, short var2) throws Exception;

    public void updateLockFlag(int var1, short var2) throws Exception;

    public String getMaxDN(String var1);

    public String getMaxINV(String var1);

    public void saveDel(TDelDn var1) throws Exception;

    public void updateLogNewFlag(int var1) throws Exception;

    public void updateCostDupFlag(int var1) throws Exception;

    public List<VBusincusreport> getBusinCusReport(Object[] var1);

    public double getBusinLimit(int var1);

    public void updateLocks() throws Exception;

    public double getProfit(int var1);

    public void updateOrders() throws Exception;

    public int getBusinVatCount(int var1);

    public List<TFinanceGroup> getGroupList(TFinanceGroup var1, Object[] var2, int var3, int var4);

    public PageBean getGroupPages(TFinanceGroup var1, Object[] var2, int var3, int var4);

    public void saveGroup(TFinanceGroup var1) throws Exception;

    public TFinanceGroup getGroupById(int var1);

    public void deleteGroup(int var1) throws Exception;

    public int checkName(String var1, Integer var2);

    public void saveGroupDetail(TFinanceGroupItem var1) throws Exception;

    public List<TFinanceGroupItem> getGroupDetail(int var1);

    public void deleteGroupDetail(int var1) throws Exception;

    public void deleteAllGroupDetail(int var1) throws Exception;

    public List<TFinanceGroup> getGroupValidList();

    public List<TCostGroup> getGroupList(TCostGroup var1, Object[] var2, int var3, int var4);

    public PageBean getGroupPages(TCostGroup var1, Object[] var2, int var3, int var4);

    public void saveGroup(TCostGroup var1) throws Exception;

    public TCostGroup getCostGroupById(int var1);

    public void deleteCostGroup(int var1) throws Exception;

    public int checkCostGroupName(String var1, Integer var2);

    public void saveGroupDetail(TCostGroupItem var1) throws Exception;

    public List<TCostGroupItem> getCostGroupDetail(int var1);

    public void deleteCostGroupDetail(int var1) throws Exception;

    public void deleteAllCostGroupDetail(int var1) throws Exception;

    public List<TCostGroup> getCostGroupValidList();

    public List<VBusinInvUser> getInvUserList(VBusinInvUser var1, Object[] var2, int var3, int var4);

    public PageBean getInvUserPages(VBusinInvUser var1, Object[] var2, int var3, int var4);

    public List<VBusinInvUser> getProblemNoticeList(VBusinAsk var1, Object[] var2, int var3, int var4);

    public PageBean getProblemNoticePages(VBusinAsk var1, Object[] var2, int var3, int var4);

    public List<VBusinSumReport> getBusinSumReport(VBusinSumReport var1, Object[] var2, int var3, int var4);

    public PageBean getBusinSumReportPages(VBusinSumReport var1, Object[] var2, int var3, int var4);

    public String getMaxRefno();

    public List<TCostGroupInfo> getCostGroupInfoList(int var1);

    public TCostGroupInfo getCostGroupInfo(int var1, int var2);

    public void saveCostGroupInfo(TCostGroupInfo var1) throws Exception;

    public void deleteCostGroupInfo(int var1) throws Exception;

    public void deleteAllCostGroupInfo(int var1) throws Exception;

    public void saveBiaoji(TBiaoji var1) throws Exception;

    public TBiaoji getBiaoji(int var1, int var2);

    public List<TBiaoji> getCostGroupBiaojiReport(TBiaoji var1, Object[] var2, int var3, int var4);

    public PageBean getCostGroupBiaojiReportPages(TBiaoji var1, Object[] var2, int var3, int var4);

    public List<TBiaoji> getCostGroupBiaojiReport(int var1);

    public int checkBiaojiNotExistsCostgroupinfo(int var1);

    public Timestamp getCashDate(int var1);

    public double getCashMoney(int var1, int var2);

    public double getCostMoney(int var1, int var2);

	public void saveBusinHis(TBusinHis his);
}


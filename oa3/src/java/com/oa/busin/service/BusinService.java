/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBiaoji
 *  com.common.entity.TBusin
 *  com.common.entity.TCash
 *  com.common.entity.TCashRate
 *  com.common.entity.TContainer
 *  com.common.entity.TCost
 *  com.common.entity.TCostGroup
 *  com.common.entity.TCostGroupInfo
 *  com.common.entity.TCostGroupItem
 *  com.common.entity.TFinanceGroup
 *  com.common.entity.TFinanceGroupItem
 *  com.common.entity.TService
 *  com.common.entity.VBusin
 *  com.common.entity.VBusinAsk
 *  com.common.entity.VBusinInvUser
 *  com.common.entity.VBusinSumReport
 *  com.common.entity.VBusincusreport
 *  com.common.entity.VLog
 *  com.common.util.PageBean
 *  com.oa.busin.service.BusinService
 */
package com.oa.busin.service;

import com.common.entity.TBiaoji;
import com.common.entity.TBusin;
import com.common.entity.TBusinHis;
import com.common.entity.TCash;
import com.common.entity.TCashRate;
import com.common.entity.TContainer;
import com.common.entity.TCost;
import com.common.entity.TCostGroup;
import com.common.entity.TCostGroupInfo;
import com.common.entity.TCostGroupItem;
import com.common.entity.TFinanceGroup;
import com.common.entity.TFinanceGroupItem;
import com.common.entity.TService;
import com.common.entity.VBusin;
import com.common.entity.VBusinAsk;
import com.common.entity.VBusinInvUser;
import com.common.entity.VBusinSumReport;
import com.common.entity.VBusincusreport;
import com.common.entity.VLog;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface BusinService {
    public List<VBusin> getList(VBusin var1, Object[] var2, int var3, int var4);

    public PageBean getPages(VBusin var1, Object[] var2, int var3, int var4);

    public void saveEntity(TBusin var1, List<TContainer> var2, List<TService> var3, List<TCost> var4, List<TCost> var5, List<TCash> var6, int[] var7, String[] var8) throws Exception;

    public void saveEntity(TBusin var1) throws Exception;

    public TBusin getEntityById(Serializable var1);

    public void deleteEntity(int var1, TBusinHis his) throws Exception;

    public void updateEntityState(Serializable var1, short var2, String var3) throws Exception;

    public void updateCostState(Serializable var1, short var2) throws Exception;

    public void saveCash(int var1, List<TContainer> var2, List<TCost> var3, List<TCost> var4, List<TCash> var5, List<TCashRate> var6, List<TCash> var7, List<TCashRate> var8, short var9, short var10, TBusin var11, String[] var12) throws Exception;

    public List<VLog> getLogList(int var1);

    public void updatePrintState(Serializable var1) throws Exception;

    public void updateOrder(int var1, short var2) throws Exception;

    public void updateRecieveMoney(int var1, double var2, int var4) throws Exception;

    public void updateScheduleArchive(int var1, short var2, double var3) throws Exception;

    public void updateScheduleArchive2(int[] var1, short var2) throws Exception;

    public void updateRateArchive(int var1, short var2) throws Exception;

    public void updateRateArchive(int[] var1, short var2) throws Exception;

    public void updateLock(int var1, short var2) throws Exception;

    public void updateLockFlag(int var1, short var2) throws Exception;

    public void updateNewCostFlag2(int var1) throws Exception;

    public void updateCostDupFlag(int var1) throws Exception;

    public List<VBusincusreport> getBusinCusReport(Object[] var1);

    public void updateLocks() throws Exception;

    public void updateLock(int[] var1, short var2) throws Exception;

    public double getProfit(int var1);

    public void updateOrders() throws Exception;

    public List<TFinanceGroup> getGroupList(TFinanceGroup var1, Object[] var2, int var3, int var4);

    public PageBean getGroupPages(TFinanceGroup var1, Object[] var2, int var3, int var4);

    public void saveGroup(TFinanceGroup var1, List<TFinanceGroupItem> var2) throws Exception;

    public TFinanceGroup getGroupById(int var1);

    public void deleteGroup(int var1) throws Exception;

    public int checkName(String var1, Integer var2);

    public void saveGroupDetail(TFinanceGroupItem var1) throws Exception;

    public List<TFinanceGroupItem> getGroupDetail(int var1);

    public List<TFinanceGroup> getGroupValidList();

    public List<TCostGroup> getGroupList(TCostGroup var1, Object[] var2, int var3, int var4);

    public PageBean getGroupPages(TCostGroup var1, Object[] var2, int var3, int var4);

    public void saveGroup(TCostGroup var1, List<TCostGroupItem> var2) throws Exception;

    public TCostGroup getCostGroupById(int var1);

    public void deleteCostGroup(int var1) throws Exception;

    public int checkCostGroupName(String var1, Integer var2);

    public List<TCostGroupItem> getCostGroupDetail(int var1);

    public List<TCostGroup> getCostGroupValidList();

    public List<VBusinInvUser> getInvUserList(VBusinInvUser var1, Object[] var2, int var3, int var4);

    public PageBean getInvUserPages(VBusinInvUser var1, Object[] var2, int var3, int var4);

    public List<VBusinSumReport> getBusinSumReport(VBusinSumReport var1, Object[] var2, int var3, int var4);

    public PageBean getBusinSumReportPages(VBusinSumReport var1, Object[] var2, int var3, int var4);

    public void updatebusinComplete(int var1, short var2) throws Exception;

    public List<TCostGroupInfo> getCostGroupInfoList(int var1);

    public TCostGroupInfo getCostGroupInfo(int var1, int var2);

    public void saveCostGroupInfo(TCostGroupInfo var1) throws Exception;

    public String getMaxRefno();

    public void saveBiaoji(TBiaoji var1) throws Exception;

    public TBiaoji getBiaoji(int var1, int var2);

    public List<TBiaoji> getCostGroupBiaojiReport(TBiaoji var1, Object[] var2, int var3, int var4);

    public PageBean getCostGroupBiaojiReportPages(TBiaoji var1, Object[] var2, int var3, int var4);

    public List<TBiaoji> getCostGroupBiaojiReport(int var1);

    public int checkBiaojiNotExistsCostgroupinfo(int var1);

    public List<VBusinInvUser> getProblemNoticeList(VBusinAsk var1, Object[] var2, int var3, int var4);

    public PageBean getProblemNoticePages(VBusinAsk var1, Object[] var2, int var3, int var4);

}


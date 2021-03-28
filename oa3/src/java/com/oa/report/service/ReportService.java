/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TJiezhuan
 *  com.common.entity.TProfitCus
 *  com.common.entity.VBankmoneyreport
 *  com.common.entity.VBusinprofitcusreport
 *  com.common.entity.VBusinprofitreport
 *  com.common.entity.VBusinreceivereport
 *  com.common.entity.VCostReport
 *  com.common.entity.VCostReport2
 *  com.common.entity.VEmpprofitreport
 *  com.common.util.PageBean
 *  com.oa.report.service.ReportService
 */
package com.oa.report.service;

import com.common.entity.BankAccountReport;
import com.common.entity.CostCashoutReport;
import com.common.entity.TJiezhuan;
import com.common.entity.TProfitCus;
import com.common.entity.VBankmoneyreport;
import com.common.entity.VBusinprofitcusreport;
import com.common.entity.VBusinprofitreport;
import com.common.entity.VBusinreceivereport;
import com.common.entity.VCostReport;
import com.common.entity.VCostReport2;
import com.common.entity.VEmpprofitreport;
import com.common.util.PageBean;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ReportService {
    public List<VBusinreceivereport> getBusinReceiveList(VBusinreceivereport var1, Object[] var2, int var3, int var4);

    public PageBean getBusinReceivePages(VBusinreceivereport var1, Object[] var2, int var3, int var4);

    public VBankmoneyreport getRecieveSum(VBusinreceivereport var1, Object[] var2);

    public List<VBusinprofitreport> getBusinProfitList(VBusinprofitreport var1, Object[] var2, int var3, int var4);

    public VBankmoneyreport getProfitSum(VBusinprofitreport var1, Object[] var2);

    public PageBean getBusinProfitPages(VBusinprofitreport var1, Object[] var2, int var3, int var4);

    public List<VBankmoneyreport> getBankMoneyList(VBankmoneyreport var1, Object[] var2, int var3, int var4);

    public PageBean getBankMoneyPages(VBankmoneyreport var1, Object[] var2, int var3, int var4);

    public List<VCostReport> getCostReport(VCostReport var1, Object[] var2, int var3, int var4);

    public PageBean getCostReportPages(VCostReport var1, Object[] var2, int var3, int var4);

    public double getCostReportSum(VCostReport var1, Object[] var2);

    public List<VCostReport2> getCostReport2(VCostReport2 var1, Object[] var2, int var3, int var4);

    public PageBean getCostReportPages2(VCostReport2 var1, Object[] var2, int var3, int var4);

    public double getCostReportSum2(VCostReport2 var1, Object[] var2);

    public List<VEmpprofitreport> getEmpProfitReport(VBusinprofitreport var1, Object[] var2, int var3, int var4);

    public PageBean getEmpProfitReportPages(VBusinprofitreport var1, Object[] var2, int var3, int var4);

    public VBankmoneyreport getEmpProfitSum(VBusinprofitreport var1, Object[] var2);

    public List<VBusinprofitcusreport> getBusinProfitCusList(VBusinprofitcusreport var1, Object[] var2, int var3, int var4);

    public PageBean getBusinProfitCusPages(VBusinprofitcusreport var1, Object[] var2, int var3, int var4);

    public double getBusinProfitCusSum(VBusinprofitcusreport var1, Object[] var2);

    public double getBusinProfitCusSum2(VBusinprofitcusreport var1, Object[] var2);

    public TProfitCus getProfitCusByCusid(int var1);

    public void updateProfitCus(TProfitCus var1) throws Exception;

    public void updatePayComplete(int var1, short var2) throws Exception;

    public void updatePayComplete2(int[] var1, short var2) throws Exception;

    public List<TJiezhuan> getJiezhuanList(TJiezhuan var1, Object[] var2, int var3, int var4);

    public PageBean getJiezhuanPages(TJiezhuan var1, Object[] var2, int var3, int var4);

    public TJiezhuan getJiezhuan(int var1);

    public void saveJiezhuan(TJiezhuan var1) throws Exception;

	public Double getBusinProfitSum(VBusinprofitreport proQuery, Object[] obj);

	public List<CostCashoutReport> getCostCashoutReport(CostCashoutReport costCashoutReport, int pageNow, int pageSize) throws ParseException;

	public PageBean getCostCashoutReportPages(CostCashoutReport costCashoutReport, int pageNow, int pageSize) throws ParseException;

	public Map<String, CostCashoutReport> getAllFinanceMap();

	public List<BankAccountReport> getBankAccountReport(BankAccountReport bankAccount, int pageNow, int pageSize, Map<Integer, BankAccountReport> bankMap) throws Exception;

	public PageBean getBankAccountReportPages(BankAccountReport bankAccount, int pageNow, int pageSize) throws Exception;
}


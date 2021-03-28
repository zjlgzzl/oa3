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
 *  com.oa.busin.dao.BusinDAO
 *  com.oa.report.dao.ReportDAO
 *  com.oa.report.service.ReportService
 *  com.oa.report.service.ReportServiceImpl
 */
package com.oa.report.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

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
import com.oa.busin.dao.BusinDAO;
import com.oa.report.dao.ReportDAO;

public class ReportServiceImpl
implements ReportService {
    private ReportDAO reportDAO;
    private BusinDAO businDAO;

    public List<VBusinreceivereport> getBusinReceiveList(VBusinreceivereport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBusinReceiveList(query, obj, pageNow, pageSize);
    }

    public PageBean getBusinReceivePages(VBusinreceivereport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBusinReceivePages(query, obj, pageNow, pageSize);
    }

    public VBankmoneyreport getRecieveSum(VBusinreceivereport query, Object[] obj) {
        return this.reportDAO.getRecieveSum(query, obj);
    }

    public List<VBusinprofitreport> getBusinProfitList(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBusinProfitList(query, obj, pageNow, pageSize);
    }

    public PageBean getBusinProfitPages(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBusinProfitPages(query, obj, pageNow, pageSize);
    }

    public VBankmoneyreport getProfitSum(VBusinprofitreport query, Object[] obj) {
        return this.reportDAO.getProfitSum(query, obj);
    }

    public List<VBankmoneyreport> getBankMoneyList(VBankmoneyreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBankMoneyList(query, obj, pageNow, pageSize);
    }

    public PageBean getBankMoneyPages(VBankmoneyreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBankMoneyPages(query, obj, pageNow, pageSize);
    }

    public List<VCostReport> getCostReport(VCostReport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getCostReport(query, obj, pageNow, pageSize);
    }

    public PageBean getCostReportPages(VCostReport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getCostReportPages(query, obj, pageNow, pageSize);
    }

    public double getCostReportSum(VCostReport query, Object[] obj) {
        return this.reportDAO.getCostReportSum(query, obj);
    }

    public List<VCostReport2> getCostReport2(VCostReport2 query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getCostReport2(query, obj, pageNow, pageSize);
    }

    public PageBean getCostReportPages2(VCostReport2 query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getCostReportPages2(query, obj, pageNow, pageSize);
    }

    public double getCostReportSum2(VCostReport2 query, Object[] obj) {
        return this.reportDAO.getCostReportSum2(query, obj);
    }

    public List<VEmpprofitreport> getEmpProfitReport(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getEmpProfitReport(query, obj, pageNow, pageSize);
    }

    public PageBean getEmpProfitReportPages(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getEmpProfitReportPages(query, obj, pageNow, pageSize);
    }

    public VBankmoneyreport getEmpProfitSum(VBusinprofitreport query, Object[] obj) {
        return this.reportDAO.getEmpProfitSum(query, obj);
    }

    public List<VBusinprofitcusreport> getBusinProfitCusList(VBusinprofitcusreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBusinProfitCusList(query, obj, pageNow, pageSize);
    }

    public PageBean getBusinProfitCusPages(VBusinprofitcusreport query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getBusinProfitCusPages(query, obj, pageNow, pageSize);
    }

    public double getBusinProfitCusSum(VBusinprofitcusreport query, Object[] obj) {
        return this.reportDAO.getBusinProfitCusSum(query, obj);
    }

    public double getBusinProfitCusSum2(VBusinprofitcusreport query, Object[] obj) {
        return this.reportDAO.getBusinProfitCusSum2(query, obj);
    }

    public TProfitCus getProfitCusByCusid(int id) {
        return this.reportDAO.getProfitCusByCusid(id);
    }

    public void updateProfitCus(TProfitCus entity) throws Exception {
        this.reportDAO.updateProfitCus(entity);
    }

    public void updatePayComplete(int businid, short flag) throws Exception {
        double cmoney = 0.0;
        if (flag == 1) {
            cmoney = this.businDAO.getBusinLimit(businid);
        }
        this.reportDAO.updatePayComplete(businid, flag, cmoney);
    }

    public void updatePayComplete2(int[] businid, short flag) throws Exception {
        for (int i = 0; i < businid.length; ++i) {
            double cmoney = 0.0;
            if (flag == 1) {
                cmoney = this.businDAO.getBusinLimit(businid[i]);
            }
            this.reportDAO.updatePayComplete(businid[i], flag, cmoney);
        }
    }

    public List<TJiezhuan> getJiezhuanList(TJiezhuan query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getJiezhuanList(query, obj, pageNow, pageSize);
    }

    public PageBean getJiezhuanPages(TJiezhuan query, Object[] obj, int pageNow, int pageSize) {
        return this.reportDAO.getJiezhuanPages(query, obj, pageNow, pageSize);
    }

    public TJiezhuan getJiezhuan(int businId) {
        return this.reportDAO.getJiezhuan(businId);
    }

    public void saveJiezhuan(TJiezhuan entity) throws Exception {
        this.reportDAO.saveJiezhuan(entity);
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public void setBusinDAO(BusinDAO businDAO) {
        this.businDAO = businDAO;
    }

	public Double getBusinProfitSum(VBusinprofitreport query, Object[] obj) {
		return this.reportDAO.getBusinProfitSum(query, obj);
	}

	@Override
	public List<CostCashoutReport> getCostCashoutReport(CostCashoutReport costCashoutReport, int pageNow,
			int pageSize) throws ParseException{
		return reportDAO.getCostCashoutReport(costCashoutReport,pageNow,pageSize);
	}

	@Override
	public PageBean getCostCashoutReportPages(CostCashoutReport costCashoutReport, int pageNow, int pageSize) throws ParseException{
		return reportDAO.getCostCashoutReportPages(costCashoutReport,pageNow,pageSize);
	}

	@Override
	public Map<String, CostCashoutReport> getAllFinanceMap() {
		return reportDAO.getAllFinanceMap();
	}

	@Override
	public List<BankAccountReport> getBankAccountReport(BankAccountReport bankAccount, int pageNow, int pageSize, Map<Integer, BankAccountReport> bankMap) throws Exception {
		return reportDAO.getBankAccountReport(bankAccount,pageNow,pageSize,bankMap);
	}

	@Override
	public PageBean getBankAccountReportPages(BankAccountReport bankAccount, int pageNow, int pageSize) throws Exception {
		return reportDAO.getBankAccountReportPages(bankAccount,pageNow,pageSize);
	}

}


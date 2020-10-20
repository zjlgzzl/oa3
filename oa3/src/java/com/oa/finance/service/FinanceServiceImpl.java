/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.auth.dao.UserDAO
 *  com.common.entity.TFinance
 *  com.common.entity.TFinanceDetail
 *  com.common.entity.VFinance
 *  com.common.entity.VOutReport
 *  com.common.util.PageBean
 *  com.oa.finance.dao.FinanceDAO
 *  com.oa.finance.service.FinanceService
 *  com.oa.finance.service.FinanceServiceImpl
 */
package com.oa.finance.service;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.UserDAO;
import com.common.entity.TFinance;
import com.common.entity.TFinanceDetail;
import com.common.entity.VFinance;
import com.common.entity.VOutReport;
import com.common.util.PageBean;
import com.oa.finance.dao.FinanceDAO;
import com.oa.finance.service.FinanceService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

public class FinanceServiceImpl
implements FinanceService {
    private FinanceDAO financeDAO;
    private UserDAO userDAO;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

    public List<VFinance> getList(VFinance query, Object[] obj, int pageNow, int pageSize) {
        return this.financeDAO.getList(query, obj, pageNow, pageSize);
    }

    public PageBean getPages(VFinance query, Object[] obj, int pageNow, int pageSize) {
        return this.financeDAO.getPages(query, obj, pageNow, pageSize);
    }

    public Double getSumMoney(VFinance query, Object[] obj) {
        return this.financeDAO.getSumMoney(query, obj);
    }

    public void saveEntity(TFinance entity, List<TFinanceDetail> detail) throws Exception {
        List detailList;
        if (entity.getCId() == null) {
            String date = this.formatter.format(entity.getCDate());
            date = entity.getCType() == 1 ? "R-" + date : "P-" + date;
            String financeNo = this.financeDAO.getMaxBillNo(date);
            entity.setCFinanceNo(financeNo);
            entity.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
            entity.setCState(Short.valueOf((short)1));
            entity.setCArchivingFlag(Short.valueOf((short)1));
        }
        this.financeDAO.saveEntity(entity);
        if (detail != null && detail.size() > 0) {
            for (int i = 0; i < detail.size(); ++i) {
                if (detail.get(i) == null) continue;
                if (detail.get(i).getCId() == null) {
                    detail.get(i).setTFinance(entity);
                }
                this.financeDAO.saveDetailEntity(detail.get(i));
            }
        }
        int dbid = 0;
        int tmpid = 0;
        boolean found = false;
        if (entity.getCId() != null && (detailList = this.financeDAO.getDetailList(entity.getCId().intValue())) != null && detailList.size() > 0) {
            for (int i = 0; i < detailList.size(); ++i) {
                dbid = ((TFinanceDetail)detailList.get(i)).getCId();
                if (detail != null && detail.size() > 0) {
                    for (int j = 0; j < detail.size(); ++j) {
                        if (detail.get(j) == null || detail.get(j).getCId() == null || dbid != (tmpid = detail.get(j).getCId().intValue())) continue;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    this.financeDAO.deleteDetailEntity(dbid);
                }
                found = false;
            }
        }
    }

    public TFinance getEntityById(Serializable id) {
        return this.financeDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.financeDAO.deleteAllDetailEntity(((Integer)id).intValue());
        this.financeDAO.deleteEntity(id);
    }

    public List<TFinanceDetail> getDetailList(int pid) {
        return this.financeDAO.getDetailList(pid);
    }

    public void saveDetailEntity(TFinanceDetail entity) throws Exception {
        this.financeDAO.saveDetailEntity(entity);
    }

    public void deleteDetailEntity(int id) throws Exception {
        this.financeDAO.deleteDetailEntity(id);
    }

    public void deleteAllDetailEntity(int pid) throws Exception {
        this.financeDAO.deleteAllDetailEntity(pid);
    }

    public int getNoticeCount() {
        return this.financeDAO.getNoticeCount();
    }

    public void updateArchivingState(int id, short state) throws Exception {
        this.financeDAO.updateArchivingState(id, state);
    }

    public void updateRemarks(int id, String remarks) throws Exception {
        this.financeDAO.updateRemarks(id, remarks);
    }

    public void updateRemarks2(int id, String remarks) throws Exception {
        this.financeDAO.updateRemarks2(id, remarks);
    }

    public List<VOutReport> getOutReportList(VOutReport query, Object[] obj) {
        return this.financeDAO.getOutReportList(query, obj);
    }

    public void updateArchiveflag(int[] financeIds, short flag) throws Exception {
        for (int i = 0; i < financeIds.length; ++i) {
            TFinance ff = this.financeDAO.getEntityById((Serializable)Integer.valueOf(financeIds[i]));
            ff.setCArchivingFlag(Short.valueOf(flag));
            this.financeDAO.saveEntity(ff);
        }
    }

    public void setFinanceDAO(FinanceDAO financeDAO) {
        this.financeDAO = financeDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}


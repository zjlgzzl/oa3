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

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.UserDAO;
import com.common.entity.TBusin;
import com.common.entity.TFinance;
import com.common.entity.TFinanceDetail;
import com.common.entity.VFinance;
import com.common.entity.VOutReport;
import com.common.util.PageBean;
import com.oa.busin.dao.BusinDAO;
import com.oa.finance.dao.FinanceDAO;

public class FinanceServiceImpl
implements FinanceService {
    private FinanceDAO financeDAO;
    private UserDAO userDAO;
    private BusinDAO businDAO;
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
        if (entity.getCId() == null) {
            String date = this.formatter.format(entity.getCDate());
            date = entity.getCType() == 1 ? "R-" + date : "P-" + date;
            String financeNo = this.financeDAO.getMaxBillNo(date);
            entity.setCFinanceNo(financeNo);
            entity.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
            entity.setCState(Short.valueOf((short)1));
            entity.setCArchivingFlag(Short.valueOf((short)1));
        }else {
        	this.financeDAO.deleteAllDetailEntity(entity.getCId());
        }
        this.financeDAO.saveEntity(entity);
        if (detail != null && detail.size() > 0) {
            for (int i = 0; i < detail.size(); ++i) {
                if (detail.get(i) == null) continue;
                if (detail.get(i).getCId() == null) {
                    detail.get(i).setTFinance(entity);
                }
                this.financeDAO.saveDetailEntity(detail.get(i));
                //收入自动销账、自动归档
                if(entity.getCType() == 1) {
                	TBusin busin = businDAO.getBusinByBillNo(detail.get(i).getCRemarks().trim());
                	if(busin != null) {
                		busin.setCRecieveMoney(new BigDecimal((busin.getCRecieveMoney() == null? 0:busin.getCRecieveMoney())).add(new BigDecimal(detail.get(i).getCMoney())).doubleValue());
                		busin.setCRecieveRemarks((StringUtils.isBlank(busin.getCRecieveRemarks())?"":(busin.getCRecieveRemarks() + ",")) + entity.getCFinanceNo());
                		if(new BigDecimal(busin.getCCashMoney1() == null?0:busin.getCCashMoney1()).setScale(2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(busin.getCCashMoney2() == null?0:busin.getCCashMoney2()).setScale(2, BigDecimal.ROUND_HALF_UP))
                				.add(new BigDecimal(busin.getCCashMoney4() == null?0:busin.getCCashMoney4()).setScale(2, BigDecimal.ROUND_HALF_UP)).doubleValue()==(new BigDecimal(busin.getCRecieveMoney()== null?0:busin.getCRecieveMoney()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())) {
                			busin.setCScheduleArchive((short)1);
                		}else {
                			busin.setCScheduleArchive((short)0);
                		}
                		businDAO.saveMergeEntity(busin);
                	}
                }
            }
        }
		/*
		 * int dbid = 0; int tmpid = 0; boolean found = false; if (entity.getCId() !=
		 * null && (detailList =
		 * this.financeDAO.getDetailList(entity.getCId().intValue())) != null &&
		 * detailList.size() > 0) { for (int i = 0; i < detailList.size(); ++i) { dbid =
		 * ((TFinanceDetail)detailList.get(i)).getCId(); if (detail != null &&
		 * detail.size() > 0) { for (int j = 0; j < detail.size(); ++j) { if
		 * (detail.get(j) == null || detail.get(j).getCId() == null || dbid != (tmpid =
		 * detail.get(j).getCId().intValue())) continue; found = true; break; } } if
		 * (!found) { this.financeDAO.deleteDetailEntity(dbid); } found = false; } }
		 */
    }

	public void updateRecover(TFinance entity) throws Exception {
		if(entity.getCId() != null) {
			List<TFinanceDetail> detailList2 = financeDAO.getDetailList(entity.getCId());
			if(detailList2 != null && detailList2.size() > 0) {
				for (TFinanceDetail tFinanceDetail : detailList2) {
					TBusin busin = businDAO.getBusinByBillNo(tFinanceDetail.getCRemarks().trim());
					if(busin != null) {
						busin.setCRecieveMoney(new BigDecimal(busin.getCRecieveMoney() == null?0:busin.getCRecieveMoney()).subtract(new BigDecimal(tFinanceDetail.getCMoney() == null?0:tFinanceDetail.getCMoney())).doubleValue());
						if(StringUtils.isNotBlank(busin.getCRecieveRemarks()) && busin.getCRecieveRemarks().contains(entity.getCFinanceNo())) {
							String newRemarks = "";
							int index = busin.getCRecieveRemarks().indexOf(entity.getCFinanceNo());
							if(index > 0) {
								newRemarks = busin.getCRecieveRemarks().substring(0, index - 1) + busin.getCRecieveRemarks().substring(index + entity.getCFinanceNo().length());
							}else {
								if(busin.getCRecieveRemarks().length() != entity.getCFinanceNo().length()) {
									newRemarks = busin.getCRecieveRemarks().substring(entity.getCFinanceNo().length() + 1);
								}
							}
							busin.setCRecieveRemarks(newRemarks);
						}
						if(new BigDecimal(busin.getCCashMoney1() == null?0:busin.getCCashMoney1()).add(new BigDecimal(busin.getCCashMoney2() == null?0:busin.getCCashMoney2()))
                				.add(new BigDecimal(busin.getCCashMoney4() == null?0:busin.getCCashMoney4())).doubleValue()==(new BigDecimal(busin.getCRecieveMoney()== null?0:busin.getCRecieveMoney()).doubleValue())) {
                			busin.setCScheduleArchive((short)1);
                		}else {
                			busin.setCScheduleArchive((short)0);
                		}
						businDAO.saveEntity(busin);
					}
				}
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

	public BusinDAO getBusinDAO() {
		return businDAO;
	}

	public void setBusinDAO(BusinDAO businDAO) {
		this.businDAO = businDAO;
	}
    
}


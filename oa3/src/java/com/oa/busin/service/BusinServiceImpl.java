package com.oa.busin.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.UserDAO;
import com.common.entity.TBiaoji;
import com.common.entity.TBusin;
import com.common.entity.TBusinHis;
import com.common.entity.TBusinLog;
import com.common.entity.TBusinType;
import com.common.entity.TCash;
import com.common.entity.TCashRate;
import com.common.entity.TContainer;
import com.common.entity.TCost;
import com.common.entity.TCostGroup;
import com.common.entity.TCostGroupInfo;
import com.common.entity.TCostGroupItem;
import com.common.entity.TDelDn;
import com.common.entity.TFinanceGroup;
import com.common.entity.TFinanceGroupItem;
import com.common.entity.TJiezhuan;
import com.common.entity.TRate;
import com.common.entity.TService;
import com.common.entity.VBusin;
import com.common.entity.VBusinAsk;
import com.common.entity.VBusinInvUser;
import com.common.entity.VBusinSumReport;
import com.common.entity.VBusincusreport;
import com.common.entity.VCostGroupSum;
import com.common.entity.VLog;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.BusinTypeDAO;
import com.oa.base.dao.CostItemDAO;
import com.oa.busin.dao.BusinDAO;
import com.oa.busin.dao.BusinLogDAO;
import com.oa.busin.dao.BusinServiceDAO;
import com.oa.busin.dao.CashDAO;
import com.oa.busin.dao.CashRateDAO;
import com.oa.busin.dao.ContainerDAO;
import com.oa.busin.dao.CostDAO;
import com.oa.rate.dao.RateDAO;
import com.oa.report.dao.ReportDAO;

public class BusinServiceImpl
implements BusinService {
    private BusinDAO businDAO;
    private UserDAO userDAO;
    private ContainerDAO containerDAO;
    private BusinServiceDAO businServiceDAO;
    private CostDAO costDAO;
    private CashDAO cashDAO;
    private BusinTypeDAO businTypeDAO;
    private BusinLogDAO businLogDAO;
    private CashRateDAO cashRateDAO;
    private CostItemDAO costItemDAO;
    private RateDAO rateDAO;
    private ReportDAO reportDAO;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat formatter2 = new SimpleDateFormat("MM/yyyy");

    public void setCashRateDAO(CashRateDAO cashRateDAO) {
        this.cashRateDAO = cashRateDAO;
    }

    public List<VBusin> getList(VBusin query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getList(query, obj, pageNow, pageSize);
    }

    public PageBean getPages(VBusin query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getPages(query, obj, pageNow, pageSize);
    }

    public void saveEntity(TBusin busin, List<TContainer> cons, List<TService> services, List<TCost> costs, List<TCost> costsGroup, List<TCash> cash, int[] delCost, String[] logs) throws Exception {
        List serTmpList;
        List conTmpList;
        TBusinType tmp;
        int businTypeId;
        boolean found = false;
        Integer businId = busin.getCId();
        Integer dbid = 0;
        Integer businTmpId = 0;
        boolean create = false;
        String logBaseinfo = logs[0].toString();
        String logRemarks = logs[1].toString();
        StringBuffer logContainer = new StringBuffer();
        StringBuffer logCostinfo = new StringBuffer();
        List<TCostGroupInfo> groupInfoList = new ArrayList();
        StringBuffer result = new StringBuffer();
        TBusinLog businLog = new TBusinLog();
        synchronized (this) {
        	if (businId == null) {
                String date = this.formatter.format(busin.getCDate());
                businTypeId = busin.getTBusinType().getCId();
                tmp = this.businTypeDAO.getEntityById((Serializable)Integer.valueOf(businTypeId));
                String billno = String.valueOf(tmp.getCName()) + "-" + this.businDAO.getMaxBillNo(date);
                busin.setCBillNo(billno);
                busin.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                busin.setCCreateDate(CommonUtil.getDatetime());
                busin.setCCostPrintFlag(Short.valueOf((short)0));
                create = true;
            } else {
                int pp = busin.getCBillNo().indexOf("-");
                businTypeId = busin.getTBusinType().getCId();
                tmp = this.businTypeDAO.getEntityById((Serializable)Integer.valueOf(businTypeId));
                busin.setCBillNo(String.valueOf(tmp.getCName()) + "-" + busin.getCBillNo().substring(pp + 1));
            }
            businLog.setCDate(CommonUtil.getDatetime());
            businLog.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
            businLog.setTBusin(busin);
            if (create) {
                result.append("新建单据，单据编号：" + busin.getCBillNo()).append("。");
            }
            busin.setCLockFlag(Short.valueOf((short)0));
            busin.setCOrder(Integer.valueOf(0));
            if (busin.getCId() != null && busin.getCId() > 0) {
                int vatCount = this.businDAO.getBusinVatCount(businId.intValue());
                if (busin.getCArrivalDate() != null && !"".equals(busin.getCArrivalDate()) && vatCount == 0) {
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(busin.getCArrivalDate());
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(CommonUtil.getDatetime());
                    int days = (int)((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / 86400000L);
                    if (days >= 7) {
                        busin.setCOrder(Integer.valueOf(1));
                    }
                }
            }
            if (busin.getCCostDate() != null) {
                if (busin.getCRefno() == null || "".equals(busin.getCRefno().trim())) {
                    busin.setCRefno(this.businDAO.getMaxRefno());
                }
            } else {
                busin.setCRefno(null);
            }
            if (busin.getTSalerByCApplyby() == null || busin.getTSalerByCApplyby().getCId() == null || busin.getTSalerByCApplyby().getCId() == 0) {
                busin.setTSalerByCApplyby(null);
            }
            double costSumMoney1 = 0.0;
            double costSumMoney2 = 0.0;
            if (costs != null && costs.size() > 0) {
                for (int i = 0; i < costs.size(); ++i) {
                    if (costs.get(i) == null || !(costs.get(i).getTCostItem() != null && costs.get(i).getTCostItem().getCId() != null && costs.get(i).getTCostItem().getCId() != 0 || costs.get(i).getCMoney() != null || costs.get(i).getCRe() != null && !"".equals(costs.get(i).getCRe()) || !"".equals(costs.get(i).getCRemarks()) && costs.get(i).getCRemarks() != null || costs.get(i).getCRemarks2() != null && !"".equals(costs.get(i).getCRemarks2()) || costs.get(i).getCRemarks3() != null && !"".equals(costs.get(i).getCRemarks3()) || costs.get(i).getCRemarks4() != null && !"".equals(costs.get(i).getCRemarks4())) && (costs.get(i).getCRemarks5() == null || "".equals(costs.get(i).getCRemarks5()))) continue;
                    if (costs.get(i).getCId() == null && costs.get(i).getTCostGroup() != null && costs.get(i).getTCostGroup().getCId() != null && costs.get(i).getTCostGroup().getCId() > 0) {
                        busin.setCAddGroup(Short.valueOf((short)1));
                    }
                    if (costs.get(i).getCMoney() == null) continue;
                    costSumMoney1 += costs.get(i).getCMoney().doubleValue();
                }
            }
            if (busin.getCId() != null) {
                busin.setCCashDate(this.businDAO.getCashDate(busin.getCId().intValue()));
            }
            if (busin.getCId() != null && busin.getCId() > 0) {
                busin.setCCashMoney1(Double.valueOf(this.businDAO.getCashMoney(busin.getCId().intValue(), 1)));
                busin.setCCashMoney2(Double.valueOf(this.businDAO.getCashMoney(busin.getCId().intValue(), 2)));
                busin.setCCashMoney4(Double.valueOf(this.businDAO.getCashMoney(busin.getCId().intValue(), 4)));
            }
            
            if (busin.getCId() != null && busin.getCId() > 0) {
                groupInfoList = this.businDAO.getCostGroupInfoList(busin.getCId().intValue());
                if (costsGroup != null && costsGroup.size() > 0) {
                    block1: for (int i = 0; i < costsGroup.size(); ++i) {
                        if (costsGroup.get(i) == null || !(costsGroup.get(i).getTCostItem() != null && costsGroup.get(i).getTCostItem().getCId() != null && costsGroup.get(i).getTCostItem().getCId() != 0 || costsGroup.get(i).getCMoney() != null || costsGroup.get(i).getCRe() != null && !"".equals(costsGroup.get(i).getCRe()) || !"".equals(costsGroup.get(i).getCRemarks()) && costsGroup.get(i).getCRemarks() != null || costsGroup.get(i).getCRemarks2() != null && !"".equals(costsGroup.get(i).getCRemarks2()) || costsGroup.get(i).getCRemarks3() != null && !"".equals(costsGroup.get(i).getCRemarks3()) || costsGroup.get(i).getCRemarks4() != null && !"".equals(costsGroup.get(i).getCRemarks4())) && (costsGroup.get(i).getCRemarks5() == null || "".equals(costsGroup.get(i).getCRemarks5())) || groupInfoList == null || groupInfoList.size() <= 0) continue;
                        for (int kk = 0; kk < groupInfoList.size(); ++kk) {
                            if (costsGroup.get(i).getTCostGroup().getCId() != ((TCostGroupInfo)groupInfoList.get(kk)).getTCostGroup().getCId()) continue;
                            if (((TCostGroupInfo)groupInfoList.get(kk)).getCExchange() == null || ((TCostGroupInfo)groupInfoList.get(kk)).getCExchange() == 0.0) continue block1;
                            double criel = 0.0;
                            if (costsGroup.get(i).getCRiel() != null) {
                                criel = costsGroup.get(i).getCRiel();
                            }
                            double tmp2 = criel / ((TCostGroupInfo)groupInfoList.get(kk)).getCExchange();
                            costSumMoney2 += tmp2;
                            if (costsGroup.get(i).getCMoney() == null) continue block1;
                            costSumMoney2 += costsGroup.get(i).getCMoney().doubleValue();
                            continue block1;
                        }
                    }
                }
            }
            busin.setCCostSum1(Double.valueOf(costSumMoney1));
            busin.setCCostSum2(Double.valueOf(costSumMoney2));
            this.businDAO.saveEntity(busin);	
		}
        
        found = false;
        if (costsGroup != null && costsGroup.size() > 0) {
            boolean costFlag = false;
            boolean userFlag = false;
            for (int i = 0; i < costsGroup.size(); ++i) {
                if (costsGroup.get(i) == null || !(costsGroup.get(i).getTCostItem() != null && costsGroup.get(i).getTCostItem().getCId() != null && costsGroup.get(i).getTCostItem().getCId() != 0 || costsGroup.get(i).getCMoney() != null || costsGroup.get(i).getCRe() != null && !"".equals(costsGroup.get(i).getCRe()) || !"".equals(costsGroup.get(i).getCRemarks()) && costsGroup.get(i).getCRemarks() != null || costsGroup.get(i).getCRemarks2() != null && !"".equals(costsGroup.get(i).getCRemarks2()) || costsGroup.get(i).getCRemarks3() != null && !"".equals(costsGroup.get(i).getCRemarks3()) || costsGroup.get(i).getCRemarks4() != null && !"".equals(costsGroup.get(i).getCRemarks4())) && (costsGroup.get(i).getCRemarks5() == null || "".equals(costsGroup.get(i).getCRemarks5()))) continue;
                if (costsGroup.get(i).getTCostItem() == null || costsGroup.get(i).getTCostItem().getCId() == null || costsGroup.get(i).getTCostItem().getCId() == 0) {
                    costsGroup.get(i).setTCostItem(null);
                }
                if (costsGroup.get(i).getTCostGroup() == null || costsGroup.get(i).getTCostGroup().getCId() == null || costsGroup.get(i).getTCostGroup().getCId() == 0) {
                    costsGroup.get(i).setTCostGroup(null);
                }
                if (costsGroup.get(i).getCId() == null) {
                    if (busin.getCState() > 1) {
                        costsGroup.get(i).setCNewCostFlag(Short.valueOf((short)1));
                    }
                    costsGroup.get(i).setTBusin(busin);
                    costsGroup.get(i).setCPrint(Short.valueOf((short)0));
                    if (CommonUtil.getUserId() == 2) {
                        costsGroup.get(i).setCHidden(Short.valueOf((short)1));
                    }
                    if (costsGroup.get(i).getTCostGroup() != null && costsGroup.get(i).getTCostGroup().getCId() != null && costsGroup.get(i).getTCostGroup().getCId() > 0) {
                        busin.setCAddGroup(Short.valueOf((short)1));
                    }
                    costsGroup.get(i).setTUserByCCreateUserid(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    logCostinfo.append("<br/>&nbsp;&nbsp;添加Cost Info，").append("Cost Item：");
                    if (costsGroup.get(i).getTCostItem() != null && costsGroup.get(i).getTCostItem().getCId() != null) {
                        logCostinfo.append(this.costItemDAO.getEntityById((Serializable)costsGroup.get(i).getTCostItem().getCId()).getCName());
                    } else {
                        logCostinfo.append("PLEASE CHOOSE");
                    }
                    logCostinfo.append("。");
                    if (costsGroup.get(i).getCMoney() != null) {
                        logCostinfo.append("$：").append(costsGroup.get(i).getCMoney()).append("；");
                    }
                    if (costsGroup.get(i).getCRiel() != null) {
                        logCostinfo.append("Riel：").append(costsGroup.get(i).getCRiel()).append("；");
                    }
                    costsGroup.get(i).setCCreateDate(CommonUtil.getDatetime());
                } else {
                    Object[] obj = this.costDAO.getCostInfo(costsGroup.get(i).getCId().intValue());
                    StringBuffer tmp3 = new StringBuffer();
                    boolean editFlag = false;
                    if (!(costsGroup.get(i).getTCostItem() != null && obj[0] != null && costsGroup.get(i).getTCostItem().getCId().equals(Integer.parseInt(obj[0].toString())) || costsGroup.get(i).getTCostItem() == null && obj[0] == null)) {
                        tmp3.append("Cost Item：");
                        if (obj[0] == null) {
                            tmp3.append("PLEASE CHOOSE");
                        } else {
                            tmp3.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        tmp3.append(" 改为 ");
                        if (costsGroup.get(i).getTCostItem() == null) {
                            tmp3.append("PLEASE CHOOSE");
                        } else {
                            tmp3.append(this.costItemDAO.getEntityById((Serializable)costsGroup.get(i).getTCostItem().getCId()).getCName());
                        }
                        if (busin.getCState() > 1) {
                            costsGroup.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp3.append("；");
                        editFlag = true;
                    }
                    if (!(costsGroup.get(i).getCMoney() == null && obj[1] == null || costsGroup.get(i).getCMoney() != null && obj[1] != null && costsGroup.get(i).getCMoney() == Double.parseDouble(obj[1].toString()))) {
                        tmp3.append("$：");
                        if (obj[1] == null) {
                            tmp3.append("0");
                        } else {
                            tmp3.append(new BigDecimal(Double.parseDouble(obj[1].toString())).setScale(2, 4));
                        }
                        tmp3.append(" 改为 ");
                        if (costsGroup.get(i).getCMoney() == null) {
                            tmp3.append("0");
                        } else {
                            tmp3.append(new BigDecimal(costsGroup.get(i).getCMoney()).setScale(2, 4));
                        }
                        if (busin.getCState() > 1) {
                            costsGroup.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp3.append("；");
                        editFlag = true;
                    }
                    if (!(costsGroup.get(i).getCRiel() == null && obj[8] == null || costsGroup.get(i).getCRiel() != null && obj[8] != null && costsGroup.get(i).getCRiel() == Double.parseDouble(obj[8].toString()))) {
                        tmp3.append("Riel：");
                        if (obj[8] == null) {
                            tmp3.append("0");
                        } else {
                            tmp3.append(new BigDecimal(Double.parseDouble(obj[8].toString())).setScale(2, 4));
                        }
                        tmp3.append(" 改为 ");
                        if (costsGroup.get(i).getCRiel() == null) {
                            tmp3.append("0");
                        } else {
                            tmp3.append(new BigDecimal(costsGroup.get(i).getCRiel()).setScale(2, 4));
                        }
                        if (busin.getCState() > 1) {
                            costsGroup.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp3.append("；");
                        editFlag = true;
                    }
                    if (!(costsGroup.get(i).getCRe() == null && obj[2] == null || costsGroup.get(i).getCRe() != null && obj[2] != null && costsGroup.get(i).getCRe().equals(obj[2].toString()))) {
                        tmp3.append("Re：");
                        if (obj[8] == null) {
                            tmp3.append("''");
                        } else {
                            tmp3.append(obj[8].toString());
                        }
                        tmp3.append(" 改为 ");
                        if (costsGroup.get(i).getCRe() == null) {
                            tmp3.append("''");
                        } else {
                            tmp3.append(costsGroup.get(i).getCRe());
                        }
                        if (busin.getCState() > 1) {
                            costsGroup.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp3.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;修改Cost Info，原Cost Item：");
                        if (obj[0] != null && Integer.parseInt(obj[0].toString()) > 0) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        logCostinfo.append("。");
                        logCostinfo.append(tmp3);
                    }
                }
                if (costsGroup.get(i).getCState() == null || costsGroup.get(i).getCState() != 3 && costsGroup.get(i).getCState() != 5) {
                    if (busin.getCState() == 1) {
                        costsGroup.get(i).setCState(Short.valueOf((short)1));
                    } else if (busin.getCState() == 2) {
                        if (!costFlag || !userFlag || costsGroup.get(i).getCMoney() == null) {
                            costsGroup.get(i).setCState(Short.valueOf((short)1));
                        } else {
                            costsGroup.get(i).setCState(Short.valueOf((short)2));
                        }
                    }
                }
                this.costDAO.saveCost(costsGroup.get(i));
                costFlag = false;
                userFlag = false;
                boolean groupInfofound = false;
                if (groupInfoList != null && groupInfoList.size() > 0) {
                    for (int kk = 0; kk < groupInfoList.size(); ++kk) {
                        if (costsGroup.get(i).getTCostGroup().getCId() != ((TCostGroupInfo)groupInfoList.get(kk)).getTCostGroup().getCId()) continue;
                        groupInfofound = true;
                        break;
                    }
                }
                if (groupInfofound) continue;
                TCostGroupInfo groupInfo = new TCostGroupInfo();
                groupInfo.setCBusinId(busin.getCId());
                groupInfo.setTCostGroup(costsGroup.get(i).getTCostGroup());
                this.businDAO.saveCostGroupInfo(groupInfo);
                groupInfoList.add(groupInfo);
                groupInfo = null;
            }
        }
        dbid = 0;
        businTmpId = 0;
        found = false;
        if (businId != null) {
            List costTmpList = this.costDAO.getList(businId.intValue(), (short)1);
            if (costsGroup != null && costsGroup.size() > 0) {
                for (int i = 0; i < costTmpList.size(); ++i) {
                    found = false;
                    dbid = ((TCost)costTmpList.get(i)).getCId();
                    if (costsGroup != null && costsGroup.size() > 0) {
                        for (int j = 0; j < costsGroup.size(); ++j) {
                            if (costsGroup.get(j) == null || costsGroup.get(j).getCId() == null || dbid != (businTmpId = costsGroup.get(j).getCId())) continue;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;删除Cost Info，Cost Item：");
                        if (((TCost)costTmpList.get(i)).getTCostItem() != null && ((TCost)costTmpList.get(i)).getTCostItem().getCId() != null) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)((TCost)costTmpList.get(i)).getTCostItem().getCId()).getCName()).append("；");
                        }
                        logCostinfo.append("$：");
                        if (((TCost)costTmpList.get(i)).getCMoney() != null) {
                            logCostinfo.append(((TCost)costTmpList.get(i)).getCMoney());
                        }
                        logCostinfo.append("；");
                        this.costDAO.deleteCost((Serializable)dbid);
                    }
                    found = false;
                }
            }
        }
        this.businDAO.deleteCostGroupInfo(busin.getCId().intValue());
        if (groupInfoList != null && groupInfoList.size() > 0) {
            for (int i = 0; i < groupInfoList.size(); ++i) {
                VCostGroupSum ss;
                TBiaoji bb = this.businDAO.getBiaoji(((TCostGroupInfo)groupInfoList.get(i)).getCBusinId().intValue(), ((TCostGroupInfo)groupInfoList.get(i)).getTCostGroup().getCId().intValue());
                if (bb == null || (ss = this.costDAO.getCostGroupSumByBusinId(((TCostGroupInfo)groupInfoList.get(i)).getCBusinId().intValue(), ((TCostGroupInfo)groupInfoList.get(i)).getTCostGroup().getCId().intValue())) == null) continue;
                BigDecimal data1 = new BigDecimal(ss.getId().getTotal());
                BigDecimal data21 = new BigDecimal(bb.getCMoney());
                BigDecimal data22 = new BigDecimal(bb.getCMoney2());
                if (data1.compareTo(data21) != 0 && data1.compareTo(data22) != 0) {
                    bb.setCMoney2(ss.getId().getTotal());
                    bb.setCNotice(Short.valueOf((short)1));
                }
                if (data1.compareTo(data21) == 0) {
                    bb.setCMoney2(ss.getId().getTotal());
                    bb.setCNotice(Short.valueOf((short)0));
                }
                this.businDAO.saveBiaoji(bb);
            }
        }
        if (cons != null && cons.size() > 0) {
            for (int i = 0; i < cons.size(); ++i) {
                if (cons.get(i) == null) continue;
                if (cons.get(i).getCId() == null) {
                    cons.get(i).setTBusin(busin);
                    logContainer.append("<br/>&nbsp;&nbsp;添加CONTAINER INFO，");
                    if (cons.get(i).getCContainerNum() != null && !"".equals(cons.get(i).getCContainerNum().trim())) {
                        logContainer.append("CONTAINER NO：").append(cons.get(i).getCContainerNum()).append("；");
                    }
                    if (cons.get(i).getCContainerType() != null && !"".equals(cons.get(i).getCContainerType().trim())) {
                        logContainer.append("TYPE：").append(cons.get(i).getCContainerType()).append("；");
                    }
                    if (cons.get(i).getCCount() != null && !"".equals(cons.get(i).getCCount().trim())) {
                        logContainer.append("Pkgs/Weight：").append(cons.get(i).getCCount()).append("；");
                    }
                    if (cons.get(i).getCRemarks() != null && !"".equals(cons.get(i).getCRemarks().trim())) {
                        logContainer.append("REM：").append(cons.get(i).getCRemarks()).append("；");
                    }
                    if (cons.get(i).getCRemarks2() != null && !"".equals(cons.get(i).getCRemarks2().trim())) {
                        logContainer.append("NT：").append(cons.get(i).getCRemarks2()).append("；");
                    }
                    if (cons.get(i).getCTrucker() != null && !"".equals(cons.get(i).getCTrucker().trim())) {
                        logContainer.append("TRUCKER：").append(cons.get(i).getCTrucker()).append("；");
                    }
                } else {
                    Object[] obj = this.containerDAO.getContainerInfo(cons.get(i).getCId().intValue());
                    StringBuffer tmp4 = new StringBuffer();
                    boolean editFlag = false;
                    if (!(cons.get(i).getCContainerNum() != null && obj[0] != null && cons.get(i).getCContainerNum().equals(obj[0].toString()) || (cons.get(i).getCContainerNum() == null || "".equals(cons.get(i).getCContainerNum().trim())) && (obj[0] == null || "".equals(obj[0])))) {
                        tmp4.append("CONTAINER NO：");
                        if (obj[0] == null || "".equals(obj[0].toString().trim())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(obj[0].toString().trim());
                        }
                        tmp4.append(" 改为 ");
                        if (cons.get(i).getCContainerNum() == null || "".equals(cons.get(i).getCContainerNum())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(cons.get(i).getCContainerNum());
                        }
                        tmp4.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i).getCContainerType() != null && obj[1] != null && cons.get(i).getCContainerType().equals(obj[1].toString()) || (cons.get(i).getCContainerType() == null || "".equals(cons.get(i).getCContainerType().trim())) && (obj[1] == null || "".equals(obj[1])))) {
                        tmp4.append("TYPE：");
                        if (obj[1] == null || "".equals(obj[1].toString().trim())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(obj[1].toString().trim());
                        }
                        tmp4.append(" 改为 ");
                        if (cons.get(i).getCContainerType() == null || "".equals(cons.get(i).getCContainerType())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(cons.get(i).getCContainerType());
                        }
                        tmp4.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i).getCCount() != null && obj[2] != null && cons.get(i).getCCount().equals(obj[2].toString()) || (cons.get(i).getCCount() == null || "".equals(cons.get(i).getCCount().trim())) && (obj[2] == null || "".equals(obj[2])))) {
                        tmp4.append("Pkgs/Weight：");
                        if (obj[2] == null || "".equals(obj[2].toString().trim())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(obj[2].toString().trim());
                        }
                        tmp4.append(" 改为 ");
                        if (cons.get(i).getCCount() == null || "".equals(cons.get(i).getCCount())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(cons.get(i).getCCount());
                        }
                        tmp4.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i).getCRemarks() != null && obj[3] != null && cons.get(i).getCRemarks().equals(obj[3].toString()) || (cons.get(i).getCRemarks() == null || "".equals(cons.get(i).getCRemarks().trim())) && (obj[3] == null || "".equals(obj[3])))) {
                        tmp4.append("REM：");
                        if (obj[3] == null || "".equals(obj[3].toString().trim())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(obj[3].toString().trim());
                        }
                        tmp4.append(" 改为 ");
                        if (cons.get(i).getCRemarks() == null || "".equals(cons.get(i).getCRemarks())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(cons.get(i).getCRemarks());
                        }
                        tmp4.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i).getCRemarks2() != null && obj[4] != null && cons.get(i).getCRemarks2().equals(obj[4].toString()) || (cons.get(i).getCRemarks2() == null || "".equals(cons.get(i).getCRemarks2().trim())) && (obj[4] == null || "".equals(obj[4])))) {
                        tmp4.append("NT：");
                        if (obj[4] == null || "".equals(obj[4].toString().trim())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(obj[4].toString().trim());
                        }
                        tmp4.append(" 改为 ");
                        if (cons.get(i).getCRemarks2() == null || "".equals(cons.get(i).getCRemarks2())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(cons.get(i).getCRemarks2());
                        }
                        tmp4.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i).getCTrucker() != null && obj[5] != null && cons.get(i).getCTrucker().equals(obj[5].toString()) || (cons.get(i).getCTrucker() == null || "".equals(cons.get(i).getCTrucker().trim())) && (obj[5] == null || "".equals(obj[5])))) {
                        tmp4.append("TRUCKER：");
                        if (obj[5] == null || "".equals(obj[5].toString().trim())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(obj[5].toString().trim());
                        }
                        tmp4.append(" 改为 ");
                        if (cons.get(i).getCTrucker() == null || "".equals(cons.get(i).getCTrucker())) {
                            tmp4.append("");
                        } else {
                            tmp4.append(cons.get(i).getCTrucker());
                        }
                        tmp4.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logContainer.append("<br/>&nbsp;&nbsp;修改CONTAINER INFO，原CONTAINER NO：");
                        if (cons.get(i).getCContainerNum() != null) {
                            logContainer.append(cons.get(i).getCContainerNum());
                        }
                        logContainer.append("。").append(tmp4);
                    }
                    editFlag = false;
                }
                this.containerDAO.saveContainer(cons.get(i));
            }
        }
        if (businId != null && (conTmpList = this.containerDAO.getList(businId.intValue())) != null && conTmpList.size() > 0) {
            for (int i = 0; i < conTmpList.size(); ++i) {
                dbid = ((TContainer)conTmpList.get(i)).getCId();
                if (cons != null && cons.size() > 0) {
                    for (int j = 0; j < cons.size(); ++j) {
                        if (cons.get(j) == null || cons.get(j).getCId() == null || dbid != (businTmpId = cons.get(j).getCId())) continue;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    logContainer.append("<br/>&nbsp;&nbsp;删除CONTAINER INFO，CONTAINER NO：");
                    if (((TContainer)conTmpList.get(i)).getCContainerNum() != null) {
                        logContainer.append(((TContainer)conTmpList.get(i)).getCContainerNum());
                    }
                    logContainer.append("。");
                    this.containerDAO.deleteContainer((Serializable)dbid);
                }
                found = false;
            }
        }
        if (services != null && services.size() > 0) {
            for (int i = 0; i < services.size(); ++i) {
                if (services.get(i) == null) continue;
                if (services.get(i).getCId() == null) {
                    services.get(i).setTBusin(busin);
                }
                this.businServiceDAO.saveService(services.get(i));
            }
        }
        if (businId != null && (serTmpList = this.businServiceDAO.getList(businId.intValue())) != null && serTmpList.size() > 0) {
            for (int i = 0; i < serTmpList.size(); ++i) {
                dbid = ((TService)serTmpList.get(i)).getCId();
                if (services != null && services.size() > 0) {
                    for (int j = 0; j < services.size(); ++j) {
                        if (services.get(j) == null || services.get(j).getCId() == null || dbid != (businTmpId = services.get(j).getCId())) continue;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    this.businServiceDAO.deleteService((Serializable)dbid);
                }
                found = false;
            }
        }
        if (costs != null && costs.size() > 0) {
            boolean costFlag = false;
            boolean userFlag = false;
            for (int i = 0; i < costs.size(); ++i) {
                if (costs.get(i) == null || !(costs.get(i).getTCostItem() != null && costs.get(i).getTCostItem().getCId() != null && costs.get(i).getTCostItem().getCId() != 0 || costs.get(i).getCMoney() != null || costs.get(i).getCRe() != null && !"".equals(costs.get(i).getCRe()) || !"".equals(costs.get(i).getCRemarks()) && costs.get(i).getCRemarks() != null || costs.get(i).getCRemarks2() != null && !"".equals(costs.get(i).getCRemarks2()) || costs.get(i).getCRemarks3() != null && !"".equals(costs.get(i).getCRemarks3()) || costs.get(i).getCRemarks4() != null && !"".equals(costs.get(i).getCRemarks4())) && (costs.get(i).getCRemarks5() == null || "".equals(costs.get(i).getCRemarks5()))) continue;
                if (costs.get(i).getTCostItem() == null || costs.get(i).getTCostItem().getCId() == null || costs.get(i).getTCostItem().getCId() == 0) {
                    costs.get(i).setTCostItem(null);
                }
                if (costs.get(i).getTCostGroup() == null || costs.get(i).getTCostGroup().getCId() == null || costs.get(i).getTCostGroup().getCId() == 0) {
                    costs.get(i).setTCostGroup(null);
                }
                if (costs.get(i).getCId() == null) {
                    if (busin.getCState() > 1) {
                        costs.get(i).setCNewCostFlag(Short.valueOf((short)1));
                    }
                    costs.get(i).setTBusin(busin);
                    costs.get(i).setCPrint(Short.valueOf((short)0));
                    if (CommonUtil.getUserId() == 2) {
                        costs.get(i).setCHidden(Short.valueOf((short)1));
                    }
                    costs.get(i).setTUserByCCreateUserid(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    logCostinfo.append("<br/>&nbsp;&nbsp;添加Cost Info，").append("Cost Item：");
                    if (costs.get(i).getTCostItem() != null && costs.get(i).getTCostItem().getCId() != null) {
                        logCostinfo.append(this.costItemDAO.getEntityById((Serializable)costs.get(i).getTCostItem().getCId()).getCName());
                    } else {
                        logCostinfo.append("PLEASE CHOOSE");
                    }
                    logCostinfo.append("。");
                    if (costs.get(i).getCMoney() != null) {
                        logCostinfo.append("$：").append(costs.get(i).getCMoney()).append("；");
                    }
                    if (costs.get(i).getCRiel() != null) {
                        logCostinfo.append("Riel：").append(costs.get(i).getCRiel()).append("；");
                    }
                    costs.get(i).setCCreateDate(CommonUtil.getDatetime());
                } else {
                    Object[] obj = this.costDAO.getCostInfo(costs.get(i).getCId().intValue());
                    StringBuffer tmp5 = new StringBuffer();
                    boolean editFlag = false;
                    if (!(costs.get(i).getTCostItem() != null && obj[0] != null && costs.get(i).getTCostItem().getCId().equals(Integer.parseInt(obj[0].toString())) || costs.get(i).getTCostItem() == null && obj[0] == null)) {
                        tmp5.append("Cost Item：");
                        if (obj[0] == null) {
                            tmp5.append("PLEASE CHOOSE");
                        } else {
                            tmp5.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        tmp5.append(" 改为 ");
                        if (costs.get(i).getTCostItem() == null) {
                            tmp5.append("PLEASE CHOOSE");
                        } else {
                            tmp5.append(this.costItemDAO.getEntityById((Serializable)costs.get(i).getTCostItem().getCId()).getCName());
                        }
                        if (busin.getCState() > 1) {
                            costs.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp5.append("；");
                        editFlag = true;
                    }
                    if (!(costs.get(i).getCMoney() == null && obj[1] == null || costs.get(i).getCMoney() != null && obj[1] != null && costs.get(i).getCMoney() == Double.parseDouble(obj[1].toString()))) {
                        tmp5.append("$：");
                        if (obj[1] == null) {
                            tmp5.append("0");
                        } else {
                            tmp5.append(new BigDecimal(Double.parseDouble(obj[1].toString())).setScale(2, 4));
                        }
                        tmp5.append(" 改为 ");
                        if (costs.get(i).getCMoney() == null) {
                            tmp5.append("0");
                        } else {
                            tmp5.append(new BigDecimal(costs.get(i).getCMoney()).setScale(2, 4));
                        }
                        if (busin.getCState() > 1) {
                            costs.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp5.append("；");
                        editFlag = true;
                    }
                    if (!(costs.get(i).getCRiel() == null && obj[8] == null || costs.get(i).getCRiel() != null && obj[8] != null && costs.get(i).getCRiel() == Double.parseDouble(obj[8].toString()))) {
                        tmp5.append("Riel：");
                        if (obj[8] == null) {
                            tmp5.append("0");
                        } else {
                            tmp5.append(new BigDecimal(Double.parseDouble(obj[8].toString())).setScale(2, 4));
                        }
                        tmp5.append(" 改为 ");
                        if (costs.get(i).getCRiel() == null) {
                            tmp5.append("0");
                        } else {
                            tmp5.append(new BigDecimal(costs.get(i).getCRiel()).setScale(2, 4));
                        }
                        if (busin.getCState() > 1) {
                            costs.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp5.append("；");
                        editFlag = true;
                    }
                    if (!(costs.get(i).getCRe() == null && obj[2] == null || costs.get(i).getCRe() != null && obj[2] != null && costs.get(i).getCRe().equals(obj[2].toString()))) {
                        tmp5.append("Re：");
                        if (obj[8] == null) {
                            tmp5.append("''");
                        } else {
                            tmp5.append(obj[8].toString());
                        }
                        tmp5.append(" 改为 ");
                        if (costs.get(i).getCRe() == null) {
                            tmp5.append("''");
                        } else {
                            tmp5.append(costs.get(i).getCRe());
                        }
                        if (busin.getCState() > 1) {
                            costs.get(i).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp5.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;修改Cost Info，原Cost Item：");
                        if (obj[0] != null && Integer.parseInt(obj[0].toString()) > 0) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        logCostinfo.append("。");
                        logCostinfo.append(tmp5);
                    }
                }
                if (costs.get(i).getCState() == null || costs.get(i).getCState() != 3 && costs.get(i).getCState() != 5) {
                    if (busin.getCState() == 1) {
                        costs.get(i).setCState(Short.valueOf((short)1));
                    } else if (busin.getCState() == 2) {
                        if (!costFlag || !userFlag || costs.get(i).getCMoney() == null) {
                            costs.get(i).setCState(Short.valueOf((short)1));
                        } else {
                            costs.get(i).setCState(Short.valueOf((short)2));
                        }
                    }
                }
                this.costDAO.saveCost(costs.get(i));
                costFlag = false;
                userFlag = false;
            }
        }
        if (businId != null) {
            List costTmpList = this.costDAO.getList(businId.intValue(), (short)0);
            if (costs != null && costs.size() > 0) {
                for (int i = 0; i < costTmpList.size(); ++i) {
                    dbid = ((TCost)costTmpList.get(i)).getCId();
                    if (costs != null && costs.size() > 0) {
                        for (int j = 0; j < costs.size(); ++j) {
                            if (costs.get(j) == null || costs.get(j).getCId() == null || dbid != (businTmpId = costs.get(j).getCId())) continue;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;删除Cost Info，Cost Item：");
                        if (((TCost)costTmpList.get(i)).getTCostItem() != null && ((TCost)costTmpList.get(i)).getTCostItem().getCId() != null) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)((TCost)costTmpList.get(i)).getTCostItem().getCId()).getCName()).append("；");
                        }
                        logCostinfo.append("$：");
                        if (((TCost)costTmpList.get(i)).getCMoney() != null) {
                            logCostinfo.append(((TCost)costTmpList.get(i)).getCMoney());
                        }
                        logCostinfo.append("；");
                        this.costDAO.deleteCost((Serializable)dbid);
                    }
                    found = false;
                }
            }
        }
        if (!create) {
            if (!"".equals(logBaseinfo.trim())) {
                result.append("<br/>BASE INFO：").append(logBaseinfo.trim());
            }
            if (!"".equals(logContainer.toString().trim())) {
                result.append("<br/>CONTAINER INFO：").append(logContainer.toString().trim());
            }
            if (!"".equals(logCostinfo.toString().trim())) {
                result.append("<br/>COST INFO：").append(logCostinfo.toString().trim());
            }
            if (!"".equals(logRemarks.trim())) {
                result.append("<br/>Remarks INFO：").append(logRemarks.trim());
            }
        }
        if (!"".equals(result.toString().trim())) {
            if (!create) {
                businLog.setCNote("修改单据。打开服务单时间：" + logs[2] + "，保存服务单时间：" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(CommonUtil.getDatetime()) + "。" + result.toString());
            } else {
                businLog.setCNote(result.toString());
            }
            this.businLogDAO.saveEntity(businLog);
        }
    }

    public void saveEntity(TBusin busin) throws Exception {
        this.businDAO.saveEntity(busin);
    }

    public void saveCash(int businId, List<TContainer> cons, List<TCost> costs, List<TCost> costsGroup, List<TCash> cash, List<TCashRate> rate, List<TCash> cash2, List<TCashRate> rate2, short state, short flag, TBusin bb, String[] logs) throws Exception {
        TJiezhuan jj;
        int i;
        List cashTmpList;
        StringBuffer logCostinfo = new StringBuffer();
        StringBuffer logDn1 = new StringBuffer();
        StringBuffer logDn = new StringBuffer();
        StringBuffer logInv = new StringBuffer();
        StringBuffer result = new StringBuffer();
        TBusinLog businLog = new TBusinLog();
        businLog.setCDate(CommonUtil.getDatetime());
        businLog.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
        businLog.setTBusin(bb);
        int count = 0;
        int orderCount = 0;
        int cashCount = 0;
        String cdate = this.formatter2.format(CommonUtil.getDatetime());
        cdate = String.valueOf(cdate.substring(0, 2)) + "/" + cdate.substring(5, 7);
        String cdate2 = cdate.substring(3);
        StringBuffer logContainer = new StringBuffer();
        if (cons != null && cons.size() > 0) {
            for (int i2 = 0; i2 < cons.size(); ++i2) {
                if (cons.get(i2) == null) continue;
                if (cons.get(i2).getCId() == null) {
                    cons.get(i2).setTBusin(bb);
                    logContainer.append("<br/>&nbsp;&nbsp;添加CONTAINER INFO，");
                    if (cons.get(i2).getCContainerNum() != null && !"".equals(cons.get(i2).getCContainerNum().trim())) {
                        logContainer.append("CONTAINER NO：").append(cons.get(i2).getCContainerNum()).append("；");
                    }
                    if (cons.get(i2).getCContainerType() != null && !"".equals(cons.get(i2).getCContainerType().trim())) {
                        logContainer.append("TYPE：").append(cons.get(i2).getCContainerType()).append("；");
                    }
                    if (cons.get(i2).getCCount() != null && !"".equals(cons.get(i2).getCCount().trim())) {
                        logContainer.append("Pkgs/Weight：").append(cons.get(i2).getCCount()).append("；");
                    }
                    if (cons.get(i2).getCRemarks() != null && !"".equals(cons.get(i2).getCRemarks().trim())) {
                        logContainer.append("REM：").append(cons.get(i2).getCRemarks()).append("；");
                    }
                    if (cons.get(i2).getCRemarks2() != null && !"".equals(cons.get(i2).getCRemarks2().trim())) {
                        logContainer.append("NT：").append(cons.get(i2).getCRemarks2()).append("；");
                    }
                    if (cons.get(i2).getCTrucker() != null && !"".equals(cons.get(i2).getCTrucker().trim())) {
                        logContainer.append("TRUCKER：").append(cons.get(i2).getCTrucker()).append("；");
                    }
                } else {
                    Object[] obj = this.containerDAO.getContainerInfo(cons.get(i2).getCId().intValue());
                    StringBuffer tmp = new StringBuffer();
                    boolean editFlag = false;
                    if (!(cons.get(i2).getCContainerNum() != null && obj[0] != null && cons.get(i2).getCContainerNum().equals(obj[0].toString()) || (cons.get(i2).getCContainerNum() == null || "".equals(cons.get(i2).getCContainerNum().trim())) && (obj[0] == null || "".equals(obj[0])))) {
                        tmp.append("CONTAINER NO：");
                        if (obj[0] == null || "".equals(obj[0].toString().trim())) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[0].toString().trim());
                        }
                        tmp.append(" 改为 ");
                        if (cons.get(i2).getCContainerNum() == null || "".equals(cons.get(i2).getCContainerNum())) {
                            tmp.append("");
                        } else {
                            tmp.append(cons.get(i2).getCContainerNum());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i2).getCContainerType() != null && obj[1] != null && cons.get(i2).getCContainerType().equals(obj[1].toString()) || (cons.get(i2).getCContainerType() == null || "".equals(cons.get(i2).getCContainerType().trim())) && (obj[1] == null || "".equals(obj[1])))) {
                        tmp.append("TYPE：");
                        if (obj[1] == null || "".equals(obj[1].toString().trim())) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[1].toString().trim());
                        }
                        tmp.append(" 改为 ");
                        if (cons.get(i2).getCContainerType() == null || "".equals(cons.get(i2).getCContainerType())) {
                            tmp.append("");
                        } else {
                            tmp.append(cons.get(i2).getCContainerType());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i2).getCCount() != null && obj[2] != null && cons.get(i2).getCCount().equals(obj[2].toString()) || (cons.get(i2).getCCount() == null || "".equals(cons.get(i2).getCCount().trim())) && (obj[2] == null || "".equals(obj[2])))) {
                        tmp.append("Pkgs/Weight：");
                        if (obj[2] == null || "".equals(obj[2].toString().trim())) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[2].toString().trim());
                        }
                        tmp.append(" 改为 ");
                        if (cons.get(i2).getCCount() == null || "".equals(cons.get(i2).getCCount())) {
                            tmp.append("");
                        } else {
                            tmp.append(cons.get(i2).getCCount());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i2).getCRemarks() != null && obj[3] != null && cons.get(i2).getCRemarks().equals(obj[3].toString()) || (cons.get(i2).getCRemarks() == null || "".equals(cons.get(i2).getCRemarks().trim())) && (obj[3] == null || "".equals(obj[3])))) {
                        tmp.append("REM：");
                        if (obj[3] == null || "".equals(obj[3].toString().trim())) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[3].toString().trim());
                        }
                        tmp.append(" 改为 ");
                        if (cons.get(i2).getCRemarks() == null || "".equals(cons.get(i2).getCRemarks())) {
                            tmp.append("");
                        } else {
                            tmp.append(cons.get(i2).getCRemarks());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i2).getCRemarks2() != null && obj[4] != null && cons.get(i2).getCRemarks2().equals(obj[4].toString()) || (cons.get(i2).getCRemarks2() == null || "".equals(cons.get(i2).getCRemarks2().trim())) && (obj[4] == null || "".equals(obj[4])))) {
                        tmp.append("NT：");
                        if (obj[4] == null || "".equals(obj[4].toString().trim())) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[4].toString().trim());
                        }
                        tmp.append(" 改为 ");
                        if (cons.get(i2).getCRemarks2() == null || "".equals(cons.get(i2).getCRemarks2())) {
                            tmp.append("");
                        } else {
                            tmp.append(cons.get(i2).getCRemarks2());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cons.get(i2).getCTrucker() != null && obj[5] != null && cons.get(i2).getCTrucker().equals(obj[5].toString()) || (cons.get(i2).getCTrucker() == null || "".equals(cons.get(i2).getCTrucker().trim())) && (obj[5] == null || "".equals(obj[5])))) {
                        tmp.append("TRUCKER：");
                        if (obj[5] == null || "".equals(obj[5].toString().trim())) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[5].toString().trim());
                        }
                        tmp.append(" 改为 ");
                        if (cons.get(i2).getCTrucker() == null || "".equals(cons.get(i2).getCTrucker())) {
                            tmp.append("");
                        } else {
                            tmp.append(cons.get(i2).getCTrucker());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logContainer.append("<br/>&nbsp;&nbsp;修改CONTAINER INFO，原CONTAINER NO：");
                        if (cons.get(i2).getCContainerNum() != null) {
                            logContainer.append(cons.get(i2).getCContainerNum());
                        }
                        logContainer.append("。").append(tmp);
                    }
                    editFlag = false;
                }
                this.containerDAO.saveContainer(cons.get(i2));
            }
        }
        if (businId > 0) {
            boolean found = false;
            List conTmpList = this.containerDAO.getList(businId);
            if (conTmpList != null && conTmpList.size() > 0) {
                for (int i3 = 0; i3 < conTmpList.size(); ++i3) {
                    found = false;
                    int dbid = ((TContainer)conTmpList.get(i3)).getCId();
                    if (cons != null && cons.size() > 0) {
                        for (int j = 0; j < cons.size(); ++j) {
                            int businTmpId;
                            if (cons.get(j) == null || cons.get(j).getCId() == null || dbid != (businTmpId = cons.get(j).getCId().intValue())) continue;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        logContainer.append("<br/>&nbsp;&nbsp;删除CONTAINER INFO，CONTAINER NO：");
                        if (((TContainer)conTmpList.get(i3)).getCContainerNum() != null) {
                            logContainer.append(((TContainer)conTmpList.get(i3)).getCContainerNum());
                        }
                        logContainer.append("。");
                        this.containerDAO.deleteContainer((Serializable)Integer.valueOf(dbid));
                    }
                    found = false;
                }
            }
        }
        boolean isAddNewCost = false;
        double costSumMoney1 = 0.0;
        double costSumMoney2 = 0.0;
        if (costs != null && costs.size() > 0) {
            boolean costFlag = false;
            boolean userFlag = false;
            for (int i4 = 0; i4 < costs.size(); ++i4) {
                if (costs.get(i4) == null || !(costs.get(i4).getTCostItem() != null && costs.get(i4).getTCostItem().getCId() != null && costs.get(i4).getTCostItem().getCId() != 0 || costs.get(i4).getCMoney() != null || costs.get(i4).getCRe() != null && !"".equals(costs.get(i4).getCRe()) || !"".equals(costs.get(i4).getCRemarks()) && costs.get(i4).getCRemarks() != null || costs.get(i4).getCRemarks2() != null && !"".equals(costs.get(i4).getCRemarks2()) || costs.get(i4).getCRemarks3() != null && !"".equals(costs.get(i4).getCRemarks3()) || costs.get(i4).getCRemarks4() != null && !"".equals(costs.get(i4).getCRemarks4())) && (costs.get(i4).getCRemarks5() == null || "".equals(costs.get(i4).getCRemarks5()))) continue;
                if (costs.get(i4).getTCostItem() == null || costs.get(i4).getTCostItem().getCId() == null || costs.get(i4).getTCostItem().getCId() == 0) {
                    costs.get(i4).setTCostItem(null);
                }
                if (costs.get(i4).getTCostGroup() == null || costs.get(i4).getTCostGroup().getCId() == null || costs.get(i4).getTCostGroup().getCId() == 0) {
                    costs.get(i4).setTCostGroup(null);
                }
                if (costs.get(i4).getCId() == null) {
                    if (bb.getCState() > 1) {
                        costs.get(i4).setCNewCostFlag(Short.valueOf((short)1));
                    }
                    costs.get(i4).setTBusin(bb);
                    costs.get(i4).setCPrint(Short.valueOf((short)0));
                    if (CommonUtil.getUserId() == 2) {
                        costs.get(i4).setCHidden(Short.valueOf((short)1));
                    }
                    if (costs.get(i4).getTCostGroup() != null && costs.get(i4).getTCostGroup().getCId() != null && costs.get(i4).getTCostGroup().getCId() > 0) {
                        bb.setCAddGroup(Short.valueOf((short)1));
                    }
                    costs.get(i4).setTUserByCCreateUserid(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    logCostinfo.append("<br/>&nbsp;&nbsp;添加Cost Info，").append("Cost Item：");
                    if (costs.get(i4).getTCostItem() != null && costs.get(i4).getTCostItem().getCId() != null) {
                        logCostinfo.append(this.costItemDAO.getEntityById((Serializable)costs.get(i4).getTCostItem().getCId()).getCName());
                    } else {
                        logCostinfo.append("PLEASE CHOOSE");
                    }
                    logCostinfo.append("。");
                    if (costs.get(i4).getCMoney() != null) {
                        logCostinfo.append("$：").append(costs.get(i4).getCMoney()).append("；");
                    }
                    if (costs.get(i4).getCRiel() != null) {
                        logCostinfo.append("Riel：").append(costs.get(i4).getCRiel()).append("；");
                    }
                    costs.get(i4).setCCreateDate(CommonUtil.getDatetime());
                } else {
                    Object[] obj = this.costDAO.getCostInfo(costs.get(i4).getCId().intValue());
                    StringBuffer tmp = new StringBuffer();
                    boolean editFlag = false;
                    if (!(costs.get(i4).getTCostItem() != null && obj[0] != null && costs.get(i4).getTCostItem().getCId().equals(Integer.parseInt(obj[0].toString())) || costs.get(i4).getTCostItem() == null && obj[0] == null)) {
                        tmp.append("Cost Item：");
                        if (obj[0] == null) {
                            tmp.append("PLEASE CHOOSE");
                        } else {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        tmp.append(" 改为 ");
                        if (costs.get(i4).getTCostItem() == null) {
                            tmp.append("PLEASE CHOOSE");
                        } else {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)costs.get(i4).getTCostItem().getCId()).getCName());
                        }
                        if (bb.getCState() > 1) {
                            costs.get(i4).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i4).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(costs.get(i4).getCMoney() == null && obj[1] == null || costs.get(i4).getCMoney() != null && obj[1] != null && costs.get(i4).getCMoney() == Double.parseDouble(obj[1].toString()))) {
                        tmp.append("$：");
                        if (obj[1] == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(Double.parseDouble(obj[1].toString())).setScale(2, 4));
                        }
                        tmp.append(" 改为 ");
                        if (costs.get(i4).getCMoney() == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(costs.get(i4).getCMoney()).setScale(2, 4));
                        }
                        if (bb.getCState() > 1) {
                            costs.get(i4).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i4).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(costs.get(i4).getCRiel() == null && obj[8] == null || costs.get(i4).getCRiel() != null && obj[8] != null && costs.get(i4).getCRiel() == Double.parseDouble(obj[8].toString()))) {
                        tmp.append("Riel：");
                        if (obj[8] == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(Double.parseDouble(obj[8].toString())).setScale(2, 4));
                        }
                        tmp.append(" 改为 ");
                        if (costs.get(i4).getCRiel() == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(costs.get(i4).getCRiel()).setScale(2, 4));
                        }
                        if (bb.getCState() > 1) {
                            costs.get(i4).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i4).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(costs.get(i4).getCRe() == null && obj[2] == null || costs.get(i4).getCRe() != null && obj[2] != null && costs.get(i4).getCRe().equals(obj[2].toString()))) {
                        tmp.append("Re：");
                        if (obj[8] == null) {
                            tmp.append("''");
                        } else {
                            tmp.append(obj[8].toString());
                        }
                        tmp.append(" 改为 ");
                        if (costs.get(i4).getCRe() == null) {
                            tmp.append("''");
                        } else {
                            tmp.append(costs.get(i4).getCRe());
                        }
                        if (bb.getCState() > 1) {
                            costs.get(i4).setCNewCostFlag(Short.valueOf((short)1));
                            costs.get(i4).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;修改Cost Info，原Cost Item：");
                        if (obj[0] != null && Integer.parseInt(obj[0].toString()) > 0) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        logCostinfo.append("。");
                        logCostinfo.append(tmp);
                    }
                }
                if (costs.get(i4).getCState() == null || costs.get(i4).getCState() != 3 && costs.get(i4).getCState() != 5) {
                    if (bb.getCState() == 1) {
                        costs.get(i4).setCState(Short.valueOf((short)1));
                    } else if (bb.getCState() == 2) {
                        if (!costFlag || !userFlag || costs.get(i4).getCMoney() == null) {
                            costs.get(i4).setCState(Short.valueOf((short)1));
                        } else {
                            costs.get(i4).setCState(Short.valueOf((short)2));
                        }
                    }
                }
                this.costDAO.saveCost(costs.get(i4));
                costFlag = false;
                userFlag = false;
                if (costs.get(i4).getTCostItem() != null && costs.get(i4).getTCostItem().getCId() != null && costs.get(i4).getTCostItem().getCId() != 0 && (costs.get(i4).getTCostItem().getCId() == 198 || costs.get(i4).getTCostItem().getCId() == 199) || costs.get(i4).getCMoney() == null) continue;
                costSumMoney1 += costs.get(i4).getCMoney().doubleValue();
            }
        }
        int dbid = 0;
        int businTmpId = 0;
        boolean found = false;
        if (businId > 0) {
            List costTmpList = this.costDAO.getList(businId, (short)0);
            if (costs != null && costs.size() > 0) {
                for (int i5 = 0; i5 < costTmpList.size(); ++i5) {
                    found = false;
                    dbid = ((TCost)costTmpList.get(i5)).getCId();
                    if (costs != null && costs.size() > 0) {
                        for (int j = 0; j < costs.size(); ++j) {
                            if (costs.get(j) == null || costs.get(j).getCId() == null || dbid != (businTmpId = costs.get(j).getCId().intValue())) continue;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;删除Cost Info，Cost Item：");
                        if (((TCost)costTmpList.get(i5)).getTCostItem() != null && ((TCost)costTmpList.get(i5)).getTCostItem().getCId() != null) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)((TCost)costTmpList.get(i5)).getTCostItem().getCId()).getCName()).append("；");
                        }
                        logCostinfo.append("$：");
                        if (((TCost)costTmpList.get(i5)).getCMoney() != null) {
                            logCostinfo.append(((TCost)costTmpList.get(i5)).getCMoney());
                        }
                        logCostinfo.append("；");
                        this.costDAO.deleteCost((Serializable)Integer.valueOf(dbid));
                    }
                    found = false;
                }
            }
        }
        List groupInfoList = this.businDAO.getCostGroupInfoList(bb.getCId().intValue());
        if (costsGroup != null && costsGroup.size() > 0) {
            boolean costFlag = false;
            boolean userFlag = false;
            for (int i6 = 0; i6 < costsGroup.size(); ++i6) {
                if (costsGroup.get(i6) == null || !(costsGroup.get(i6).getTCostItem() != null && costsGroup.get(i6).getTCostItem().getCId() != null && costsGroup.get(i6).getTCostItem().getCId() != 0 || costsGroup.get(i6).getCMoney() != null || costsGroup.get(i6).getCRe() != null && !"".equals(costsGroup.get(i6).getCRe()) || !"".equals(costsGroup.get(i6).getCRemarks()) && costsGroup.get(i6).getCRemarks() != null || costsGroup.get(i6).getCRemarks2() != null && !"".equals(costsGroup.get(i6).getCRemarks2()) || costsGroup.get(i6).getCRemarks3() != null && !"".equals(costsGroup.get(i6).getCRemarks3()) || costsGroup.get(i6).getCRemarks4() != null && !"".equals(costsGroup.get(i6).getCRemarks4())) && (costsGroup.get(i6).getCRemarks5() == null || "".equals(costsGroup.get(i6).getCRemarks5()))) continue;
                if (costsGroup.get(i6).getTCostItem() == null || costsGroup.get(i6).getTCostItem().getCId() == null || costsGroup.get(i6).getTCostItem().getCId() == 0) {
                    costsGroup.get(i6).setTCostItem(null);
                }
                if (costsGroup.get(i6).getTCostGroup() == null || costsGroup.get(i6).getTCostGroup().getCId() == null || costsGroup.get(i6).getTCostGroup().getCId() == 0) {
                    costsGroup.get(i6).setTCostGroup(null);
                }
                if (costsGroup.get(i6).getCId() == null) {
                    if (bb.getCState() > 1) {
                        costsGroup.get(i6).setCNewCostFlag(Short.valueOf((short)1));
                    }
                    costsGroup.get(i6).setTBusin(bb);
                    costsGroup.get(i6).setCPrint(Short.valueOf((short)0));
                    if (CommonUtil.getUserId() == 2) {
                        costsGroup.get(i6).setCHidden(Short.valueOf((short)1));
                    }
                    if (costsGroup.get(i6).getTCostGroup() != null && costsGroup.get(i6).getTCostGroup().getCId() != null && costsGroup.get(i6).getTCostGroup().getCId() > 0) {
                        bb.setCAddGroup(Short.valueOf((short)1));
                    }
                    costsGroup.get(i6).setTUserByCCreateUserid(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    logCostinfo.append("<br/>&nbsp;&nbsp;添加Cost Info，").append("Cost Item：");
                    if (costsGroup.get(i6).getTCostItem() != null && costsGroup.get(i6).getTCostItem().getCId() != null) {
                        logCostinfo.append(this.costItemDAO.getEntityById((Serializable)costsGroup.get(i6).getTCostItem().getCId()).getCName());
                    } else {
                        logCostinfo.append("PLEASE CHOOSE");
                    }
                    logCostinfo.append("。");
                    if (costsGroup.get(i6).getCMoney() != null) {
                        logCostinfo.append("$：").append(costsGroup.get(i6).getCMoney()).append("；");
                    }
                    if (costsGroup.get(i6).getCRiel() != null) {
                        logCostinfo.append("Riel：").append(costsGroup.get(i6).getCRiel()).append("；");
                    }
                    costsGroup.get(i6).setCCreateDate(CommonUtil.getDatetime());
                } else {
                    Object[] obj = this.costDAO.getCostInfo(costsGroup.get(i6).getCId().intValue());
                    StringBuffer tmp = new StringBuffer();
                    boolean editFlag = false;
                    if (!(costsGroup.get(i6).getTCostItem() != null && obj[0] != null && costsGroup.get(i6).getTCostItem().getCId().equals(Integer.parseInt(obj[0].toString())) || costsGroup.get(i6).getTCostItem() == null && obj[0] == null)) {
                        tmp.append("Cost Item：");
                        if (obj[0] == null) {
                            tmp.append("PLEASE CHOOSE");
                        } else {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        tmp.append(" 改为 ");
                        if (costsGroup.get(i6).getTCostItem() == null) {
                            tmp.append("PLEASE CHOOSE");
                        } else {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)costsGroup.get(i6).getTCostItem().getCId()).getCName());
                        }
                        if (bb.getCState() > 1) {
                            costsGroup.get(i6).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i6).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(costsGroup.get(i6).getCMoney() == null && obj[1] == null || costsGroup.get(i6).getCMoney() != null && obj[1] != null && costsGroup.get(i6).getCMoney() == Double.parseDouble(obj[1].toString()))) {
                        tmp.append("$：");
                        if (obj[1] == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(Double.parseDouble(obj[1].toString())).setScale(2, 4));
                        }
                        tmp.append(" 改为 ");
                        if (costsGroup.get(i6).getCMoney() == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(costsGroup.get(i6).getCMoney()).setScale(2, 4));
                        }
                        if (bb.getCState() > 1) {
                            costsGroup.get(i6).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i6).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(costsGroup.get(i6).getCRiel() == null && obj[8] == null || costsGroup.get(i6).getCRiel() != null && obj[8] != null && costsGroup.get(i6).getCRiel() == Double.parseDouble(obj[8].toString()))) {
                        tmp.append("Riel：");
                        if (obj[8] == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(Double.parseDouble(obj[8].toString())).setScale(2, 4));
                        }
                        tmp.append(" 改为 ");
                        if (costsGroup.get(i6).getCRiel() == null) {
                            tmp.append("0");
                        } else {
                            tmp.append(new BigDecimal(costsGroup.get(i6).getCRiel()).setScale(2, 4));
                        }
                        if (bb.getCState() > 1) {
                            costsGroup.get(i6).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i6).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(costsGroup.get(i6).getCRe() == null && obj[2] == null || costsGroup.get(i6).getCRe() != null && obj[2] != null && costsGroup.get(i6).getCRe().equals(obj[2].toString()))) {
                        tmp.append("Re：");
                        if (obj[8] == null) {
                            tmp.append("''");
                        } else {
                            tmp.append(obj[8].toString());
                        }
                        tmp.append(" 改为 ");
                        if (costsGroup.get(i6).getCRe() == null) {
                            tmp.append("''");
                        } else {
                            tmp.append(costsGroup.get(i6).getCRe());
                        }
                        if (bb.getCState() > 1) {
                            costsGroup.get(i6).setCNewCostFlag(Short.valueOf((short)1));
                            costsGroup.get(i6).setCEditCostFlag(Short.valueOf((short)1));
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;修改Cost Info，原Cost Item：");
                        if (obj[0] != null && Integer.parseInt(obj[0].toString()) > 0) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        logCostinfo.append("。");
                        logCostinfo.append(tmp);
                    }
                }
                if (costsGroup.get(i6).getCState() == null || costsGroup.get(i6).getCState() != 3 && costsGroup.get(i6).getCState() != 5) {
                    if (bb.getCState() == 1) {
                        costsGroup.get(i6).setCState(Short.valueOf((short)1));
                    } else if (bb.getCState() == 2) {
                        if (!costFlag || !userFlag || costsGroup.get(i6).getCMoney() == null) {
                            costsGroup.get(i6).setCState(Short.valueOf((short)1));
                        } else {
                            costsGroup.get(i6).setCState(Short.valueOf((short)2));
                        }
                    }
                }
                this.costDAO.saveCost(costsGroup.get(i6));
                costFlag = false;
                userFlag = false;
                boolean groupInfofound = false;
                if (groupInfoList != null && groupInfoList.size() > 0) {
                    for (int kk = 0; kk < groupInfoList.size(); ++kk) {
                        if (costsGroup.get(i6).getTCostGroup().getCId() != ((TCostGroupInfo)groupInfoList.get(kk)).getTCostGroup().getCId()) continue;
                        groupInfofound = true;
                        if (((TCostGroupInfo)groupInfoList.get(kk)).getCExchange() == null || ((TCostGroupInfo)groupInfoList.get(kk)).getCExchange() == 0.0) break;
                        double criel = 0.0;
                        if (costsGroup.get(i6).getCRiel() != null) {
                            criel = costsGroup.get(i6).getCRiel();
                        }
                        double tmp = criel / ((TCostGroupInfo)groupInfoList.get(kk)).getCExchange();
                        costSumMoney2 += tmp;
                        if (costsGroup.get(i6).getCMoney() == null) break;
                        costSumMoney2 += costsGroup.get(i6).getCMoney().doubleValue();
                        break;
                    }
                }
                if (groupInfofound) continue;
                TCostGroupInfo groupInfo = new TCostGroupInfo();
                groupInfo.setCBusinId(bb.getCId());
                groupInfo.setTCostGroup(costsGroup.get(i6).getTCostGroup());
                this.businDAO.saveCostGroupInfo(groupInfo);
                groupInfoList.add(groupInfo);
                groupInfo = null;
            }
        }
        dbid = 0;
        businTmpId = 0;
        found = false;
        if (businId > 0) {
            List costTmpList = this.costDAO.getList(businId, (short)1);
            if (costsGroup != null && costsGroup.size() > 0) {
                for (int i7 = 0; i7 < costTmpList.size(); ++i7) {
                    found = false;
                    dbid = ((TCost)costTmpList.get(i7)).getCId();
                    if (costsGroup != null && costsGroup.size() > 0) {
                        for (int j = 0; j < costsGroup.size(); ++j) {
                            if (costsGroup.get(j) == null || costsGroup.get(j).getCId() == null || dbid != (businTmpId = costsGroup.get(j).getCId().intValue())) continue;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        logCostinfo.append("<br/>&nbsp;&nbsp;删除Cost Info，Cost Item：");
                        if (((TCost)costTmpList.get(i7)).getTCostItem() != null && ((TCost)costTmpList.get(i7)).getTCostItem().getCId() != null) {
                            logCostinfo.append(this.costItemDAO.getEntityById((Serializable)((TCost)costTmpList.get(i7)).getTCostItem().getCId()).getCName()).append("；");
                        }
                        logCostinfo.append("$：");
                        if (((TCost)costTmpList.get(i7)).getCMoney() != null) {
                            logCostinfo.append(((TCost)costTmpList.get(i7)).getCMoney());
                        }
                        logCostinfo.append("；");
                        this.costDAO.deleteCost((Serializable)Integer.valueOf(dbid));
                    }
                    found = false;
                }
            }
        }
        this.businDAO.deleteCostGroupInfo(bb.getCId().intValue());
        if (groupInfoList != null && groupInfoList.size() > 0) {
            for (int i8 = 0; i8 < groupInfoList.size(); ++i8) {
                VCostGroupSum ss;
                TBiaoji bj = this.businDAO.getBiaoji(((TCostGroupInfo)groupInfoList.get(i8)).getCBusinId().intValue(), ((TCostGroupInfo)groupInfoList.get(i8)).getTCostGroup().getCId().intValue());
                if (bj == null || (ss = this.costDAO.getCostGroupSumByBusinId(((TCostGroupInfo)groupInfoList.get(i8)).getCBusinId().intValue(), ((TCostGroupInfo)groupInfoList.get(i8)).getTCostGroup().getCId().intValue())) == null) continue;
                BigDecimal data1 = new BigDecimal(ss.getId().getTotal());
                BigDecimal data21 = new BigDecimal(bj.getCMoney());
                BigDecimal data22 = new BigDecimal(bj.getCMoney2());
                if (data1.compareTo(data21) != 0 && data1.compareTo(data22) != 0) {
                    bj.setCMoney2(ss.getId().getTotal());
                    bj.setCNotice(Short.valueOf((short)1));
                }
                if (data1.compareTo(data21) == 0) {
                    bj.setCMoney2(ss.getId().getTotal());
                    bj.setCNotice(Short.valueOf((short)0));
                }
                this.businDAO.saveBiaoji(bj);
            }
        }
        double cmoney = 0.0;
        double cashMoney1 = 0.0;
        double cashMoney2 = 0.0;
        double cashMoney4 = 0.0;
        count = 0;
        if (cash != null && cash.size() > 0) {
            for (int i9 = 0; i9 < cash.size(); ++i9) {
                if (flag == 1 && (cash.get(i9) == null || (cash.get(i9).getTCostItem() == null || cash.get(i9).getTCostItem().getCId() == null || cash.get(i9).getTCostItem().getCId() == 0) && cash.get(i9).getCMoney() == null) || cash.get(i9) == null) continue;
                ++count;
                ++cashCount;
                if (cash.get(i9).getTCostItem() == null || cash.get(i9).getTCostItem().getCId() == null || cash.get(i9).getTCostItem().getCId() == 0) {
                    cash.get(i9).setTCostItem(null);
                }
                if (cash.get(i9).getCId() == null) {
                    cash.get(i9).setTBusin(this.businDAO.getEntityById((Serializable)Integer.valueOf(businId)));
                    cash.get(i9).setTUserByCCreateUserid(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    cash.get(i9).setCCreatedate(CommonUtil.getDatetime());
                    bb.setCAddGroup(Short.valueOf((short)0));
                    isAddNewCost = true;
                    logDn1.append("<br/>&nbsp;&nbsp;添加Debit Note，Cash Item：");
                    if (cash.get(i9).getTCostItem() != null && cash.get(i9).getTCostItem().getCId() != null && cash.get(i9).getTCostItem().getCId() > 0) {
                        logDn1.append(this.costItemDAO.getEntityById((Serializable)cash.get(i9).getTCostItem().getCId()).getCName());
                    } else {
                        logDn1.append("PLEASE CHOOSE");
                    }
                    logDn1.append("。");
                    if (cash.get(i9).getCMoney() != null) {
                        logDn1.append("价格：").append(cash.get(i9).getCMoney()).append("；");
                    }
                    if (cash.get(i9).getCCount() != null) {
                        logDn1.append("数量：").append(cash.get(i9).getCCount()).append("；");
                    }
                } else {
                    Object[] obj = this.cashDAO.getCashInfo(cash.get(i9).getCId().intValue());
                    StringBuffer tmp = new StringBuffer();
                    boolean editFlag = false;
                    if ((cash.get(i9).getTCostItem() == null || cash.get(i9).getTCostItem().getCId() == null || cash.get(i9).getTCostItem().getCId() <= 0 || obj[0] == null || Integer.parseInt(obj[0].toString()) <= 0 || !cash.get(i9).getTCostItem().getCId().equals(Integer.parseInt(obj[0].toString()))) && (cash.get(i9).getTCostItem() != null && cash.get(i9).getTCostItem().getCId() != null && cash.get(i9).getTCostItem().getCId() != 0 || obj[0] != null && Integer.parseInt(obj[0].toString()) != 0)) {
                        tmp.append("Cash Item：");
                        if (obj[0] == null || Integer.parseInt(obj[0].toString()) == 0) {
                            tmp.append("PLEASE CHOOSE");
                        } else {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        tmp.append(" 改为 ");
                        if (cash.get(i9).getTCostItem() != null && cash.get(i9).getTCostItem().getCId() != null && cash.get(i9).getTCostItem().getCId() > 0) {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)cash.get(i9).getTCostItem().getCId()).getCName());
                        } else {
                            tmp.append("PLEASE CHOOSE");
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cash.get(i9).getCMoney() != null && obj[1] != null && cash.get(i9).getCMoney().equals(Double.parseDouble(obj[1].toString())) || cash.get(i9).getCMoney() == null && obj[1] == null)) {
                        tmp.append("价格：");
                        if (obj[1] == null) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[1].toString());
                        }
                        tmp.append(" 改为 ");
                        if (cash.get(i9).getCMoney() == null) {
                            tmp.append("");
                        } else {
                            tmp.append(cash.get(i9).getCMoney());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cash.get(i9).getCCount() != null && obj[2] != null && cash.get(i9).getCCount().equals(Double.parseDouble(obj[2].toString())) || cash.get(i9).getCCount() == null && obj[2] == null)) {
                        tmp.append("价格：");
                        if (obj[2] == null) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[2].toString());
                        }
                        tmp.append(" 改为 ");
                        if (cash.get(i9).getCCount() == null) {
                            tmp.append("");
                        } else {
                            tmp.append(cash.get(i9).getCCount());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logDn1.append("<br/>&nbsp;&nbsp;修改Debit Note，原Cash Item：");
                        if (obj[0] != null && Integer.parseInt(obj[0].toString()) != 0) {
                            logDn1.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        logDn1.append("。").append(tmp);
                        isAddNewCost = true;
                    }
                    editFlag = false;
                }
                cash.get(i9).setCState(Short.valueOf(state));
                if (cash.get(i9).getCCount() != null) {
                    cash.get(i9).setCCount(Double.valueOf(Double.parseDouble(cash.get(i9).getCCount().toString().trim())));
                }
                if (cash.get(i9).getCMoney() != null) {
                    cash.get(i9).setCMoney(Double.valueOf(Double.parseDouble(cash.get(i9).getCMoney().toString().trim())));
                }
                this.cashDAO.saveCash(cash.get(i9));
                cmoney += cash.get(i9).getCMoney() * cash.get(i9).getCCount();
                cashMoney1 += cash.get(i9).getCMoney() * cash.get(i9).getCCount();
            }
        }
        orderCount += count;
        String dn1 = bb.getCNodate1();
        if (count > 0 && bb.getCNodate2() != null) {
            cdate = this.formatter2.format(bb.getCNodate2());
            if (dn1 == null || "".equals(dn1)) {
                dn1 = this.businDAO.getMaxDN(cdate);
                bb.setCNodate1(dn1);
                logDn1.append("<br/>&nbsp;&nbsp;生成D.N1：").append(dn1);
            }
        } else if (dn1 != null && !"".equals(dn1)) {
            logDn1.append("<br/>&nbsp;&nbsp;清空D.N1：").append(dn1);
            TDelDn del = new TDelDn();
            del.setCType(Short.valueOf((short)1));
            del.setCCode(dn1);
            bb.setCNodate1(null);
            this.businDAO.saveDel(del);
        }
        if (count > 0) {
            bb.setCOrder(Integer.valueOf(0));
        }
        if ((cashTmpList = this.cashDAO.getList(businId, (short)1)) != null && cashTmpList.size() > 0) {
            for (int i10 = 0; i10 < cashTmpList.size(); ++i10) {
                dbid = ((TCash)cashTmpList.get(i10)).getCId();
                found = false;
                if (cash != null && cash.size() > 0) {
                    for (int j = 0; j < cash.size(); ++j) {
                        if (cash.get(j) == null || cash.get(j).getCId() == null || dbid != (businTmpId = cash.get(j).getCId().intValue())) continue;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    isAddNewCost = true;
                    logDn1.append("<br/>&nbsp;&nbsp;删除Debit Note，原Cash Item：");
                    if (((TCash)cashTmpList.get(i10)).getTCostItem() != null && ((TCash)cashTmpList.get(i10)).getTCostItem().getCId() != null && ((TCash)cashTmpList.get(i10)).getTCostItem().getCId() > 0) {
                        logDn1.append(this.costItemDAO.getEntityById((Serializable)((TCash)cashTmpList.get(i10)).getTCostItem().getCId()).getCName());
                    }
                    logDn1.append("。");
                    this.cashDAO.deleteCash((Serializable)Integer.valueOf(dbid));
                }
                found = false;
            }
        }
        if (bb.getCCostDate() != null) {
            if (bb.getCRefno() == null || "".equals(bb.getCRefno().trim())) {
                bb.setCRefno(this.businDAO.getMaxRefno());
            }
        } else {
            bb.setCRefno(null);
        }
        if (bb.getTSalerByCApplyby() == null || bb.getTSalerByCApplyby().getCId() == null || bb.getTSalerByCApplyby().getCId() == 0) {
            bb.setTSalerByCApplyby(null);
        }
        this.businDAO.saveMergeEntity(bb);
        count = 0;
        if (cash2 != null && cash2.size() > 0) {
            for (int i11 = 0; i11 < cash2.size(); ++i11) {
                if (flag == 1 && (cash2.get(i11) == null || (cash2.get(i11).getTCostItem() == null || cash2.get(i11).getTCostItem().getCId() == null || cash2.get(i11).getTCostItem().getCId() == 0) && cash2.get(i11).getCMoney() == null) || cash2.get(i11) == null) continue;
                ++count;
                ++cashCount;
                if (cash2.get(i11).getTCostItem() == null || cash2.get(i11).getTCostItem().getCId() == null || cash2.get(i11).getTCostItem().getCId() == 0) {
                    cash2.get(i11).setTCostItem(null);
                }
                if (cash2.get(i11).getCId() == null) {
                    cash2.get(i11).setTBusin(this.businDAO.getEntityById((Serializable)Integer.valueOf(businId)));
                    cash2.get(i11).setTUserByCCreateUserid(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    cash2.get(i11).setCCreatedate(CommonUtil.getDatetime());
                    bb.setCAddGroup(Short.valueOf((short)0));
                    isAddNewCost = true;
                    logDn.append("<br/>&nbsp;&nbsp;添加Re-Imbursement，Cash Item：");
                    if (cash2.get(i11).getTCostItem() != null && cash2.get(i11).getTCostItem().getCId() != null && cash2.get(i11).getTCostItem().getCId() > 0) {
                        logDn.append(this.costItemDAO.getEntityById((Serializable)cash2.get(i11).getTCostItem().getCId()).getCName());
                    } else {
                        logDn.append("PLEASE CHOOSE");
                    }
                    logDn.append("。");
                    if (cash2.get(i11).getCMoney() != null) {
                        logDn.append("价格：").append(cash2.get(i11).getCMoney()).append("；");
                    }
                    if (cash2.get(i11).getCCount() != null) {
                        logDn.append("数量：").append(cash2.get(i11).getCCount()).append("；");
                    }
                } else {
                    Object[] obj = this.cashDAO.getCashInfo(cash2.get(i11).getCId().intValue());
                    StringBuffer tmp = new StringBuffer();
                    boolean editFlag = false;
                    if ((cash2.get(i11).getTCostItem() == null || cash2.get(i11).getTCostItem().getCId() == null || cash2.get(i11).getTCostItem().getCId() <= 0 || obj[0] == null || Integer.parseInt(obj[0].toString()) <= 0 || !cash2.get(i11).getTCostItem().getCId().equals(Integer.parseInt(obj[0].toString()))) && (cash2.get(i11).getTCostItem() != null && cash2.get(i11).getTCostItem().getCId() != null && cash2.get(i11).getTCostItem().getCId() != 0 || obj[0] != null && Integer.parseInt(obj[0].toString()) != 0)) {
                        tmp.append("Cash Item：");
                        if (obj[0] == null || Integer.parseInt(obj[0].toString()) == 0) {
                            tmp.append("PLEASE CHOOSE");
                        } else {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        tmp.append(" 改为 ");
                        if (cash2.get(i11).getTCostItem() != null && cash2.get(i11).getTCostItem().getCId() != null && cash2.get(i11).getTCostItem().getCId() > 0) {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)cash2.get(i11).getTCostItem().getCId()).getCName());
                        } else {
                            tmp.append("PLEASE CHOOSE");
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cash2.get(i11).getCMoney() != null && obj[1] != null && cash2.get(i11).getCMoney().equals(Double.parseDouble(obj[1].toString())) || cash2.get(i11).getCMoney() == null && obj[1] == null)) {
                        tmp.append("价格：");
                        if (obj[1] == null) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[1].toString());
                        }
                        tmp.append(" 改为 ");
                        if (cash2.get(i11).getCMoney() == null) {
                            tmp.append("");
                        } else {
                            tmp.append(cash2.get(i11).getCMoney());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(cash2.get(i11).getCCount() != null && obj[2] != null && cash2.get(i11).getCCount().equals(Double.parseDouble(obj[2].toString())) || cash2.get(i11).getCCount() == null && obj[2] == null)) {
                        tmp.append("价格：");
                        if (obj[2] == null) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[2].toString());
                        }
                        tmp.append(" 改为 ");
                        if (cash2.get(i11).getCCount() == null) {
                            tmp.append("");
                        } else {
                            tmp.append(cash2.get(i11).getCCount());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logDn.append("<br/>&nbsp;&nbsp;修改Re-Imbursement，原Cash Item：");
                        if (obj[0] != null && Integer.parseInt(obj[0].toString()) != 0) {
                            logDn.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        logDn.append("。").append(tmp);
                        isAddNewCost = true;
                    }
                    editFlag = false;
                }
                cash2.get(i11).setCState(Short.valueOf(state));
                if (cash2.get(i11).getCCount() != null) {
                    cash2.get(i11).setCCount(Double.valueOf(Double.parseDouble(cash2.get(i11).getCCount().toString().trim())));
                }
                if (cash2.get(i11).getCMoney() != null) {
                    cash2.get(i11).setCMoney(Double.valueOf(Double.parseDouble(cash2.get(i11).getCMoney().toString().trim())));
                }
                cmoney += cash2.get(i11).getCMoney() * cash2.get(i11).getCCount();
                this.cashDAO.saveCash(cash2.get(i11));
                cashMoney2 += cash2.get(i11).getCMoney() * cash2.get(i11).getCCount();
            }
        }
        orderCount += count;
        String dn3 = bb.getCNodate3();
        if (count > 0 && bb.getCNodate4() != null) {
            cdate = this.formatter2.format(bb.getCNodate4());
            if (dn3 == null || "".equals(dn3)) {
                dn3 = this.businDAO.getMaxDN(cdate);
                bb.setCNodate3(dn3);
                logDn.append("<br/>&nbsp;&nbsp;生成D.N2：").append(dn3);
            }
        } else if (dn3 != null && !"".equals(dn3)) {
            logDn.append("<br/>&nbsp;&nbsp;清空D.N2：").append(dn3);
            TDelDn del = new TDelDn();
            del.setCType(Short.valueOf((short)1));
            del.setCCode(dn3);
            bb.setCNodate3(null);
            this.businDAO.saveDel(del);
        }
        if (count > 0) {
            bb.setCOrder(Integer.valueOf(0));
        }
        List cash2TmpList = this.cashDAO.getList(businId, (short)2);
        dbid = 0;
        businTmpId = 0;
        found = false;
        if (cash2TmpList != null && cash2TmpList.size() > 0) {
            for (int i12 = 0; i12 < cash2TmpList.size(); ++i12) {
                dbid = ((TCash)cash2TmpList.get(i12)).getCId();
                if (cash2 != null && cash2.size() > 0) {
                    for (int j = 0; j < cash2.size(); ++j) {
                        if (cash2.get(j) == null || cash2.get(j).getCId() == null || dbid != (businTmpId = cash2.get(j).getCId().intValue())) continue;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    isAddNewCost = true;
                    logDn.append("<br/>&nbsp;&nbsp;删除Re-Imbursement，原Cash Item：");
                    if (((TCash)cash2TmpList.get(i12)).getTCostItem() != null && ((TCash)cash2TmpList.get(i12)).getTCostItem().getCId() != null && ((TCash)cash2TmpList.get(i12)).getTCostItem().getCId() > 0) {
                        logDn.append(this.costItemDAO.getEntityById((Serializable)((TCash)cash2TmpList.get(i12)).getTCostItem().getCId()).getCName());
                    }
                    logDn.append("。");
                    this.cashDAO.deleteCash((Serializable)Integer.valueOf(dbid));
                }
                found = false;
            }
        }
        Double costRate = 0.0;
        Double sumCostRate = 0.0;
        count = 0;
        if (rate != null && rate.size() > 0) {
            for (int i13 = 0; i13 < rate.size(); ++i13) {
                if (flag == 1 && (rate.get(i13) == null || !(rate.get(i13).getTCostItem() != null && rate.get(i13).getTCostItem().getCId() != null && rate.get(i13).getTCostItem().getCId() != 0 || rate.get(i13).getTRate() != null && rate.get(i13).getTRate().getCId() != null && rate.get(i13).getTRate().getCId() != 0) && rate.get(i13).getCMoney() == null) || rate.get(i13) == null) continue;
                ++count;
                ++cashCount;
                if (rate.get(i13).getTCostItem() == null || rate.get(i13).getTCostItem().getCId() == null || rate.get(i13).getTCostItem().getCId() == 0) {
                    rate.get(i13).setTCostItem(null);
                }
                if (rate.get(i13).getCId() == null) {
                    rate.get(i13).setTBusin(this.businDAO.getEntityById((Serializable)Integer.valueOf(businId)));
                    rate.get(i13).setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    rate.get(i13).setCCreatedate(CommonUtil.getDatetime());
                    isAddNewCost = true;
                    bb.setCAddGroup(Short.valueOf((short)0));
                }
                rate.get(i13).setCState(Short.valueOf(state));
                if (rate.get(i13).getCCount() != null) {
                    rate.get(i13).setCCount(Double.valueOf(Double.parseDouble(rate.get(i13).getCCount().toString().trim())));
                }
                if (rate.get(i13).getCMoney() != null) {
                    rate.get(i13).setCMoney(Double.valueOf(Double.parseDouble(rate.get(i13).getCMoney().toString().trim())));
                }
                if (rate.get(i13).getCCount() != null && rate.get(i13).getCMoney() != null) {
                    TRate rr = this.rateDAO.getEntityById((Serializable)rate.get(i13).getTRate().getCId());
                    costRate = costRate + rate.get(i13).getCCount() * rate.get(i13).getCMoney() * rr.getCRate() / 100.0;
                    sumCostRate = sumCostRate + rate.get(i13).getCCount() * rate.get(i13).getCMoney();
                    cmoney += rate.get(i13).getCCount() * rate.get(i13).getCMoney() + rate.get(i13).getCCount() * rate.get(i13).getCMoney() * rr.getCRate() / 100.0;
                }
                this.cashRateDAO.saveRate(rate.get(i13));
            }
        }
        orderCount += count;
        String dn5 = bb.getCNodate5();
        if (count > 0 && bb.getCNodate6() != null) {
            if (dn5 == null || "".equals(dn5)) {
                cdate = this.formatter2.format(bb.getCNodate6());
                dn5 = this.businDAO.getMaxINV(cdate);
                bb.setCNodate5(dn5);
            }
        } else if (dn5 != null && !"".equals(dn5)) {
            TDelDn del = new TDelDn();
            del.setCType(Short.valueOf((short)2));
            del.setCCode(dn5);
            bb.setCNodate5(null);
            this.businDAO.saveDel(del);
        }
        if (count > 0) {
            bb.setCOrder(Integer.valueOf(0));
        }
        List rateTmpList = this.cashRateDAO.getList(businId, (short)1);
        int dbid2 = 0;
        int businTmpId2 = 0;
        boolean found2 = false;
        if (rateTmpList != null && rateTmpList.size() > 0) {
            for (i = 0; i < rateTmpList.size(); ++i) {
                dbid2 = ((TCashRate)rateTmpList.get(i)).getCId();
                if (rate != null && rate.size() > 0) {
                    for (int j = 0; j < rate.size(); ++j) {
                        if (rate.get(j) == null || rate.get(j).getCId() == null || dbid2 != (businTmpId2 = rate.get(j).getCId().intValue())) continue;
                        found2 = true;
                        break;
                    }
                }
                if (!found2) {
                    isAddNewCost = true;
                    this.cashRateDAO.deleteRate((Serializable)Integer.valueOf(dbid2));
                }
                found2 = false;
            }
        }
        count = 0;
        if (rate2 != null && rate2.size() > 0) {
            for (i = 0; i < rate2.size(); ++i) {
                if (flag == 1 && (rate2.get(i) == null || !(rate2.get(i).getTCostItem() != null && rate2.get(i).getTCostItem().getCId() != null && rate2.get(i).getTCostItem().getCId() != 0 || rate2.get(i).getTRate() != null && rate2.get(i).getTRate().getCId() != null && rate2.get(i).getTRate().getCId() != 0) && rate2.get(i).getCMoney() == null) || rate2.get(i) == null) continue;
                ++count;
                ++cashCount;
                if (rate2.get(i).getTCostItem() == null || rate2.get(i).getTCostItem().getCId() == null || rate2.get(i).getTCostItem().getCId() == 0) {
                    rate2.get(i).setTCostItem(null);
                }
                if (rate2.get(i).getCId() == null) {
                    rate2.get(i).setTBusin(this.businDAO.getEntityById((Serializable)Integer.valueOf(businId)));
                    rate2.get(i).setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
                    rate2.get(i).setCCreatedate(CommonUtil.getDatetime());
                    isAddNewCost = true;
                    bb.setCAddGroup(Short.valueOf((short)0));
                    logInv.append("<br/>&nbsp;&nbsp;添加Tax Invoice，Cash Item：");
                    if (rate2.get(i).getTCostItem() != null && rate2.get(i).getTCostItem().getCId() != null && rate2.get(i).getTCostItem().getCId() > 0) {
                        logInv.append(this.costItemDAO.getEntityById((Serializable)rate2.get(i).getTCostItem().getCId()).getCName());
                    } else {
                        logInv.append("PLEASE CHOOSE");
                    }
                    logInv.append("。");
                    if (rate2.get(i).getCMoney() != null) {
                        logInv.append("价格：").append(rate2.get(i).getCMoney()).append("；");
                    }
                    if (rate2.get(i).getCCount() != null) {
                        logInv.append("数量：").append(rate2.get(i).getCCount()).append("；");
                    }
                    if (rate2.get(i).getTRate() != null && rate2.get(i).getTRate().getCId() != null) {
                        logInv.append("税率：").append(this.rateDAO.getEntityById((Serializable)rate2.get(i).getTRate().getCId()).getCRate()).append("；");
                    }
                } else {
                    Object[] obj = this.cashRateDAO.getCashInfo(rate2.get(i).getCId().intValue());
                    StringBuffer tmp = new StringBuffer();
                    boolean editFlag = false;
                    if ((rate2.get(i).getTCostItem() == null || rate2.get(i).getTCostItem().getCId() == null || rate2.get(i).getTCostItem().getCId() <= 0 || obj[0] == null || Integer.parseInt(obj[0].toString()) <= 0 || !rate2.get(i).getTCostItem().getCId().equals(Integer.parseInt(obj[0].toString()))) && (rate2.get(i).getTCostItem() != null && rate2.get(i).getTCostItem().getCId() != null && rate2.get(i).getTCostItem().getCId() != 0 || obj[0] != null && Integer.parseInt(obj[0].toString()) != 0)) {
                        tmp.append("Cash Item：");
                        if (obj[0] == null || Integer.parseInt(obj[0].toString()) == 0) {
                            tmp.append("PLEASE CHOOSE");
                        } else {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        tmp.append(" 改为 ");
                        if (rate2.get(i).getTCostItem() != null && rate2.get(i).getTCostItem().getCId() != null && rate2.get(i).getTCostItem().getCId() > 0) {
                            tmp.append(this.costItemDAO.getEntityById((Serializable)rate2.get(i).getTCostItem().getCId()).getCName());
                        } else {
                            tmp.append("PLEASE CHOOSE");
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(rate2.get(i).getCMoney() != null && obj[1] != null && rate2.get(i).getCMoney().equals(Double.parseDouble(obj[1].toString())) || rate2.get(i).getCMoney() == null && obj[1] == null)) {
                        tmp.append("价格：");
                        if (obj[1] == null) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[1].toString());
                        }
                        tmp.append(" 改为 ");
                        if (rate2.get(i).getCMoney() == null) {
                            tmp.append("");
                        } else {
                            tmp.append(rate2.get(i).getCMoney());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (!(rate2.get(i).getCCount() != null && obj[2] != null && rate2.get(i).getCCount().equals(Double.parseDouble(obj[2].toString())) || rate2.get(i).getCCount() == null && obj[2] == null)) {
                        tmp.append("价格：");
                        if (obj[2] == null) {
                            tmp.append("");
                        } else {
                            tmp.append(obj[2].toString());
                        }
                        tmp.append(" 改为 ");
                        if (rate2.get(i).getCCount() == null) {
                            tmp.append("");
                        } else {
                            tmp.append(rate2.get(i).getCCount());
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if ((rate2.get(i).getTRate() == null || rate2.get(i).getTRate().getCId() == null || rate2.get(i).getTRate().getCId() <= 0 || obj[3] == null || Integer.parseInt(obj[3].toString()) <= 0 || !rate2.get(i).getTRate().getCId().equals(Integer.parseInt(obj[3].toString()))) && (rate2.get(i).getTRate() != null && rate2.get(i).getTRate().getCId() != null && rate2.get(i).getTRate().getCId() != 0 || obj[3] != null && Integer.parseInt(obj[3].toString()) != 0)) {
                        tmp.append("税率：");
                        if (obj[3] == null || Integer.parseInt(obj[3].toString()) == 0) {
                            tmp.append("");
                        } else {
                            tmp.append(this.rateDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[3].toString()))).getCRate());
                        }
                        tmp.append(" 改为 ");
                        if (rate2.get(i).getTRate() != null && rate2.get(i).getTRate().getCId() != null && rate2.get(i).getTRate().getCId() > 0) {
                            tmp.append(this.rateDAO.getEntityById((Serializable)rate2.get(i).getTRate().getCId()).getCRate());
                        } else {
                            tmp.append("");
                        }
                        tmp.append("；");
                        editFlag = true;
                    }
                    if (editFlag) {
                        logInv.append("<br/>&nbsp;&nbsp;修改Tax Invoice，原Cash Item：");
                        if (obj[0] != null && Integer.parseInt(obj[0].toString()) != 0) {
                            logInv.append(this.costItemDAO.getEntityById((Serializable)Integer.valueOf(Integer.parseInt(obj[0].toString()))).getCName());
                        }
                        logInv.append("。").append(tmp);
                        isAddNewCost = true;
                    }
                    editFlag = false;
                }
                rate2.get(i).setCState(Short.valueOf(state));
                if (rate2.get(i).getCCount() != null) {
                    rate2.get(i).setCCount(Double.valueOf(Double.parseDouble(rate2.get(i).getCCount().toString().trim())));
                }
                if (rate2.get(i).getCMoney() != null) {
                    rate2.get(i).setCMoney(Double.valueOf(Double.parseDouble(rate2.get(i).getCMoney().toString().trim())));
                }
                if (rate2.get(i).getCCount() != null && rate2.get(i).getCMoney() != null) {
                    TRate rr = this.rateDAO.getEntityById((Serializable)rate2.get(i).getTRate().getCId());
                    costRate = costRate + rate2.get(i).getCCount() * rate2.get(i).getCMoney() * rr.getCRate() / 100.0;
                    sumCostRate = sumCostRate + rate2.get(i).getCCount() * rate2.get(i).getCMoney();
                    cmoney += rate2.get(i).getCCount() * rate2.get(i).getCMoney() + rate2.get(i).getCCount() * rate2.get(i).getCMoney() * rr.getCRate() / 100.0;
                    cashMoney4 += rate2.get(i).getCCount() * rate2.get(i).getCMoney() + rate2.get(i).getCCount() * rate2.get(i).getCMoney() * rr.getCRate() / 100.0;
                }
                this.cashRateDAO.saveRate(rate2.get(i));
            }
        }
        orderCount += count;
        String dn7 = bb.getCNodate7();
        if (count > 0 && bb.getCNodate8() != null) {
            if (dn7 == null || "".equals(dn7)) {
                cdate = this.formatter2.format(bb.getCNodate8());
                dn7 = this.businDAO.getMaxINV(cdate);
                bb.setCNodate7(dn7);
                logInv.append("<br/>&nbsp;&nbsp;生成INV2：").append(dn7);
            }
        } else if (dn7 != null && !"".equals(dn7)) {
            logInv.append("<br/>&nbsp;&nbsp;清空INV2：").append(dn7);
            TDelDn del = new TDelDn();
            del.setCType(Short.valueOf((short)2));
            del.setCCode(dn7);
            bb.setCNodate7(null);
            this.businDAO.saveDel(del);
        }
        if (count > 0) {
            bb.setCOrder(Integer.valueOf(0));
        }
        List rateTmpList2 = this.cashRateDAO.getList(businId, (short)2);
        dbid2 = 0;
        businTmpId2 = 0;
        found2 = false;
        if (rateTmpList2 != null && rateTmpList2.size() > 0) {
            for (int i14 = 0; i14 < rateTmpList2.size(); ++i14) {
                dbid2 = ((TCashRate)rateTmpList2.get(i14)).getCId();
                if (rate2 != null && rate2.size() > 0) {
                    for (int j = 0; j < rate2.size(); ++j) {
                        if (rate2.get(j) == null || rate2.get(j).getCId() == null || dbid2 != (businTmpId2 = rate2.get(j).getCId().intValue())) continue;
                        found2 = true;
                        break;
                    }
                }
                if (!found2) {
                    isAddNewCost = true;
                    logInv.append("<br/>&nbsp;&nbsp;删除Tax Invoice，原Cash Item：");
                    if (((TCashRate)rateTmpList2.get(i14)).getTCostItem() != null && ((TCashRate)rateTmpList2.get(i14)).getTCostItem().getCId() != null && ((TCashRate)rateTmpList2.get(i14)).getTCostItem().getCId() > 0) {
                        logInv.append(this.costItemDAO.getEntityById((Serializable)((TCashRate)rateTmpList2.get(i14)).getTCostItem().getCId()).getCName());
                    }
                    logInv.append("。");
                    this.cashRateDAO.deleteRate((Serializable)Integer.valueOf(dbid2));
                }
                found2 = false;
            }
        }
        if ((jj = this.reportDAO.getJiezhuan(businId)) != null) {
            if (jj.getCNotice() == 0 && jj.getCMoney2() != cmoney) {
                jj.setCNotice(Short.valueOf((short)1));
            }
            jj.setCMoney2(Double.valueOf(cmoney));
            this.reportDAO.saveJiezhuan(jj);
        }
        jj = null;
        if (isAddNewCost && bb.getCRecieveFlag() == 1) {
            bb.setCNewCostFlag(Short.valueOf((short)1));
            businLog.setCNewCostFlag(Short.valueOf((short)1));
        }
        bb.setCLockFlag(Short.valueOf((short)0));
        int pp = bb.getCBillNo().indexOf("-");
        int businTypeId = bb.getTBusinType().getCId();
        TBusinType tt = this.businTypeDAO.getEntityById((Serializable)Integer.valueOf(businTypeId));
        bb.setCBillNo(String.valueOf(tt.getCName()) + "-" + bb.getCBillNo().substring(pp + 1));
        bb.setCOrder(Integer.valueOf(0));
        if (bb.getCArrivalDate() != null && !"".equals(bb.getCArrivalDate()) && orderCount == 0) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(bb.getCArrivalDate());
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(CommonUtil.getDatetime());
            int days = (int)((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / 86400000L);
            if (days >= 7) {
                bb.setCOrder(Integer.valueOf(1));
            }
        }
        bb.setCCashDate(this.businDAO.getCashDate(bb.getCId().intValue()));
        bb.setCCashMoney1(Double.valueOf(cashMoney1));
        bb.setCCashMoney2(Double.valueOf(cashMoney2));
        bb.setCCashMoney4(Double.valueOf(cashMoney4));
        if (costRate != null && costRate > 0.0) {
            costSumMoney1 += costRate.doubleValue();
        }
        if (sumCostRate != null && sumCostRate > 0.0) {
            costSumMoney1 += sumCostRate * 0.01;
        }
        bb.setCCostSum1(Double.valueOf(costSumMoney1));
        bb.setCCostSum2(Double.valueOf(costSumMoney2));
        this.businDAO.saveMergeEntity(bb);
        this.businDAO.updateCostRate(businId, costRate.doubleValue());
        this.businDAO.updateCostRate2(businId, sumCostRate * 0.01);
        String logBaseinfo = logs[0];
        String logRemarks = logs[1];
        StringBuffer logNote = new StringBuffer();
        if (!"".equals(logBaseinfo.trim())) {
            logNote.append("<br/>BASE INFO，").append(logBaseinfo.trim());
        }
        if (!"".equals(logCostinfo.toString().trim())) {
            logNote.append("<br/>COST INFO：").append(logCostinfo.toString().trim());
        }
        if (!"".equals(logDn1.toString().trim())) {
            logNote.append("<br/>Debit Note ，").append(logDn1.toString().trim());
        }
        if (!"".equals(logDn.toString().trim())) {
            logNote.append("<br/>Re-Imbursement ，").append(logDn.toString().trim());
        }
        if (!"".equals(logInv.toString().trim())) {
            logNote.append("<br/>Tax Invoice，").append(logInv.toString().trim());
        }
        if (!"".equals(logRemarks.trim())) {
            logNote.append("<br/>修改Remarks INFO，").append(logRemarks.trim());
        }
        if (!"".equals(logNote.toString().trim())) {
            businLog.setCNote("修改单据。打开服务单时间：" + logs[2] + "，保存服务单时间：" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(CommonUtil.getDatetime()) + "。" + logNote.toString());
            this.businLogDAO.saveEntity(businLog);
        }
    }

    public TBusin getEntityById(Serializable id) {
        return this.businDAO.getEntityById(id);
    }

    public void deleteEntity(int id,TBusinHis his) throws Exception {
		/*
		 * this.containerDAO.deleteContainerByBusinid(id);
		 * this.businServiceDAO.deleteServiceByBusinid((Serializable)Integer.valueOf(id)
		 * ); this.costDAO.deleteCostByBusinid(id);
		 * this.cashDAO.deleteCashByBusinid(id);
		 * this.cashRateDAO.deleteRateByBusinid(id); this.businLogDAO.deleteEntity(id);
		 */
    	businDAO.saveBusinHis(his);
        this.businDAO.deleteEntity((Serializable)Integer.valueOf(id));
    }

    public void updateEntityState(Serializable id, short state, String remarks) throws Exception {
        this.businDAO.updateEntityState(id, state, remarks);
        TBusin tmp = this.businDAO.getEntityById(id);
        TBusinLog businLog = new TBusinLog();
        businLog.setCDate(CommonUtil.getDatetime());
        businLog.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
        businLog.setTBusin(tmp);
        if (state == 3 || state == 5) {
            businLog.setCNote("经理审核");
            businLog.setCResult(Short.valueOf((short)1));
        } else if (state == 4) {
            businLog.setCNote("经理审核");
            businLog.setCResult(Short.valueOf((short)0));
        } else if (state == 6) {
            businLog.setCNote("财务审核");
            businLog.setCResult(Short.valueOf((short)1));
        } else if (state == 7) {
            businLog.setCNote("财务审核");
            businLog.setCResult(Short.valueOf((short)0));
        } else if (state == 8) {
            businLog.setCNote("经理归档");
        }
    }

    public void updateCostState(Serializable businId, short state) throws Exception {
        this.costDAO.updateCostState(businId, state);
    }

    public List<VLog> getLogList(int businId) {
        return this.businLogDAO.getlist(businId);
    }

    public void updatePrintState(Serializable id) throws Exception {
        this.businDAO.updatePrintState(id);
    }

    public void updateOrder(int businId, short flag) throws Exception {
        this.businDAO.updateOrder(businId, flag);
    }

    public void updateRecieveMoney(int businId, double money, int flag) throws Exception {
        this.businDAO.updateRecieveMoney(businId, money, flag);
    }

    public void updateScheduleArchive(int businId, short flag, double cmoney) throws Exception {
        double money = this.businDAO.getBusinLimit(businId);
        this.businDAO.updateScheduleArchive(businId, flag, money);
    }

    public void updateScheduleArchive2(int[] businId, short flag) throws Exception {
        for (int i = 0; i < businId.length; ++i) {
            double cmoney = this.businDAO.getBusinLimit(businId[i]);
            this.businDAO.updateScheduleArchive(businId[i], flag, cmoney);
        }
    }

    public void updateRateArchive(int businId, short flag) throws Exception {
        TBusin bb = this.businDAO.getEntityById((Serializable)Integer.valueOf(businId));
        bb.setCComplete(Short.valueOf(flag));
        bb.setCRateArchive(Short.valueOf(flag));
        this.businDAO.saveEntity(bb);
    }

    public void updateRateArchive(int[] businIds, short flag) throws Exception {
        for (int i = 0; i < businIds.length; ++i) {
            TBusin bb = this.businDAO.getEntityById((Serializable)Integer.valueOf(businIds[i]));
            bb.setCComplete(Short.valueOf(flag));
            bb.setCRateArchive(Short.valueOf(flag));
            this.businDAO.saveEntity(bb);
        }
    }

    public void updateLock(int businId, short flag) throws Exception {
        this.businDAO.updateLock(businId, flag);
    }

    public void updateLock(int[] businid, short lock) throws Exception {
        for (int i = 0; i < businid.length; ++i) {
            this.businDAO.updateLock(businid[i], lock);
        }
    }

    public void updateLockFlag(int businId, short flag) throws Exception {
        this.businDAO.updateLockFlag(businId, flag);
    }

    public void updateNewCostFlag2(int businId) throws Exception {
        TBusin bb = this.businDAO.getEntityById((Serializable)Integer.valueOf(businId));
        bb.setCNewCostFlag(Short.valueOf((short)0));
        this.businDAO.saveEntity(bb);
        this.businDAO.updateLogNewFlag(businId);
    }

    public double getProfit(int businid) {
        return this.businDAO.getProfit(businid);
    }

    public List<VBusincusreport> getBusinCusReport(Object[] obj) {
        return this.businDAO.getBusinCusReport(obj);
    }

    public void updateCostDupFlag(int businId) throws Exception {
        this.businDAO.updateCostDupFlag(businId);
    }

    public void updateLocks() throws Exception {
    }

    public void updateOrders() throws Exception {
        this.businDAO.updateOrders();
    }

    public List<TFinanceGroup> getGroupList(TFinanceGroup query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getGroupList(query, obj, pageNow, pageSize);
    }

    public PageBean getGroupPages(TFinanceGroup query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getGroupPages(query, obj, pageNow, pageSize);
    }

    public void saveGroup(TFinanceGroup entity, List<TFinanceGroupItem> detail) throws Exception {
        Integer cid = entity.getCId();
        this.businDAO.saveGroup(entity);
        for (int i = 0; i < detail.size(); ++i) {
            if (detail.get(i) == null) continue;
            if (detail.get(i).getCId() == null) {
                detail.get(i).setCPid(entity.getCId());
            }
            this.businDAO.saveGroupDetail(detail.get(i));
        }
        if (cid != null) {
            int dbid = 0;
            int tmpid = 0;
            boolean found = false;
            List dbList = this.businDAO.getGroupDetail(cid.intValue());
            if (dbList != null && dbList.size() > 0) {
                for (int i = 0; i < dbList.size(); ++i) {
                    dbid = ((TFinanceGroupItem)dbList.get(i)).getCId();
                    if (detail != null && detail.size() > 0) {
                        for (int j = 0; j < detail.size(); ++j) {
                            if (detail.get(j) == null || detail.get(j).getCId() == null || dbid != (tmpid = detail.get(j).getCId().intValue())) continue;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        this.businDAO.deleteGroupDetail(dbid);
                    }
                    found = false;
                }
            }
        }
    }

    public TFinanceGroup getGroupById(int id) {
        return this.businDAO.getGroupById(id);
    }

    public void deleteGroup(int id) throws Exception {
        this.businDAO.deleteAllGroupDetail(id);
        this.businDAO.deleteGroup(id);
    }

    public int checkName(String name, Integer id) {
        return this.businDAO.checkName(name, id);
    }

    public void saveGroupDetail(TFinanceGroupItem item) throws Exception {
        this.businDAO.saveGroupDetail(item);
    }

    public List<TFinanceGroupItem> getGroupDetail(int pid) {
        return this.businDAO.getGroupDetail(pid);
    }

    public List<TFinanceGroup> getGroupValidList() {
        return this.businDAO.getGroupValidList();
    }

    public List<TCostGroup> getGroupList(TCostGroup query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getGroupList(query, obj, pageNow, pageSize);
    }

    public PageBean getGroupPages(TCostGroup query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getGroupPages(query, obj, pageNow, pageSize);
    }

    public void saveGroup(TCostGroup entity, List<TCostGroupItem> detail) throws Exception {
        Integer cid = entity.getCId();
        this.businDAO.saveGroup(entity);
        for (int i = 0; i < detail.size(); ++i) {
            if (detail.get(i) == null) continue;
            if (detail.get(i).getCId() == null) {
                detail.get(i).setCPid(entity.getCId());
            }
            this.businDAO.saveGroupDetail(detail.get(i));
        }
        if (cid != null) {
            int dbid = 0;
            int tmpid = 0;
            boolean found = false;
            List dbList = this.businDAO.getCostGroupDetail(cid.intValue());
            if (dbList != null && dbList.size() > 0) {
                for (int i = 0; i < dbList.size(); ++i) {
                    dbid = ((TCostGroupItem)dbList.get(i)).getCId();
                    if (detail != null && detail.size() > 0) {
                        for (int j = 0; j < detail.size(); ++j) {
                            if (detail.get(j) == null || detail.get(j).getCId() == null || dbid != (tmpid = detail.get(j).getCId().intValue())) continue;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        this.businDAO.deleteCostGroupDetail(dbid);
                    }
                    found = false;
                }
            }
        }
    }

    public TCostGroup getCostGroupById(int id) {
        return this.businDAO.getCostGroupById(id);
    }

    public void deleteCostGroup(int id) throws Exception {
        this.businDAO.deleteAllCostGroupInfo(id);
        this.businDAO.deleteAllCostGroupDetail(id);
        this.businDAO.deleteCostGroup(id);
    }

    public int checkCostGroupName(String name, Integer id) {
        return this.businDAO.checkCostGroupName(name, id);
    }

    public List<TCostGroupItem> getCostGroupDetail(int pid) {
        return this.businDAO.getCostGroupDetail(pid);
    }

    public List<TCostGroup> getCostGroupValidList() {
        return this.businDAO.getCostGroupValidList();
    }

    public List<VBusinInvUser> getInvUserList(VBusinInvUser query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getInvUserList(query, obj, pageNow, pageSize);
    }

    public PageBean getInvUserPages(VBusinInvUser query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getInvUserPages(query, obj, pageNow, pageSize);
    }

    public List<VBusinSumReport> getBusinSumReport(VBusinSumReport query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getBusinSumReport(query, obj, pageNow, pageSize);
    }

    public PageBean getBusinSumReportPages(VBusinSumReport query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getBusinSumReportPages(query, obj, pageNow, pageSize);
    }

    public void updatebusinComplete(int businid, short archiveFlag) throws Exception {
        block3: {
            block4: {
                TBusin bb = this.businDAO.getEntityById((Serializable)Integer.valueOf(businid));
                bb.setCComplete(Short.valueOf(archiveFlag));
                this.businDAO.saveEntity(bb);
                if (bb.getCRecieveFlag() != 1) break block3;
                if (archiveFlag != 1) break block4;
                List costList = this.costDAO.getList(businid, (short)9);
                if (costList == null || costList.size() <= 0) break block3;
                for (int i = 0; i < costList.size(); ++i) {
                    TCost cost = (TCost)costList.get(i);
                    cost.setCHidden(Short.valueOf((short)1));
                    this.costDAO.saveCost(cost);
                }
                break block3;
            }
            List costList = this.costDAO.getList(businid, (short)9);
            if (costList != null && costList.size() > 0) {
                for (int i = 0; i < costList.size(); ++i) {
                    TCost cost = (TCost)costList.get(i);
                    cost.setCHidden(Short.valueOf((short)0));
                    this.costDAO.saveCost(cost);
                }
            }
        }
    }

    public List<TCostGroupInfo> getCostGroupInfoList(int businId) {
        return this.businDAO.getCostGroupInfoList(businId);
    }

    public TCostGroupInfo getCostGroupInfo(int businId, int groupId) {
        return this.businDAO.getCostGroupInfo(businId, groupId);
    }

    public void saveCostGroupInfo(TCostGroupInfo info) throws Exception {
        this.businDAO.saveCostGroupInfo(info);
        TBiaoji bj = this.businDAO.getBiaoji(info.getCBusinId().intValue(), info.getTCostGroup().getCId().intValue());
        if (bj != null) {
            BigDecimal data2;
            VCostGroupSum ss = this.costDAO.getCostGroupSumByBusinId(info.getCBusinId().intValue(), info.getTCostGroup().getCId().intValue());
            BigDecimal data1 = new BigDecimal(bj.getCMoney2());
            if (data1.compareTo(data2 = new BigDecimal(ss.getId().getTotal())) != 0) {
                bj.setCMoney2(ss.getId().getTotal());
                bj.setCNotice(Short.valueOf((short)1));
                this.businDAO.saveBiaoji(bj);
            }
        }
        double costSum2 = 0.0;
        List costs = this.costDAO.getCostGroupSumByBusinId(info.getCBusinId().intValue());
        if (costs != null && costs.size() > 0) {
            for (int i = 0; i < costs.size(); ++i) {
                double creil = 0.0;
                double cmoney = 0.0;
                double exchange = 0.0;
                if (!((VCostGroupSum)costs.get(i)).getId().getGroupId().equals(info.getTCostGroup().getCId())) {
                    costSum2 += ((VCostGroupSum)costs.get(i)).getId().getTotal().doubleValue();
                    continue;
                }
                if (info.getCExchange() != null) {
                    exchange = info.getCExchange();
                }
                if (((VCostGroupSum)costs.get(i)).getId().getSumRiel() != null) {
                    creil = ((VCostGroupSum)costs.get(i)).getId().getSumRiel();
                }
                if (((VCostGroupSum)costs.get(i)).getId().getSumMoney() != null) {
                    cmoney = ((VCostGroupSum)costs.get(i)).getId().getSumMoney();
                }
                double tmp = 0.0;
                if (exchange != 0.0) {
                    tmp = creil / exchange + cmoney;
                }
                costSum2 += tmp;
            }
        }
        TBusin bb = this.businDAO.getEntityById((Serializable)info.getCBusinId());
        bb.setCCostSum2(Double.valueOf(costSum2));
        this.businDAO.saveEntity(bb);
    }

    public String getMaxRefno() {
        return this.businDAO.getMaxRefno();
    }

    public void saveBiaoji(TBiaoji bb) throws Exception {
        this.businDAO.saveBiaoji(bb);
    }

    public TBiaoji getBiaoji(int businId, int groupId) {
        return this.businDAO.getBiaoji(businId, groupId);
    }

    public List<TBiaoji> getCostGroupBiaojiReport(TBiaoji query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getCostGroupBiaojiReport(query, obj, pageNow, pageSize);
    }

    public PageBean getCostGroupBiaojiReportPages(TBiaoji query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getCostGroupBiaojiReportPages(query, obj, pageNow, pageSize);
    }

    public List<TBiaoji> getCostGroupBiaojiReport(int businId) {
        return this.businDAO.getCostGroupBiaojiReport(businId);
    }

    public int checkBiaojiNotExistsCostgroupinfo(int businId) {
        return this.businDAO.checkBiaojiNotExistsCostgroupinfo(businId);
    }

    public List<VBusinInvUser> getProblemNoticeList(VBusinAsk query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getProblemNoticeList(query, obj, pageNow, pageSize);
    }

    public PageBean getProblemNoticePages(VBusinAsk query, Object[] obj, int pageNow, int pageSize) {
        return this.businDAO.getProblemNoticePages(query, obj, pageNow, pageSize);
    }

    public void setBusinDAO(BusinDAO businDAO) {
        this.businDAO = businDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setContainerDAO(ContainerDAO containerDAO) {
        this.containerDAO = containerDAO;
    }

    public void setBusinServiceDAO(BusinServiceDAO businServiceDAO) {
        this.businServiceDAO = businServiceDAO;
    }

    public void setCostDAO(CostDAO costDAO) {
        this.costDAO = costDAO;
    }

    public void setCashDAO(CashDAO cashDAO) {
        this.cashDAO = cashDAO;
    }

    public BusinDAO getBusinDAO() {
        return this.businDAO;
    }

    public void setBusinTypeDAO(BusinTypeDAO businTypeDAO) {
        this.businTypeDAO = businTypeDAO;
    }

    public void setBusinLogDAO(BusinLogDAO businLogDAO) {
        this.businLogDAO = businLogDAO;
    }

    public void setCostItemDAO(CostItemDAO costItemDAO) {
        this.costItemDAO = costItemDAO;
    }

    public void setRateDAO(RateDAO rateDAO) {
        this.rateDAO = rateDAO;
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

}


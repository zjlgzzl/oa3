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
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.report.dao.ReportDAO
 *  com.oa.report.dao.ReportDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.report.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.auth.action.UserInfo;
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
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.bank.service.BankService;
import com.oa.bank.service.BankServiceImpl;

public class ReportDAOImpl
extends HibernateDaoSupport
implements ReportDAO {
	SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat fmt2 = new SimpleDateFormat("dd-MM-yyyy");
    public String getBusinReceiveHql(VBusinreceivereport query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VBusinreceivereport");
        
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim()) && obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and ((cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate1 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate2 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate3 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (cdate4 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate4 <= '").append(obj[1].toString()).append(" 23:59:59'))");
            } else {
                if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                    whereStr.append(" and (cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" cdate4 >= '").append(obj[0].toString()).append(" 00:00:00')");
                }
                if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                    whereStr.append(" and (cdate1 <= '").append(obj[1].toString()).append(" 23:59:59' or").append(" cdate2 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" cdate3 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" cdate4 <= '").append(obj[1].toString()).append(" 23:59:59')");
                }
            }
            if (obj[3] != null) {
                whereStr.append(" and schedule_archive = ").append(obj[3]);
            }
            if(obj.length >= 6) {
            	if(!"1".equals(String.valueOf(obj[5]))) {
                	whereStr.append(" and salerId in (select saler.CId from TSaler saler where saler.user.CId = ").append(UserInfo.getUserId()).append(")");
                }
            }
        }
        if (query != null) {
            if (query.getCusName() != null && !"".equals(query.getCusName().trim())) {
                whereStr.append(" and cusName like '%").append(query.getCusName().trim()).append("%'");
            }
            if (query.getCusId() != null && query.getCusId() > 0) {
                whereStr.append(" and cusId = ").append(query.getCusId());
            }
            if (query.getCusId2() != null && query.getCusId2() > 0) {
                whereStr.append(" and cusId2 = ").append(query.getCusId2());
            }
            if (query.getBillNo() != null && !"".equals(query.getBillNo().trim())) {
                whereStr.append(" and billNo like '%").append(query.getBillNo().trim()).append("%'");
            }
            if (query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
                whereStr.append(" and empName like '%").append(query.getEmpName().trim()).append("%'");
            }
            if (query.getNodate5() != null && !"".equals(query.getNodate5().trim())) {
                whereStr.append(" and (").append(" reim like '%").append(query.getNodate5().trim()).append("%' or ").append(" inv like '%").append(query.getNodate5().trim()).append("%' or ").append(" nodate5 like '%").append(query.getNodate5().trim()).append("%' or ").append(" nodate7 like '%").append(query.getNodate5().trim()).append("%'").append(")");
            }
            if (query.getPayComplete() != null && query.getPayComplete() > -1) {
                whereStr.append(" and payComplete = ").append(query.getPayComplete());
            }
            if (query.getSalerId() != null && query.getSalerId() > -1) {
                whereStr.append(" and salerId = ").append(query.getSalerId());
            }
            if (query.getTypeId() != null && query.getTypeId() > -1) {
            	whereStr.append(" and typeId = ").append(query.getTypeId());
            }
            if (query.getOverDaysFlag() != null) {
            	whereStr.append(" and overDaysFlag = ").append(query.getOverDaysFlag());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            if (Integer.parseInt(obj[4].toString()) == 1) {
                return hql.append(" order by overDaysFlag desc,(cmoney - receiveMoney1 - receiveMoney2 - receiveMoney3 - receiveMoney4 - receiveMoney) asc, cdate desc, cusId asc").toString();
            }
            if (Integer.parseInt(obj[4].toString()) == 2) {
                return hql.append(" order by overDaysFlag desc,(cmoney - receiveMoney1 - receiveMoney2 - receiveMoney3 - receiveMoney4 - receiveMoney) desc, cdate desc, cusId asc").toString();
            }
            if (Integer.parseInt(obj[4].toString()) == 3) {
                return hql.append(" order by overDaysFlag desc,lastDate2 asc, cdate desc, cusId asc").toString();
            }
            if (Integer.parseInt(obj[4].toString()) == 4) {
                return hql.append(" order by overDaysFlag desc,lastDate2 desc, cdate desc, cusId asc").toString();
            }
            return hql.append(" order by overDaysFlag desc,acNotice desc, cdate desc, cusId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VBusinreceivereport> getBusinReceiveList(VBusinreceivereport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBusinReceiveHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getBusinReceivePages(VBusinreceivereport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBusinReceiveHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public VBankmoneyreport getRecieveSum(VBusinreceivereport query, Object[] obj) {
        List list;
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("select 1 as bankId, '' as bankName, coalesce(sum(receiveMoney),0) as inMoney,").append(" coalesce(sum(cmoney),0) as outMoney1,").append(" round(coalesce(sum(coalesce(cmoney,0) - coalesce(receiveMoney1,0) - coalesce(receiveMoney2,0) - coalesce(receiveMoney3,0) - coalesce(receiveMoney4,0) - coalesce(receiveMoney)),0),4) as outMoney2, ").append(" 0 as outMoney,").append(" coalesce(sum(coalesce(money1,0) - coalesce(receiveMoney1,0)), 0) as cmoney,").append(" coalesce(sum(coalesce(money2,0)) - sum(coalesce(receiveMoney2,0)), 0) as money2,").append(" coalesce(sum(coalesce(money4,0)) - sum(coalesce(receiveMoney4,0)), 0) as money4").append(" from v_businreceivereport");
        
        if (obj != null) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim()) && obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and ((cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate1 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate2 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate3 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (cdate4 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" cdate4 <= '").append(obj[1].toString()).append(" 23:59:59'))");
            } else {
                if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                    whereStr.append(" and (cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" cdate4 >= '").append(obj[0].toString()).append(" 00:00:00')");
                }
                if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                    whereStr.append(" and (cdate1 <= '").append(obj[1].toString()).append(" 23:59:59' or").append(" cdate2 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" cdate3 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" cdate4 <= '").append(obj[1].toString()).append(" 23:59:59')");
                }
            }
            if (obj[3] != null) {
                whereStr.append(" and schedule_archive = ").append(obj[3]);
            }
            if(obj.length >= 6) {
            	if(!"1".equals(String.valueOf(obj[5]))) {
            		whereStr.append(" and salerId in (select saler.c_id from t_saler saler where saler.user_id = ").append(UserInfo.getUserId()).append(")");
            	}
            }
        }
        if (query != null) {
            if (query.getCusName() != null && !"".equals(query.getCusName().trim())) {
                whereStr.append(" and cusName like '%").append(query.getCusName().trim()).append("%'");
            }
            if (query.getCusId() != null && query.getCusId() > 0) {
                whereStr.append(" and cusId = ").append(query.getCusId());
            }
            if (query.getCusId2() != null && query.getCusId2() > 0) {
                whereStr.append(" and cusId2 = ").append(query.getCusId2());
            }
            if (query.getBillNo() != null && !"".equals(query.getBillNo().trim())) {
                whereStr.append(" and billNo like '%").append(query.getBillNo().trim()).append("%'");
            }
            if (query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
                whereStr.append(" and empName like '%").append(query.getEmpName().trim()).append("%'");
            }
            if (query.getNodate5() != null && !"".equals(query.getNodate5().trim())) {
                whereStr.append(" and (").append(" reim like '%").append(query.getNodate5().trim()).append("%' or ").append(" inv like '%").append(query.getNodate5().trim()).append("%' or ").append(" nodate5 like '%").append(query.getNodate5().trim()).append("%' or ").append(" nodate7 like '%").append(query.getNodate5().trim()).append("%'").append(")");
            }
            if (query.getPayComplete() != null && query.getPayComplete() > -1) {
                whereStr.append(" and payComplete = ").append(query.getPayComplete());
            }
            if (query.getSalerId() != null && query.getSalerId() > -1) {
                whereStr.append(" and salerId = ").append(query.getSalerId());
            }
            if (query.getTypeId() != null && query.getTypeId() > -1) {
            	whereStr.append(" and typeId = ").append(query.getTypeId());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if ((list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).addEntity(VBankmoneyreport.class).list()) != null && list.size() > 0) {
            return (VBankmoneyreport)list.get(0);
        }
        return null;
    }

    public String getBusinProfitHql(VBusinprofitreport query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VBusinprofitreport");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                whereStr.append(" and cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
            if (obj[2] != null) {
                whereStr.append(" and rate_archive = ").append(obj[2].toString());
            }
            if(obj.length >= 4) {
            	if(!"1".equals(String.valueOf(obj[3]))) {
            		whereStr.append(" and salerId in (select saler.CId from TSaler saler where saler.user.CId = ").append(UserInfo.getUserId()).append(")");
            	}
            }
        }
        if (query != null) {
            if (query.getCusName() != null && !"".equals(query.getCusName().trim())) {
                whereStr.append(" and cusName like '%").append(query.getCusName()).append("%'");
            }
            if (query.getBillNo() != null && !"".equals(query.getBillNo().trim())) {
                whereStr.append(" and billNo like '%").append(query.getBillNo()).append("%'");
            }
            if (query.getComplete() != null && query.getComplete() >= 0) {
                whereStr.append(" and complete = ").append(query.getComplete());
            }
            if (query.getTypeid() != null && query.getTypeid() > 0) {
                whereStr.append(" and typeid = ").append(query.getTypeid());
            }
            if (query.getSalerId() != null && query.getSalerId() > 0) {
                whereStr.append(" and salerId = ").append(query.getSalerId());
            }
            if (query.getRecieveFlag() != null && query.getRecieveFlag() > -1) {
                whereStr.append(" and recieveFlag = ").append(query.getRecieveFlag());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            hql.append(" group by businId");
        }
        if (flag == 1) {
            return hql.append(" order by cdate desc, cusId asc").toString();
        }
        if(flag == 3) {
        	return "select coalesce(sum(coalesce(profit,0)),0) " + hql.toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VBusinprofitreport> getBusinProfitList(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBusinProfitHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getBusinProfitPages(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBusinProfitHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public VBankmoneyreport getProfitSum(VBusinprofitreport query, Object[] obj) {
        List list;
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("select 1 as bankId, '' as bankName, 0 as inMoney,").append(" coalesce(sum(coalesce(profit,0)),0) as outMoney1,").append(" coalesce(sum(coalesce(profit2,0)),0) as outMoney2, ").append(" 0 as outMoney, 0 as cmoney, 0 as money2, 0 as money4 from v_businProfitReport");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                whereStr.append(" and cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
            if (obj[2] != null) {
                whereStr.append(" and rate_archive = ").append(obj[2].toString());
            }
        }
        if (query != null) {
            if (query.getCusName() != null && !"".equals(query.getCusName().trim())) {
                whereStr.append(" and cusName like '%").append(query.getCusName()).append("%'");
            }
            if (query.getBillNo() != null && !"".equals(query.getBillNo().trim())) {
                whereStr.append(" and billNo like '%").append(query.getBillNo()).append("%'");
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if ((list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).addEntity(VBankmoneyreport.class).list()) != null && list.size() > 0) {
            return (VBankmoneyreport)list.get(0);
        }
        return null;
    }

    public String getEmpProfitReportHQL(VBusinprofitreport query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("select ifnull(empid,0) as empid, ifnull(empName,'') as empName, coalesce(sum(costMoney),0) as costMoney,").append(" coalesce(sum(cashMoney),0) as cashMoney, coalesce(sum(cashMoney2),0) as cashMoney2,").append(" coalesce(sum(cashMoney4),0) as cashMoney4,").append("      coalesce(sum(receiveMoney),0) as receiveMoney, coalesce(sum(profit),0) as profit,").append("      coalesce(sum(profit2),0) as profit2").append(" from v_businprofitreport where rate_archive = 1 ");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                whereStr.append(" and cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
        }
        if (query != null && query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
            whereStr.append(" and empName like '%").append(query.getEmpName().trim()).append("%'");
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(whereStr);
        }
        hql.append(" group by empid, empName");
        if (flag == 1) {
            return hql.append(" order by empid asc").toString();
        }
        return "select count(*) from (" + hql.toString() + ") tt";
    }

    public List<VEmpprofitreport> getEmpProfitReport(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(this.getEmpProfitReportHQL(query, obj, 1)).addEntity(VEmpprofitreport.class).list();
    }

    public PageBean getEmpProfitReportPages(VBusinprofitreport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getEmpProfitReportHQL(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql).list().get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public VBankmoneyreport getEmpProfitSum(VBusinprofitreport query, Object[] obj) {
        List list;
        StringBuffer hql = new StringBuffer();
        hql.append("select 1 as bankId, '' as bankName, 0 as inMoney,").append(" coalesce(sum(coalesce(profit,0)),0) as outMoney1,").append(" coalesce(sum(coalesce(profit2,0)),0) as outMoney2, ").append(" 0 as outMoney, 0 as cmoney, 0 as money2, 0 as money4").append(" from v_businprofitreport where rate_archive = 1 ");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                hql.append(" and cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                hql.append(" and cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
        }
        if (query != null && query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
            hql.append(" and empName like '%").append(query.getEmpName().trim()).append("%'");
        }
        if ((list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).addEntity(VBankmoneyreport.class).list()) != null && list.size() > 0) {
            return (VBankmoneyreport)list.get(0);
        }
        return null;
    }

    public String getBankMoneyHql(VBankmoneyreport query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VBankmoneyreport");
        if (query != null && query.getBankName() != null && !"".equals(query.getBankName().trim())) {
            whereStr.append(" and bankName like '%").append(query.getBankName()).append("%'");
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by bankId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VBankmoneyreport> getBankMoneyList(VBankmoneyreport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBankMoneyHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getBankMoneyPages(VBankmoneyreport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBankMoneyHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public String getCostReportHql(VCostReport query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hql.append("from VCostReport vv");
        if (query != null && query.getId() != null) {
            if (query.getId().getBillNo() != null && !"".equals(query.getId().getBillNo().trim())) {
                where.append(" and vv.id.billNo like '%").append(query.getId().getBillNo().trim()).append("%'");
            }
            if (query.getId().getItemId() != null && query.getId().getItemId() > 0) {
                where.append(" and vv.id.itemId = ").append(query.getId().getItemId());
            }
            if (query.getId().getCstate() != null && query.getId().getCstate() > 0) {
                where.append(" and vv.id.cstate = ").append(query.getId().getCstate());
            }
            if (query.getId().getHiddenFlag() != null && query.getId().getHiddenFlag() > -1) {
                where.append(" and vv.id.hiddenFlag = ").append(query.getId().getHiddenFlag());
            }
            if (query.getId().getCusName() != null && !"".equals(query.getId().getCusName().trim())) {
                where.append(" and vv.id.cusName like '%").append(query.getId().getCusName().trim()).append("%'");
            }
            if (query.getId().getTakeNo() != null && !"".equals(query.getId().getTakeNo().trim())) {
                where.append(" and vv.id.takeNo like '%").append(query.getId().getTakeNo().trim()).append("%'");
            }
            if (query.getId().getGroupid() != null && query.getId().getGroupid() > 0L) {
                where.append(" and vv.id.groupid = ").append(query.getId().getGroupid());
            }
            if (query.getId().getRefno() != null && !"".equals(query.getId().getRefno().trim())) {
                where.append(" and vv.id.refno like '%").append(query.getId().getRefno().trim()).append("%'");
            }
            if (query.getId().getAppbyName() != null && !"".equals(query.getId().getAppbyName().trim())) {
                where.append(" and vv.id.appbyName like '%").append(query.getId().getAppbyName().trim()).append("%'");
            }
            if (query.getId().getBaoxiao() != null && query.getId().getBaoxiao() > -1) {
                where.append(" and vv.id.baoxiao = ").append(query.getId().getBaoxiao());
            }
        }
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and vv.id.cdate >= '").append(obj[0].toString()).append("'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and vv.id.cdate <= '").append(obj[1].toString()).append("'");
            }
            if (obj.length >= 4 && obj[3] != null && !"".equals(obj[3].toString().trim())) {
                where.append(" and exists (select 1 from TContainer cc where cc.TBusin.CId = vv.id.bid").append(" and (cc.CContainerNum like '%").append(obj[3].toString().trim()).append("%'").append(" or cc.CContainerType like '%").append(obj[3].toString().trim()).append("%'").append(")");
            }
        }
        where.append(" and not exists (select 1 from TCostUser uu where uu.CCostid = vv.id.itemId and ").append(" uu.CUserid = ").append(CommonUtil.getUserId()).append(")");
        if (where != null && !"".equals(where.toString().trim())) {
            hql.append(" where ").append(where.toString().substring(4));
        }
        if (flag == 1) {
            if (query.getId().getGroupid() != null) {
                return hql.append(" order by vv.id.newCostFlag desc, vv.id.editCostFlag desc, vv.id.cdate desc, vv.id.billNo desc").toString();
            }
            return hql.append(" order by vv.id.newCostFlag desc, vv.id.cdate desc, vv.id.billNo desc").toString();
        }
        if (flag == 2) {
            return "select count(*) " + hql.toString();
        }
        return "select coalesce(sum(coalesce(vv.id.cmoney,0)),0) " + hql.toString();
    }

    public List<VCostReport> getCostReport(VCostReport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostReportHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getCostReportPages(VCostReport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostReportHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public double getCostReportSum(VCostReport query, Object[] obj) {
        String hql = this.getCostReportHql(query, obj, 3);
        return Double.parseDouble(this.getHibernateTemplate().find(hql).get(0).toString());
    }

    public String getCostReportHql2(VCostReport2 query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hql.append("from VCostReport2 vv");
        if (query != null && query.getId() != null) {
            if (query.getId().getBillNo() != null && !"".equals(query.getId().getBillNo().trim())) {
                where.append(" and vv.id.billNo like '%").append(query.getId().getBillNo().trim()).append("%'");
            }
            if (query.getId().getItemId() != null && query.getId().getItemId() > 0L) {
                where.append(" and (vv.id.itemId = ").append(query.getId().getItemId()).append(" or exists (select 1 from TCostGroup gg, TCostGroupItem ii").append(" where gg.CId = ii.CPid and gg.CId = vv.id.groupid and ii.TCostItem.CId = ").append(query.getId().getItemId()).append("))");
            }
            if (query.getId().getCstate() != null && query.getId().getCstate() > 0) {
                where.append(" and vv.id.cstate = ").append(query.getId().getCstate());
            }
            if (query.getId().getHiddenFlag() != null && query.getId().getHiddenFlag() > -1) {
                where.append(" and vv.id.hiddenFlag = ").append(query.getId().getHiddenFlag());
            }
            if (query.getId().getCusName() != null && !"".equals(query.getId().getCusName().trim())) {
                where.append(" and vv.id.cusName like '%").append(query.getId().getCusName().trim()).append("%'");
            }
            if (query.getId().getTakeNo() != null && !"".equals(query.getId().getTakeNo().trim())) {
                where.append(" and vv.id.takeNo like '%").append(query.getId().getTakeNo().trim()).append("%'");
            }
            if (query.getId().getGroupid() != null && query.getId().getGroupid() > 0L) {
                where.append(" and vv.id.groupid = ").append(query.getId().getGroupid());
            }
            if (query.getId().getRefno() != null && !"".equals(query.getId().getRefno().trim())) {
                where.append(" and vv.id.refno like '%").append(query.getId().getRefno().trim()).append("%'");
            }
            if (query.getId().getAppbyName() != null && !"".equals(query.getId().getAppbyName().trim())) {
                where.append(" and vv.id.appbyName like '%").append(query.getId().getAppbyName().trim()).append("%'");
            }
            if (query.getId().getBaoxiao() != null && query.getId().getBaoxiao() > -1) {
                where.append(" and vv.id.baoxiao = ").append(query.getId().getBaoxiao());
            }
        }
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and vv.id.cdate >= '").append(obj[0].toString()).append("'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and vv.id.cdate <= '").append(obj[1].toString()).append("'");
            }
            if (obj.length >= 4 && obj[3] != null && !"".equals(obj[3].toString().trim())) {
                where.append(" and exists (select 1 from TContainer cc where cc.TBusin.CId = vv.id.bid").append(" and (cc.CContainerNum like '%").append(obj[3].toString().trim()).append("%'").append(" or cc.CContainerType like '%").append(obj[3].toString().trim()).append("%'").append(")");
            }
        }
        where.append(" and not exists (select 1 from TCostUser uu where uu.CCostid = vv.id.itemId and ").append(" uu.CUserid = ").append(CommonUtil.getUserId()).append(")");
        if (where != null && !"".equals(where.toString().trim())) {
            hql.append(" where ").append(where.toString().substring(4));
        }
        if (flag == 1) {
            if (query.getId().getGroupid() != null) {
                return hql.append(" order by vv.id.newCostFlag desc, vv.id.editCostFlag desc, vv.id.cdate desc, vv.id.billNo desc").toString();
            }
            return hql.append(" order by vv.id.newCostFlag desc, vv.id.cdate desc, vv.id.billNo desc").toString();
        }
        if (flag == 2) {
            return "select count(*) " + hql.toString();
        }
        return "select coalesce(sum(coalesce(vv.id.cmoney,0)),0) " + hql.toString();
    }

    public List<VCostReport2> getCostReport2(VCostReport2 query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostReportHql2(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getCostReportPages2(VCostReport2 query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostReportHql2(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public double getCostReportSum2(VCostReport2 query, Object[] obj) {
        String hql = this.getCostReportHql2(query, obj, 3);
        return Double.parseDouble(this.getHibernateTemplate().find(hql).get(0).toString());
    }

    public List<VBusinprofitcusreport> getBusinProfitCusList(VBusinprofitcusreport query, Object[] obj, int pageNow, int pageSize) {
        StringBuffer sql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        sql.append("select tt.cusId as cusId,tt.cusName as cusName, round(sum(tt.cmoney),2) as cmoney,").append(" round(sum(tt.cmoney - tt.receiveMoney1 - tt.receiveMoney2 - tt.receiveMoney3 - tt.receiveMoney4 - tt.receiveMoney),2) as cmoney2,").append(" ifnull(cc.c_remarks, '') as remarks, ifnull(cc.c_remarks2, '') as remarks2, ifnull(cc.c_flag,0) as flag, ").append(" DATE_FORMAT(cc.c_payable_date,'%d-%m-%Y') as payableDate,").append(" case when cc.c_payable_date is null then 0 else case when TO_DAYS(NOW()) - TO_DAYS(cc.c_payable_date) > 0 then 1 else 0 end end as flag2,").append(" ifnull(ss.c_id,0) as saleId, ifnull(ss.c_name,'') as saleName").append(" from v_businreceivereport tt left join t_profit_cus cc on tt.cusId = cc.c_cusid").append("                              left join t_saler ss on ss.c_id = cc.c_saleId");
        if (query != null && query.getCusId() != null && query.getCusId() > 0) {
            where.append(" and tt.cusId = ").append(query.getCusId());
        }
        if(query != null) {
        	if(query.getSaleId() != null && query.getSaleId() > 0) {
        		where.append(" and tt.salerId = ").append(query.getSaleId());
        	}
        }
        if (obj[0] != null && !"".equals(obj[0].toString().trim()) && obj[1] != null && !"".equals(obj[1].toString().trim())) {
            where.append(" and ((tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59'))");
        } else {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and (tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00')");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and (tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59' or").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59')");
            }
        }
        if (where != null && !"".equals(where.toString().trim())) {
            sql.append(" where ").append(where.toString().substring(4));
        }
        sql.append(" group by tt.cusId, tt.cusName").append(" having round(sum(tt.cmoney - tt.receiveMoney1 - tt.receiveMoney2 - tt.receiveMoney3 - tt.receiveMoney4 - tt.receiveMoney),2) <> 0").append(" order by ifnull(cc.c_flag,0) desc, trim(tt.cusName) asc, ifnull(cc.c_flag,0) + (case when cc.c_payable_date is null then 0 else case when TO_DAYS(NOW()) - TO_DAYS(cc.c_payable_date) > 0 then 1 else 0 end end) desc, round(sum(tt.cmoney - tt.receiveMoney1 - tt.receiveMoney2 - tt.receiveMoney3 - tt.receiveMoney4 - tt.receiveMoney),2) desc, tt.cusId asc");
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(VBusinprofitcusreport.class).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getBusinProfitCusPages(VBusinprofitcusreport query, Object[] obj, int pageNow, int pageSize) {
        StringBuffer sql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        sql.append("select tt.cusId as cusId,tt.cusName as cusName, round(sum(tt.cmoney),2) as cmoney,").append(" round(sum(tt.cmoney - tt.receiveMoney1 - tt.receiveMoney2 - tt.receiveMoney3 - tt.receiveMoney4 - tt.receiveMoney),2) as cmoney2,").append(" ifnull(cc.c_remarks, '') as remarks").append(" from v_businreceivereport tt left join t_profit_cus cc on tt.cusId = cc.c_cusid");
        if (query != null && query.getCusId() != null && query.getCusId() > 0) {
            where.append(" and tt.cusId = ").append(query.getCusId());
        }
        if(query != null) {
        	if(query.getSaleId() != null && query.getSaleId() > 0) {
        		where.append(" and tt.salerId = ").append(query.getSaleId());
        	}
        }
        if (obj[0] != null && !"".equals(obj[0].toString().trim()) && obj[1] != null && !"".equals(obj[1].toString().trim())) {
            where.append(" and ((tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59'))");
        } else {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and (tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00')");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and (tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59' or").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59')");
            }
        }
        if (where != null && !"".equals(where.toString().trim())) {
            sql.append(" where ").append(where.toString().substring(4));
        }
        sql.append(" group by tt.cusId,tt.cusName");
        String str = "select count(*) from (" + sql.toString() + ") tt";
        int rowCount = Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(str).list().get(0).toString());
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public double getBusinProfitCusSum(VBusinprofitcusreport query, Object[] obj) {
        StringBuffer sql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        sql.append("select ifnull(sum(ttt.cmoney),0) from (select round(ifnull(sum(tt.cmoney),0),2) as cmoney").append(" from v_businreceivereport tt left join t_profit_cus cc on tt.cusId = cc.c_cusid");
        if (query != null && query.getCusId() != null && query.getCusId() > 0) {
            where.append(" and tt.cusId = ").append(query.getCusId());
        }
        if(query != null) {
        	if(query.getSaleId() != null && query.getSaleId() > 0) {
        		where.append(" and tt.salerId = ").append(query.getSaleId());
        	}
        }
        if (obj[0] != null && !"".equals(obj[0].toString().trim()) && obj[1] != null && !"".equals(obj[1].toString().trim())) {
            where.append(" and ((tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59'))");
        } else {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and (tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00')");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and (tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59' or").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59')");
            }
        }
        if (where != null && !"".equals(where.toString().trim())) {
            sql.append(" where ").append(where.toString().substring(4));
        }
        sql.append(" group by tt.cusId, tt.cusName").append(" having round(sum(tt.cmoney - tt.receiveMoney1 - tt.receiveMoney2 - tt.receiveMoney3 - tt.receiveMoney4 - tt.receiveMoney),2) <> 0) ttt");
        return Double.parseDouble(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
    }

    public double getBusinProfitCusSum2(VBusinprofitcusreport query, Object[] obj) {
        StringBuffer sql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        sql.append("select round(ifnull(sum(tt.cmoney - tt.receiveMoney1 - tt.receiveMoney2 - tt.receiveMoney3 - tt.receiveMoney4 - tt.receiveMoney),0),2)").append(" from v_businreceivereport tt left join t_profit_cus cc on tt.cusId = cc.c_cusid");
        if (query != null && query.getCusId() != null && query.getCusId() > 0) {
            where.append(" and tt.cusId = ").append(query.getCusId());
        }
        if(query != null) {
        	if(query.getSaleId() != null && query.getSaleId() > 0) {
        		where.append(" and tt.salerId = ").append(query.getSaleId());
        	}
        }
        if (obj[0] != null && !"".equals(obj[0].toString().trim()) && obj[1] != null && !"".equals(obj[1].toString().trim())) {
            where.append(" and ((tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59') or ").append(" (tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00' and ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59'))");
        } else {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and (tt.cdate1 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate2 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate3 >= '").append(obj[0].toString()).append(" 00:00:00' or ").append(" tt.cdate4 >= '").append(obj[0].toString()).append(" 00:00:00')");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and (tt.cdate1 <= '").append(obj[1].toString()).append(" 23:59:59' or").append(" tt.cdate2 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate3 <= '").append(obj[1].toString()).append(" 23:59:59' or ").append(" tt.cdate4 <= '").append(obj[1].toString()).append(" 23:59:59')");
            }
        }
        if (where != null && !"".equals(where.toString().trim())) {
            sql.append(" where ").append(where.toString().substring(4));
        }
        return Double.parseDouble(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
    }

    public TProfitCus getProfitCusByCusid(int id) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TProfitCus where TCustomer.CId = ").append(id);
        List list = this.getHibernateTemplate().find(hql.toString());
        if (list != null && list.size() > 0) {
            return (TProfitCus)list.get(0);
        }
        return null;
    }

    public void updateProfitCus(TProfitCus entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void updatePayComplete(int businid, short flag, double cmoney) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin set c_pay_complete = ").append(flag);
        if (flag == 1 && cmoney > 0.0) {
            sql.append(", c_recieve_money = ").append(cmoney);
        }
        sql.append(" where c_id = ").append(businid);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public String getJiezhuanList(TJiezhuan query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        String fetch = "";
        if (flag == 1) {
            fetch = "fetch";
        }
        hql.append("from TJiezhuan jz inner join ").append(fetch).append(" jz.TBusin left join ").append(fetch).append(" jz.TBusin.TCustomerByCCusid");
        if (query != null) {
            if (query.getTBusin().getCBillNo() != null && !"".equals(query.getTBusin().getCBillNo().trim())) {
                where.append(" and jz.TBusin.CBillNo like '%").append(query.getTBusin().getCBillNo().trim()).append("%'");
            }
            if (query.getTBusin().getTCustomerByCCusid() != null && query.getTBusin().getTCustomerByCCusid().getCId() > 0) {
                where.append(" and jz.TBusin.TCustomerByCCusid.CId = ").append(query.getTBusin().getTCustomerByCCusid().getCId());
            }
        }
        where.append(" and jz.CMoney <> jz.CMoney2 and jz.CNotice = 1");
        if (where != null && !"".equals(where.toString().trim())) {
            hql.append(" where ").append(where.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by jz.TBusin.CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TJiezhuan> getJiezhuanList(TJiezhuan query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getJiezhuanList(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getJiezhuanPages(TJiezhuan query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getJiezhuanList(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public TJiezhuan getJiezhuan(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TJiezhuan jz where jz.TBusin.CId = ").append(businId);
        List list = this.getHibernateTemplate().find(hql.toString());
        if (list != null && list.size() > 0) {
            return (TJiezhuan)list.get(0);
        }
        return null;
    }

    public void saveJiezhuan(TJiezhuan entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

	public Double getBusinProfitSum(VBusinprofitreport query, Object[] obj) {
		String hql = getBusinProfitHql(query, obj, 3);
		return (Double) this.getHibernateTemplate().find(hql).get(0);
	}

	@Override
	public List<CostCashoutReport> getCostCashoutReport(CostCashoutReport costCashoutReport, int pageNow,
			int pageSize) throws ParseException{
		String sql = getCostCashoutReportSql(costCashoutReport,1);
		List<Object[]> list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
		List<CostCashoutReport> listNew = new ArrayList<CostCashoutReport>();
		CostCashoutReport bean = null;
		for (Object[] objects : list) {
			bean = new CostCashoutReport();
			bean.setBillNo((String)objects[0]);
			bean.setName((String)objects[1]);
			bean.setAgent((String)objects[2]);
			bean.setItems((String)objects[3]);
			bean.setCosMoney((Double)objects[4]);
			bean.setPayMoney((Double)objects[5]);
			bean.setRemark((String)objects[6]);
			bean.setDiffMoney((Double)objects[7]);
			listNew.add(bean);
		}
		sql = getCostCashoutReportSql(costCashoutReport,3);
		Object[] objs = (Object[]) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list().get(0);
		bean = new CostCashoutReport();
		bean.setCosMoneyTotal(new BigDecimal(String.valueOf(objs[0])).toPlainString());
		bean.setPayMoneyTotal(new BigDecimal(String.valueOf(objs[1])).toPlainString());
		bean.setDiffMoneyTotal(new BigDecimal(String.valueOf(objs[2])).toPlainString());
		listNew.add(bean);
		return listNew;
	}

	private String getCostCashoutReportSql(CostCashoutReport costCashoutReport, int flag) throws ParseException {
		StringBuilder sql = new StringBuilder();
		if(flag == 2) {
			sql.append("select count(1) from (");
		}
		if(flag == 3) {
			sql.append("select IFNULL(SUM(cosMoney),0),IFNULL(SUM(payMoney),0),IFNULL(SUM(diffMoney),0) from (");
		}
		sql.append("select a.billNo,a.NAME,a.agent,a.items,IFNULL(a.cosMoney,0) as cosMoney,IFNULL(r.payMoney,0) as payMoney,r.remark,(IFNULL(a.cosMoney,0) - IFNULL(r.payMoney,0)) as diffMoney from (");
		sql.append("select busin.c_billNo billNo,cus.c_addr name,cost.agent,group_concat(item.c_name) items,SUM(IFNULL(cost.c_money,0)) cosMoney");
		sql.append(" from t_busin busin");
		sql.append(" INNER JOIN t_cost cost on busin.c_id = cost.c_businid");
		sql.append(" left join t_cost_item item on cost.c_costitemid = item.c_id");
		sql.append(" left join t_customer cus on busin.c_cusid = cus.c_id");
		sql.append(" where 1=1");
		if(costCashoutReport != null) {
			if(StringUtils.isNotBlank(costCashoutReport.getStartDate())) {
				sql.append(" and cost.paydate >= '").append(fmt1.format(fmt2.parse(costCashoutReport.getStartDate()))).append("'");
			}
			if(StringUtils.isNotBlank(costCashoutReport.getEndDate())) {
				sql.append(" and cost.paydate <= '").append(fmt1.format(fmt2.parse(costCashoutReport.getEndDate()))).append("'");
			}
			if(StringUtils.isNotBlank(costCashoutReport.getBillNo())) {
				sql.append(" and busin.c_billNo like '%").append(costCashoutReport.getBillNo()).append("%'");
			}
			if(StringUtils.isNotBlank(costCashoutReport.getAgent())) {
				sql.append(" and cost.agent = '").append(costCashoutReport.getAgent()).append("'");
			}
			if(costCashoutReport.getCusId() != null) {
				sql.append(" and busin.c_cusid = ").append(costCashoutReport.getCusId());
			}
		}
		sql.append(" GROUP BY busin.c_id,cost.agent");
		sql.append(") a");
		sql.append(" left join (");
		sql.append("select fin.c_pay,detail.c_remarks,SUM(IFNULL(detail.c_money,0)) payMoney");
		sql.append(",GROUP_CONCAT(CONCAT_WS(':',DATE_FORMAT(fin.c_date,'%d-%m-%Y'),fin.c_financeNo,detail.c_money) separator ';') remark");
		sql.append(" from t_finance fin ");
		sql.append(" inner join t_finance_detail detail on fin.c_id = detail.c_pid");
		sql.append(" WHERE fin.c_type = 2 and exists (select 1 from t_agent a where a.name = fin.c_pay) and exists (select 1 from t_busin a where a.c_billNo = detail.c_remarks)");
		sql.append(" GROUP BY fin.c_pay,detail.c_remarks) r");
		sql.append(" ON a.billNo = r.c_remarks AND a.agent = r.c_pay ");
		
		if(StringUtils.isNotBlank(costCashoutReport.getStatus())) {
			if("unpay".equals(costCashoutReport.getStatus())) {
				sql.append(" where (IFNULL(a.cosMoney,0) - IFNULL(r.payMoney,0)) > 0");
			}
			if("payed".equals(costCashoutReport.getStatus())) {
				sql.append(" where (IFNULL(a.cosMoney,0) - IFNULL(r.payMoney,0)) <= 0");
			}
			
		}
		if(flag == 2 || flag == 3) {
			sql.append(") w");
		}
		if(flag == 1) {
			sql.append(" order by substring(billNo,LENGTH(billNo) - 12,13) desc");
		}
		return sql.toString();
	}

	@Override
	public PageBean getCostCashoutReportPages(CostCashoutReport costCashoutReport, int pageNow, int pageSize) throws ParseException{
		String sql = this.getCostCashoutReportSql(costCashoutReport,2);
		Object object = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list().get(0);
        int rowCount = ((BigInteger)object).intValue();
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
	}

	@Override
	public Map<String, CostCashoutReport> getAllFinanceMap() {
		Map<String, CostCashoutReport> map = new HashMap<String, CostCashoutReport>();
		StringBuilder sql = new StringBuilder();
		sql.append("select fin.c_pay,detail.c_remarks,SUM(IFNULL(detail.c_money,0)) payMoney,");
		sql.append("GROUP_CONCAT(CONCAT_WS(':',DATE_FORMAT(fin.c_date,'%d-%m-%Y'),fin.c_financeNo,IFNULL(detail.c_money,0)) separator ';') remark");
		sql.append(" from t_finance fin ");
		sql.append(" inner join t_finance_detail detail on fin.c_id = detail.c_pid");
		sql.append(" WHERE fin.c_pay in (select name from t_agent) and detail.c_remarks in (select c_billNo from t_busin)");
		sql.append(" GROUP BY fin.c_pay,detail.c_remarks");
		List<Object[]> list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).list();
		for (Object[] objects : list) {
			CostCashoutReport bean = new CostCashoutReport();
			bean.setPayMoney((Double)objects[2]);
			bean.setRemark((String)objects[3]);
			map.put(objects[0] + "_" + objects[1], bean);
		}
		return map;
	}

	@Override
	public List<BankAccountReport> getBankAccountReport(BankAccountReport bankAccount, int pageNow, int pageSize, Map<Integer, BankAccountReport> bankMap) throws Exception {
		
		String sql = getBankAccountReportSql(bankAccount,1);
		List<Object[]> list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
		List<BankAccountReport> listNew = new ArrayList<BankAccountReport>();
		BankAccountReport bean = null;
		for (Object[] objects : list) {
			bean = bankMap.get((Integer)objects[0]);
			if(bean != null) {
				//bean.setBankName((String)objects[1]);
				bean.setInMoney(((Double)objects[2]).toString());
				bean.setOutMoney(((Double)objects[5]).toString());
				bean.setEndMoney(((Double)objects[6]).toString());
			}
		}
		if(StringUtils.isNotBlank(bankAccount.getStartDate())) {
			BankAccountReport query = new BankAccountReport();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fmt2.parse(bankAccount.getStartDate()));
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			query.setEndDate(fmt2.format(calendar.getTime()));
			sql = getBankAccountReportSql(query,1);
			List<Object[]> list2 = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
			for (Object[] objects : list2) {
				Integer bankId = (Integer)objects[0];
				BankAccountReport bankAccountReport = bankMap.get(bankId);
				if(bankAccountReport != null) {
					bankAccountReport.setBeginMoney(((Double)objects[6]).toString());
				}
			}
		}
		Set<Entry<Integer,BankAccountReport>> entrySet = bankMap.entrySet();
		for (Entry<Integer, BankAccountReport> entry : entrySet) {
			listNew.add(entry.getValue());
		}
		return listNew;
	}

	private String getBankAccountReportSql(BankAccountReport bankAccount, int flag) throws Exception {
		StringBuilder sql = new StringBuilder();
		if(flag == 2) {
			sql.append("select count(1) from (");
		}
		sql.append("select `v1`.`bankId` AS `bankId`,`v1`.`bankName` AS `bankName`,ifnull(`v1`.`inMoney`,0) AS `inMoney`");
		sql.append(",ifnull(`v2`.`outMoney1`,0) AS `outMoney1`,ifnull(`v2`.`outMoney2`,0) AS `outMoney2`");
		sql.append(",ifnull(`v2`.`outMoney`,0) AS `outMoney`,(ifnull(`v1`.`inMoney`,0) - ifnull(`v2`.`outMoney`,0)) AS `cmoney`");
		sql.append(",0.00 AS `money2`,0.00 AS `money4`");
		sql.append(" from ");
		getv1(bankAccount, sql);
		sql.append(" left join");
		getv2(bankAccount, sql);
		sql.append(" on((`v1`.`bankId` = `v2`.`bankId`))");
		sql.append(" union select `v2`.`bankId` AS `bankId`,`v2`.`bankName` AS `bankName`,ifnull(`v1`.`inMoney`,0) AS `inMoney`");
		sql.append(",ifnull(`v2`.`outMoney1`,0) AS `outMoney1`,ifnull(`v2`.`outMoney2`,0) AS `outMoney2`");
		sql.append(",ifnull(`v2`.`outMoney`,0) AS `outMoney`,(ifnull(`v1`.`inMoney`,0) - ifnull(`v2`.`outMoney`,0)) AS `cmoney`");
		sql.append(",0.00 AS `money2`,0.00 AS `money4` from ");
		getv2(bankAccount, sql);
		sql.append(" left join");
		getv1(bankAccount, sql);
		sql.append(" on((`v1`.`bankId` = `v2`.`bankId`))");
		
		if(flag == 2) {
			sql.append(") w");
		}
		return sql.toString();
	}

	private void getv2(BankAccountReport bankAccount, StringBuilder sql) throws ParseException {
		sql.append("(select `bank`.`c_id` AS `bankId`,`bank`.`c_name` AS `bankName`,");
		sql.append("ifnull(sum((case `detail`.`c_item` when '永久支出' then 0 else `detail`.`c_money` end)),0) AS `outMoney1`");
		sql.append(",ifnull(sum((case `detail`.`c_item` when '永久支出' then `detail`.`c_money` else 0 end)),0) AS `outMoney2`");
		sql.append(",ifnull(sum(`detail`.`c_money`),0) AS `outMoney`");
		sql.append(" from ((`t_finance` `finance` join `t_finance_detail` `detail`) join `t_bank` `bank`)");
		sql.append(" where ((`finance`.`c_id` = `detail`.`c_pid`) and (`finance`.`c_bankid` = `bank`.`c_id`) and (`finance`.`c_type` = 2))");
		if(bankAccount != null) {
			if(StringUtils.isNotBlank(bankAccount.getStartDate())) {
				sql.append(" and finance.c_date >= '").append(fmt1.format(fmt2.parse(bankAccount.getStartDate()))).append(" 00:00:00'");
			}
			if(StringUtils.isNotBlank(bankAccount.getEndDate())) {
				sql.append(" and finance.c_date <= '").append(fmt1.format(fmt2.parse(bankAccount.getEndDate()))).append(" 00:00:00'");
			}
		}
		sql.append(" group by `bank`.`c_id`,`bank`.`c_name`) v2");
	}

	private void getv1(BankAccountReport bankAccount, StringBuilder sql) throws ParseException {
		sql.append("(select `bank`.`c_id` AS `bankId`,`bank`.`c_name` AS `bankName`,ifnull(sum(`detail`.`c_money`),0) AS `inMoney`");
		sql.append(" from ((`t_finance` `finance` join `t_finance_detail` `detail`) join `t_bank` `bank`)");
		sql.append(" where ((`finance`.`c_id` = `detail`.`c_pid`) and (`finance`.`c_bankid` = `bank`.`c_id`) and (`finance`.`c_type` = 1))");
		if(bankAccount != null) {
			if(StringUtils.isNotBlank(bankAccount.getStartDate())) {
				sql.append(" and finance.c_date >= '").append(fmt1.format(fmt2.parse(bankAccount.getStartDate()))).append(" 00:00:00'");
			}
			if(StringUtils.isNotBlank(bankAccount.getEndDate())) {
				sql.append(" and finance.c_date <= '").append(fmt1.format(fmt2.parse(bankAccount.getEndDate()))).append(" 00:00:00'");
			}
		}
		sql.append(" group by `bank`.`c_id`,`bank`.`c_name`) v1");
	}

	@Override
	public PageBean getBankAccountReportPages(BankAccountReport bankAccount, int pageNow, int pageSize) throws Exception {
		String sql = this.getBankAccountReportSql(bankAccount,2);
		Object object = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list().get(0);
        int rowCount = ((BigInteger)object).intValue();
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
	}
}


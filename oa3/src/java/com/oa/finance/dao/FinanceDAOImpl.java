/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinance
 *  com.common.entity.TFinanceDetail
 *  com.common.entity.VFinance
 *  com.common.entity.VOutReport
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.finance.dao.FinanceDAO
 *  com.oa.finance.dao.FinanceDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.finance.dao;

import com.common.entity.TFinance;
import com.common.entity.TFinanceDetail;
import com.common.entity.VFinance;
import com.common.entity.VOutReport;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.finance.dao.FinanceDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FinanceDAOImpl
extends HibernateDaoSupport
implements FinanceDAO {
    public String getHql(VFinance query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VFinance vv");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                whereStr.append(" and vv.cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and vv.cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
            if (obj[2] != null && Integer.parseInt(obj[2].toString()) > 0) {
                if (Integer.parseInt(obj[2].toString()) == 1) {
                    whereStr.append(" and vv.archivingFlag = 1");
                } else {
                    whereStr.append(" and vv.archivingFlag in (2,3)");
                }
            }
            if (obj[3] != null && !"".equals(obj[3].toString().trim())) {
                whereStr.append(" and exists (select 1 from TFinanceDetail dd where dd.TFinance.CId = vv.cid and dd.CRemarks like '%").append(obj[3].toString().trim()).append("%')");
            }
        }
        if (query != null) {
            if (query.getCpay() != null && !"".equals(query.getCpay().trim())) {
                whereStr.append(" and vv.cpay like '%").append(query.getCpay().trim()).append("%'");
            }
            if (query.getBankName() != null && !"".equals(query.getBankName().trim())) {
                whereStr.append(" and vv.bankName like '%").append(query.getBankName().trim()).append("%'");
            }
            if (query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
                whereStr.append(" and vv.empName like '%").append(query.getEmpName().trim()).append("%'");
            }
            if (query.getCtype() != null && query.getCtype() > 0) {
                whereStr.append(" and vv.ctype = ").append(query.getCtype());
            }
            if (query.getFtype() != null && query.getFtype() != -1) {
                whereStr.append(" and vv.ftype = ").append(query.getFtype());
            }
            if (query.getArchivingFlag() != null && query.getArchivingFlag() > 0) {
                whereStr.append(" and vv.archivingFlag = ").append(query.getArchivingFlag());
            }
            if (query.getCmoney() != null) {
                whereStr.append(" and vv.cmoney = ").append(query.getCmoney());
            }
        }
        whereStr.append(" and not exists (select 1 from TFinancetypeUser uu where uu.CUserid = " + CommonUtil.getUserId() + " and uu.CTypeid = vv.ftype)");
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by vv.cdate desc, vv.cid desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VFinance> getList(VFinance query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(VFinance query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public Double getSumMoney(VFinance query, Object[] obj) {
        Object oo;
        StringBuffer sql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        sql.append("select sum(vv.cmoney) from v_finance vv");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                whereStr.append(" and vv.cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and vv.cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
            if (obj[2] != null && Integer.parseInt(obj[2].toString()) > 0) {
                if (Integer.parseInt(obj[2].toString()) == 1) {
                    whereStr.append(" and vv.archivingFlag = 1");
                } else {
                    whereStr.append(" and vv.archivingFlag in (2,3)");
                }
            }
            if (obj[3] != null && !"".equals(obj[3].toString().trim())) {
                whereStr.append(" and exists (select 1 from t_finance_detail dd where dd.c_pid = vv.cid and dd.c_remarks like '%").append(obj[3].toString().trim()).append("%')");
            }
        }
        if (query != null) {
            if (query.getCpay() != null && !"".equals(query.getCpay().trim())) {
                whereStr.append(" and vv.cpay like '%").append(query.getCpay().trim()).append("%'");
            }
            if (query.getBankName() != null && !"".equals(query.getBankName().trim())) {
                whereStr.append(" and vv.bankName like '%").append(query.getBankName().trim()).append("%'");
            }
            if (query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
                whereStr.append(" and vv.empName like '%").append(query.getEmpName().trim()).append("%'");
            }
            if (query.getCtype() != null && query.getCtype() > 0) {
                whereStr.append(" and vv.ctype = ").append(query.getCtype());
            }
            if (query.getFtype() != null && query.getFtype() != -1) {
                whereStr.append(" and vv.ftype = ").append(query.getFtype());
            }
            if (query.getArchivingFlag() != null && query.getArchivingFlag() > 0) {
                whereStr.append(" and vv.archivingFlag = ").append(query.getArchivingFlag());
            }
            if (query.getCmoney() != null) {
                whereStr.append(" and vv.cmoney = ").append(query.getCmoney());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            sql.append(" where ").append(whereStr.toString().substring(4));
        }
        if ((oo = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult()) != null) {
            return Double.parseDouble(oo.toString());
        }
        return 0.0;
    }

    public void saveEntity(TFinance entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TFinance getEntityById(Serializable id) {
        return (TFinance)this.getHibernateTemplate().get(TFinance.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_finance where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public String getMaxBillNo(String date) {
        String maxBillNo = null;
        String sql = "select max(right(c_financeNo, 5)) from t_finance where left(c_financeNo,9) = '" + date + "'";
        Object max = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        maxBillNo = max == null ? "00001" : String.valueOf(100001 + Integer.parseInt(max.toString())).substring(1);
        return String.valueOf(date) + "-" + maxBillNo;
    }

    public List<TFinanceDetail> getDetailList(int pid) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TFinanceDetail where TFinance.CId = ").append(pid);
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveDetailEntity(TFinanceDetail entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void deleteDetailEntity(int id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_finance_detail where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteAllDetailEntity(int pid) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_finance_detail where c_pid = ").append(pid);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int getNoticeCount() {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from t_finance f where exists").append(" (select 1 from t_finance_detail d where f.c_id = d.c_pid and d.c_item = 'ÁÙÊ±Ö§³ö' and d.c_notice = 1)");
        return Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
    }

    public void updateRemarks(int id, String remarks) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_finance set c_remarks = ").append(remarks).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateRemarks2(int id, String remarks) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_finance set c_remarks2 = ").append(remarks).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateArchivingState(int id, short state) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_finance set c_archiving_flag = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public List<VOutReport> getOutReportList(VOutReport query, Object[] obj) {
        StringBuffer sql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        sql.append("select tt.c_id as typeid, tt.c_name as typename, ifnull(sum(dd.c_money),0) as cmoney").append(" from t_finance ff inner join t_financetype tt on ff.c_financetype = tt.c_id").append(" \t\t\t\t    inner join t_finance_detail dd on ff.c_id = dd.c_pid");
        if (query != null && query.getTypeid() != null && query.getTypeid() > 0) {
            where.append(" and tt.c_id = ").append(query.getTypeid());
        }
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and ff.c_date >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and ff.c_date <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
        }
        if (where != null && !"".equals(where.toString().trim())) {
            sql.append(" where ").append(where.toString().substring(4));
        }
        sql.append(" group by tt.c_id asc");
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(VOutReport.class).list();
    }
}


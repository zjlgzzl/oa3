/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
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
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.busin.dao.BusinDAO
 *  com.oa.busin.dao.BusinDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.busin.dao;

import com.common.auth.action.UserInfo;
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
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.busin.dao.BusinDAO;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusinDAOImpl
extends HibernateDaoSupport
implements BusinDAO {
    public String getHql(VBusin query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VBusin busin");
        if (query != null) {
            if (query.getBillNo() != null && !"".equals(query.getBillNo().trim())) {
                whereStr.append(" and busin.billNo like '%").append(query.getBillNo().trim()).append("%'");
            }
            if (query.getTypeId() != null && query.getTypeId() > 0) {
                whereStr.append(" and busin.typeId = ").append(query.getTypeId());
            }
            if (query.getCusId() != null && query.getCusId() > 0) {
                whereStr.append(" and busin.cusId = ").append(query.getCusId());
            }
            if (query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
                whereStr.append(" and busin.saleman like '%").append(query.getEmpName().trim()).append("%'");
            }
            if (query.getBusinState() != null && query.getBusinState() > 0) {
                whereStr.append(" and busin.businState = ").append(query.getBusinState());
            }
			/*
			 * if (query.getUserId() != null && query.getUserId() > 0) {
			 * whereStr.append(" and busin.userId = ").append(query.getUserId()); }
			 */
            if (query.getBusinStateName() != null && !"".equals(query.getBusinStateName()) && !"0".equals(query.getBusinStateName())) {
                if ("1".equals(query.getBusinStateName())) {
                    whereStr.append(" and busin.businStateName = '已开票'");
                } else if ("2".equals(query.getBusinStateName())) {
                    whereStr.append(" and busin.businStateName = '未开票'");
                } else {
                    whereStr.append(" and busin.recieveFlag = 1");
                }
            }
            if (query.getCompleteFlag() != null && query.getCompleteFlag() > -1) {
                whereStr.append(" and busin.completeFlag = ").append(query.getCompleteFlag());
            }
        }
        int type = 0;
        boolean lock = false;
        if (obj != null && obj.length > 0) {
            type = Integer.parseInt(obj[2].toString());
            if (type == 1) {
                whereStr.append(" and busin.deptId = ").append(UserInfo.getUppId());
            }
            StringBuffer tmp1 = new StringBuffer();
            StringBuffer tmp2 = new StringBuffer();
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                if (type == 6) {
                    if (query.getBusinStateName() != null && !"".equals(query.getBusinStateName())) {
                        if ("2".equals(query.getBusinStateName())) {
                            whereStr.append(" and busin.businDate >= '").append(obj[0].toString()).append(" 00:00:00'");
                        } else if ("1".equals(query.getBusinStateName()) || "3".equals(query.getBusinStateName())) {
                            whereStr.append(" and busin.nodate >= '").append(obj[0].toString()).append(" 00:00:00'");
                        } else {
                            whereStr.append(" and (((busin.businStateName = '已开票' or busin.recieveFlag = 1) and busin.nodate >= '").append(obj[0].toString()).append(" 00:00:00') or ").append("(busin.businStateName = '未开票' and busin.businDate >= '").append(obj[0].toString()).append(" 00:00:00'))");
                        }
                    }
                } else {
                    whereStr.append(" and busin.businDate >= '").append(obj[0].toString()).append(" 00:00:00'");
                }
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                if (type == 6) {
                    if (query.getBusinStateName() != null && !"".equals(query.getBusinStateName())) {
                        if ("2".equals(query.getBusinStateName())) {
                            whereStr.append(" and busin.businDate <= '").append(obj[1].toString()).append(" 23:59:59'");
                        } else if ("1".equals(query.getBusinStateName()) || "3".equals(query.getBusinStateName())) {
                            whereStr.append(" and busin.nodate <= '").append(obj[1].toString()).append(" 23:59:59'");
                        } else {
                            whereStr.append(" and (((busin.businStateName = '已开票' or busin.recieveFlag = 1) and busin.nodate <= '").append(obj[1].toString()).append(" 23:59:59') or ").append("(busin.businStateName = '未开票' and busin.businDate <= '").append(obj[1].toString()).append(" 23:59:59'))");
                        }
                    }
                } else {
                    whereStr.append(" and busin.businDate <= '").append(obj[1].toString()).append(" 23:59:59'");
                }
            }
            if (obj[3] != null && !"".equals(obj[3].toString().trim())) {
                whereStr.append(" and exists (select 1 from TContainer con where con.TBusin.CId = busin.businId and con.CContainerNum like '%" + obj[3].toString() + "%')");
            }
            if (obj[4] != null && !"".equals(obj[4].toString().trim())) {
                whereStr.append(" and busin.takeNo like '%").append(obj[4].toString()).append("%'");
            }
            if (obj.length == 6 && Integer.parseInt(obj[5].toString()) == 1) {
                whereStr.append(" and busin.lockFlag2 = 1 and busin.lockBy = ").append(CommonUtil.getEmpId());
                lock = true;
            }
            if (obj.length == 8) {
                if (obj[6] != null && !"".equals(obj[6].toString().trim())) {
                    whereStr.append(" and busin.clearDate >= '").append(obj[6].toString()).append(" 00:00:00'");
                }
                if (obj[7] != null && !"".equals(obj[7].toString().trim())) {
                    whereStr.append(" and busin.clearDate <= '").append(obj[7].toString()).append(" 23:59:59'");
                }
            }
        }
        if (!lock) {
            if (type == 1) {
                if (CommonUtil.getFun((int)206) == 0) {
                    whereStr.append(" and busin.businState = 1");
                }
            } else if (CommonUtil.getFun((int)308) == 0) {
                whereStr.append(" and busin.businState = 1");
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            if (type == 6) {
                return hql.append(" order by busin.addGroupFlag desc, newCostFlag desc, (case when COALESCE(busin.nodate, '') = '' then busin.businDate else busin.nodate end) desc, substring(busin.billNo,LENGTH(busin.billNo) - 12,13) desc").toString();
            }
            return hql.append(" order by substring(busin.billNo,LENGTH(busin.billNo) - 12,13) desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VBusin> getList(VBusin query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(VBusin query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TBusin entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void saveMergeEntity(TBusin entity) throws Exception {
        this.getHibernateTemplate().merge((Object)entity);
    }

    public String getMaxBillNo(String date) {
        String maxBillNo = null;
        String sql = "select max(right(c_billno, 5)) from t_busin where right(left(c_billno, CHAR_LENGTH(c_billno) - 6),7) = '" + date + "'";
        Object max = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        maxBillNo = max == null ? "00001" : String.valueOf(100001 + Integer.parseInt(max.toString())).substring(1);
        return String.valueOf(date) + "-" + maxBillNo;
    }

    public TBusin getEntityById(Serializable id) {
        String hql = "from TBusin busin inner join fetch busin.TCustomerByCCusid left join fetch busin.TCustomerByCCusid2 inner join fetch busin.TBusinType inner join fetch busin.TUser left join fetch busin.TSalerByCApplyby where busin.CId = " + id;
        List list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return (TBusin)list.get(0);
        }
        return null;
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_busin where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateEntityState(Serializable id, short state, String remarks) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update t_busin set c_state = ").append(state);
        hql.append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).executeUpdate();
    }

    public void updatePrintState(Serializable id) throws Exception {
        String hql = "update t_cost set c_print = 1 where c_businid = " + id;
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql).executeUpdate();
    }

    public void updateOrder(int businId, short flag) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin b set b.c_order = ").append(flag).append(" where b.c_id = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateOrders() throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin bb set bb.c_order = 1 ").append(" where bb.c_arrivalDate is not null and ").append(" not exists (select 1 from t_cash cc where cc.c_businid = bb.c_id) and ").append(" not exists (select 1 from t_cash_rate rr where rr.c_businid = bb.c_id) and").append(" TIMESTAMPDIFF(DAY, bb.c_arrivalDate, '").append(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(CommonUtil.getDatetime())) + " 00:00:00') >= 7").append(" and bb.c_id > 0");
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateCostRate(int businId, double money) throws Exception {
        StringBuffer sql = new StringBuffer();
        if (money == 0.0) {
            sql.append("delete from t_cost where c_businid = ").append(businId).append(" and c_costitemid = 198");
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
        } else {
            sql.append("select count(*) from t_cost where c_businid = ").append(businId).append(" and c_costitemid = 198");
            int count = Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
            if (count > 0) {
                sql.setLength(0);
                sql.append("select c_id from t_cost where c_businid = ").append(businId).append(" and c_costitemid = 198 limit 0,1");
                int cid = Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
                sql.setLength(0);
                sql.append("update t_cost set c_money = ").append(money).append(" where c_id = ").append(cid);
                this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
            } else {
                sql.setLength(0);
                sql.append("insert into t_cost (c_businid, c_costitemid, c_money, c_state, c_print, c_createuserid, c_re, c_hidden)").append(" values (").append(businId).append(", 198, ").append(money).append(", 1, 0, 1, 'VAt 10%', 1)");
                this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
            }
        }
    }

    public void updateCostRate2(int businId, double money) throws Exception {
        StringBuffer sql = new StringBuffer();
        if (money == 0.0) {
            sql.append("delete from t_cost where c_businid = ").append(businId).append(" and c_costitemid = 199");
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
        } else {
            sql.setLength(0);
            sql.append("select count(*) from t_cost where c_businid = ").append(businId).append(" and c_costitemid = 199");
            int count = Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
            if (count > 0) {
                sql.setLength(0);
                sql.append("select c_id from t_cost where c_businid = ").append(businId).append(" and c_costitemid = 199 limit 0,1");
                int cid = Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
                sql.setLength(0);
                sql.append("update t_cost set c_money = ").append(money).append(" where c_id = ").append(cid);
                this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
            } else {
                sql.setLength(0);
                sql.append("insert into t_cost (c_businid, c_costitemid, c_money, c_state, c_print, c_createuserid, c_re, c_hidden)").append(" values (").append(businId).append(", 199, ").append(money).append(", 1, 0, 1, 'IB 1%', 1)");
                this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
            }
        }
    }

    public void updateRecieveMoney(int businId, double money, int flag) throws Exception {
        StringBuffer sql = new StringBuffer();
        if (flag == 1) {
            sql.append("update t_busin set c_recieve_money1 = ").append(money);
        } else if (flag == 2) {
            sql.append("update t_busin set c_recieve_money2 = ").append(money);
        } else if (flag == 3) {
            sql.append("update t_busin set c_recieve_money4 = ").append(money);
        } else if (flag == 4) {
            sql.append("update t_busin set c_recieve_money = ").append(money);
        }
        sql.append(", c_lastDate2 = '").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(CommonUtil.getDatetime())).append("'").append(" where c_id = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateScheduleArchive(int businId, short flag, double cmoney) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin set c_schedule_archive = ").append(flag);
        if (flag == 1 && cmoney > 0.0) {
            sql.append(", c_recieve_money = ").append(cmoney);
        }
        sql.append(" where c_id = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateRateArchive(int businId, short flag) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin set c_rate_archive = ").append(flag).append(" where c_id = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateLock(int businId, short flag) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin set c_lock = ").append(flag).append(" where c_id = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateLockFlag(int businId, short flag) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin set c_lock_flag = ").append(flag).append(", c_lock_by = ");
        if (flag == 1) {
            sql.append(CommonUtil.getEmpId());
        } else {
            sql.append(" null ");
        }
        sql.append(" where c_id = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public String getMaxDN(String cdate) {
        String maxNumber = "";
        String sql = "";
        sql = "select max(right(left(c_nodate1, 8), 5)) from t_busin where right(c_nodate1,4) = " + cdate.substring(3);
        Object max = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        sql = "select max(right(left(c_nodate3, 8), 5)) from t_busin where right(c_nodate3,4) = " + cdate.substring(3);
        Object max2 = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        if ((max == null || "".equals(max)) && (max2 == null || "".equals(max2))) {
            maxNumber = "00001";
        } else {
            if ((max == null || "".equals(max)) && max2 != null && !"".equals(max2.toString())) {
                maxNumber = String.valueOf(100001 + Integer.parseInt(max2.toString())).substring(1);
            }
            if ((max2 == null || "".equals(max2)) && max != null && !"".equals(max.toString())) {
                maxNumber = String.valueOf(100001 + Integer.parseInt(max.toString())).substring(1);
            }
            if (max != null && !"".equals(max.toString()) && max2 != null && !"".equals(max2.toString())) {
                maxNumber = Integer.parseInt(max.toString()) > Integer.parseInt(max2.toString()) ? String.valueOf(100001 + Integer.parseInt(max.toString())).substring(1) : String.valueOf(100001 + Integer.parseInt(max2.toString())).substring(1);
            }
        }
        return "DN " + maxNumber + "/" + cdate;
    }

    public String getMaxINV(String cdate) {
        String maxNumber = "";
        String sql = "";
        sql = "select min(c_code) from t_del_dn where c_type = 2 and right(left(c_code,6),2) = '" + cdate.substring(5, 7) + "'";
        Object min = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        if (min != null) {
            maxNumber = min.toString();
            sql = "delete from t_del_dn where c_type = 2 and c_code = '" + maxNumber + "'";
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
            maxNumber = maxNumber.substring(7);
        } else {
            sql = "select max(right(c_nodate5,4)) from t_busin where right(left(c_nodate5,6),2) = '" + cdate.substring(5, 7) + "'";
            Object max = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
            sql = "select max(right(c_nodate7,4)) from t_busin where right(left(c_nodate7,6),2) = '" + cdate.substring(5, 7) + "'";
            Object max2 = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
            if ((max == null || "".equals(max)) && (max2 == null || "".equals(max2))) {
                maxNumber = "0001";
            } else {
                if ((max == null || "".equals(max)) && max2 != null && !"".equals(max2.toString())) {
                    maxNumber = String.valueOf(10001 + Integer.parseInt(max2.toString())).substring(1);
                }
                if ((max2 == null || "".equals(max2)) && max != null && !"".equals(max.toString())) {
                    maxNumber = String.valueOf(10001 + Integer.parseInt(max.toString())).substring(1);
                }
                if (max != null && !"".equals(max.toString()) && max2 != null && !"".equals(max2.toString())) {
                    maxNumber = Integer.parseInt(max.toString()) > Integer.parseInt(max2.toString()) ? String.valueOf(10001 + Integer.parseInt(max.toString())).substring(1) : String.valueOf(10001 + Integer.parseInt(max2.toString())).substring(1);
                }
            }
        }
        return "INV " + cdate.substring(5, 7) + "-" + maxNumber;
    }

    public void saveDel(TDelDn entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void updateLogNewFlag(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin_log set c_newCostFlag = 0 where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateCostDupFlag(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_cost set c_costDupFlag = 0 where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public List<VBusincusreport> getBusinCusReport(Object[] obj) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sub = new StringBuffer();
        sql.append("select distinct cus.c_id as cusid, cus.c_addr as cusname, ifnull(cus.c_flag,0) as flag,");
        sub.append("case when ifnull(cus.c_black,0) = 0 then 0 else ").append(" case when cus.c_lastDate < (select max(busin.c_date) from t_busin busin where busin.c_cusid = cus.c_id) then 1 else 0 end end");
        sql.append(sub).append(" as black");
        sql.append(" from t_busin bb, t_customer cus").append(" where bb.c_cusid = cus.c_id and bb.c_state = 1");
        if (obj[0] != null && !"".equals(obj[0])) {
            sql.append(" and bb.c_date >= '").append(obj[0]).append(" 00:00:00'");
        }
        if (obj[1] != null && !"".equals(obj[1])) {
            sql.append(" and bb.c_date <= '").append(obj[1]).append(" 23:59:59'");
        }
        sql.append(" order by ").append(sub).append(" desc, ifnull(cus.c_flag,0) desc, cus.c_id asc");
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(VBusincusreport.class).list();
    }

    public double getBusinLimit(int businid) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ifnull(cmoney, 0) - ifnull(receiveMoney1, 0) - ifnull(receiveMoney2, 0) - ifnull(receiveMoney4, 0)").append(" from v_businreceivereport").append(" where businId = ").append(businid);
        return Double.parseDouble(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
    }

    public void updateLocks() throws Exception {
        Calendar cale = Calendar.getInstance();
        cale.set(5, 0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastDay = format.format(cale.getTime());
        StringBuffer sql = new StringBuffer();
        sql.append("update t_busin set c_lock = 1 where c_id in ").append(" (select vv.businId from v_busin vv where ifnull(vv.nodate,'') <> '' and vv.nodate <= '").append(lastDay).append(" 23:59:59')");
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public double getProfit(int businid) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ifnull(profit,0) from v_businProfitReport where businId = ").append(businid);
        return Double.parseDouble(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
    }

    public int getBusinVatCount(int businId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from t_cash cc where cc.c_businid = ").append(businId);
        int count = 0;
        count = Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
        if (count > 0) {
            return count;
        }
        sql.setLength(0);
        sql.append("select count(*) from t_cash_rate rr where rr.c_businid = ").append(businId);
        count = Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
        return count;
    }

    public String getGroupHql(TFinanceGroup query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TFinanceGroup gg");
        if (query != null && query.getCName() != null && !"".equals(query.getCName().trim())) {
            hql.append(" where gg.CName like '%").append(query.getCName().trim()).append("%'");
        }
        if (flag == 1) {
            return hql.append(" order by gg.CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TFinanceGroup> getGroupList(TFinanceGroup query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getGroupHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getGroupPages(TFinanceGroup query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getGroupHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveGroup(TFinanceGroup entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TFinanceGroup getGroupById(int id) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TFinanceGroup gg where gg.CId = ").append(id);
        List list = this.getHibernateTemplate().find(hql.toString());
        if (list != null && list.size() > 0) {
            return (TFinanceGroup)list.get(0);
        }
        return null;
    }

    public void deleteGroup(int id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_finance_group where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkName(String name, Integer id) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TFinanceGroup where CName = '").append(name).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public void saveGroupDetail(TFinanceGroupItem item) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)item);
    }

    public List<TFinanceGroupItem> getGroupDetail(int pid) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TFinanceGroupItem ii inner join fetch ii.TCostItem where ii.CPid = ").append(pid).append(" order by ii.CId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void deleteGroupDetail(int id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_finance_group_item where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteAllGroupDetail(int pid) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_finance_group_item where c_pid = ").append(pid);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public List<TFinanceGroup> getGroupValidList() {
        StringBuffer hql = new StringBuffer();
        hql.append("from TFinanceGroup order by CId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public String getCostGroupHql(TCostGroup query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TCostGroup gg");
        if (query != null && query.getCName() != null && !"".equals(query.getCName().trim())) {
            hql.append(" where gg.CName like '%").append(query.getCName().trim()).append("%'");
        }
        if (flag == 1) {
            return hql.append(" order by gg.CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TCostGroup> getGroupList(TCostGroup query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostGroupHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getGroupPages(TCostGroup query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostGroupHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveGroup(TCostGroup entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TCostGroup getCostGroupById(int id) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TCostGroup gg where gg.CId = ").append(id);
        List list = this.getHibernateTemplate().find(hql.toString());
        if (list != null && list.size() > 0) {
            return (TCostGroup)list.get(0);
        }
        return null;
    }

    public void deleteCostGroup(int id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost_group where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkCostGroupName(String name, Integer id) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TCostGroup where CName = '").append(name).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public void saveGroupDetail(TCostGroupItem item) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)item);
    }

    public List<TCostGroupItem> getCostGroupDetail(int pid) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TCostGroupItem ii inner join fetch ii.TCostItem where ii.CPid = ").append(pid).append(" order by ii.COrder asc, ii.CId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void deleteCostGroupDetail(int id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost_group_item where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteAllCostGroupDetail(int pid) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost_group_item where c_pid = ").append(pid);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public List<TCostGroup> getCostGroupValidList() {
        StringBuffer hql = new StringBuffer();
        hql.append("from TCostGroup order by CId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public String getInvUserHql(VBusinInvUser query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hql.append("from VBusinInvUser vv");
        if (query != null) {
            if (query.getEid() != null && query.getEid() > 0) {
                where.append(" and vv.eid = ").append(query.getEid());
            }
            if (query.getCcode() != null && !"".equals(query.getCcode().trim())) {
                where.append(" and vv.ccode like '%").append(query.getCcode().trim()).append("%'");
            }
            if (query.getCusid() != null && query.getCusid() > 0) {
                where.append(" and vv.cusid = ").append(query.getCusid());
            }
        }
        boolean notice = false;
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and vv.createdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and vv.createdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
            if (obj[2] != null && Integer.parseInt(obj[2].toString()) == 2) {
                where.append(" and vv.noticeFlag = 1");
                notice = true;
            }
        }
        if (where != null && !"".equals(where.toString().trim())) {
            hql.append(" where ").append(where.toString().substring(4));
        }
        if (flag == 1) {
            if (!notice) {
                return hql.append(" order by vv.createdate desc").toString();
            }
            return hql.append(" order by vv.lastNoticeDate desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VBusinInvUser> getInvUserList(VBusinInvUser query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getInvUserHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getInvUserPages(VBusinInvUser query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getInvUserHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public String getProblemHql(VBusinAsk query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hql.append("from VBusinAsk vv where vv.id.noticeFlag = 1");
        if (query != null) {
            if (query.getId().getEid() != null && query.getId().getEid() > 0L) {
                hql.append(" and vv.id.eid = ").append(query.getId().getEid());
            }
            if (query.getId().getCcode() != null && !"".equals(query.getId().getCcode().trim())) {
                hql.append(" and vv.id.ccode like '%").append(query.getId().getCcode().trim()).append("%'");
            }
            if (query.getId().getCusid() != null && query.getId().getCusid() > 0) {
                hql.append(" and vv.id.cusid = ").append(query.getId().getCusid());
            }
        }
        if (flag == 1) {
            return hql.append(" order by vv.id.createdate desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VBusinInvUser> getProblemNoticeList(VBusinAsk query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getProblemHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getProblemNoticePages(VBusinAsk query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getProblemHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public String getBusinSumReportHql(VBusinSumReport query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hql.append("from VBusinSumReport vv");
        if (query != null && query.getEid() != null && query.getEid() > 0) {
            where.append(" and vv.eid = ").append(query.getEid());
        }
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                where.append(" and vv.createdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                where.append(" and vv.createdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
        }
        if (where != null && !"".equals(where.toString().trim())) {
            hql.append(" where ").append(where.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by vv.createdate asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VBusinSumReport> getBusinSumReport(VBusinSumReport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBusinSumReportHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getBusinSumReportPages(VBusinSumReport query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getBusinSumReportHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public String getMaxRefno() {
        String maxBillNo = null;
        String sql = "select max(c_ref_no) from t_cost_group_info";
        Object max = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        maxBillNo = max == null ? "00000126" : String.valueOf(100000001 + Integer.parseInt(max.toString())).substring(1);
        return maxBillNo;
    }

    public List<TCostGroupInfo> getCostGroupInfoList(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TCostGroupInfo info inner join fetch info.TCostGroup where info.CBusinId = ").append(businId).append(" order by info.CId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public TCostGroupInfo getCostGroupInfo(int businId, int groupId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TCostGroupInfo info inner join fetch info.TCostGroup where info.CBusinId = ").append(businId).append(" and info.TCostGroup.CId = ").append(groupId);
        List list = this.getHibernateTemplate().find(hql.toString());
        if (list != null && list.size() > 0) {
            return (TCostGroupInfo)list.get(0);
        }
        return null;
    }

    public void saveCostGroupInfo(TCostGroupInfo info) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)info);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
    }

    public void deleteCostGroupInfo(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost_group_info where c_groupId not in (select c_groupid from t_cost cost where cost.c_businId = ").append(businId).append(") and c_businId = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteAllCostGroupInfo(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost_group_info where c_groupId = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void saveBiaoji(TBiaoji bb) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)bb);
    }

    public TBiaoji getBiaoji(int businId, int groupId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TBiaoji bb where bb.TBusin.CId = ").append(businId).append(" and bb.TCostGroup.CId = ").append(groupId);
        List list = this.getHibernateTemplate().find(hql.toString());
        if (list != null && list.size() > 0) {
            return (TBiaoji)list.get(0);
        }
        return null;
    }

    public String getCostGroupBiaojiHql(TBiaoji query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        String fetch = "";
        if (flag == 1) {
            fetch = " fetch ";
        }
        hql.append("from TBiaoji bb inner join ").append(fetch).append(" bb.TBusin").append(" inner join ").append(fetch).append(" bb.TCostGroup").append(" left join ").append(fetch).append(" bb.TBusin.TCustomerByCCusid").append(" where bb.CNotice = 1 and bb.CMoney <> bb.CMoney2");
        if (query != null) {
            if (query.getTBusin() != null && query.getTBusin().getCBillNo() != null && !"".equals(query.getTBusin().getCBillNo().trim())) {
                hql.append(" and bb.TBusin.CBillNo like '%").append(query.getTBusin().getCBillNo().trim()).append("%'");
            }
            if (query.getTBusin().getTCustomerByCCusid() != null && query.getTBusin().getTCustomerByCCusid().getCId() != null && query.getTBusin().getTCustomerByCCusid().getCId() > 0) {
                hql.append(" and bb.TBusin.TCustomerByCCusid.CId = ").append(query.getTBusin().getTCustomerByCCusid().getCId());
            }
        }
        if (flag == 1) {
            return hql.append(" order by bb.TBusin.CId asc, bb.TCostGroup.CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TBiaoji> getCostGroupBiaojiReport(TBiaoji query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostGroupBiaojiHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getCostGroupBiaojiReportPages(TBiaoji query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getCostGroupBiaojiHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public List<TBiaoji> getCostGroupBiaojiReport(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TBiaoji bb inner join fetch bb.TBusin inner join fetch bb.TCostGroup where bb.TBusin.CId = ").append(businId);
        return this.getHibernateTemplate().find(hql.toString());
    }

    public int checkBiaojiNotExistsCostgroupinfo(int businId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from t_cost_group_info info where info.c_businId = ").append(businId).append(" and not exists").append(" (select 1 from t_biaoji bb where bb.c_businId = info.c_businId and bb.c_groupId = info.c_groupId and info.c_businId = ").append(businId).append(")");
        return Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult().toString());
    }

    public Timestamp getCashDate(int businId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select min(createdate) from v_busin_inv_tmp2 tt where tt.businid = ").append(businId);
        Object obj = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
        if (obj != null) {
            return (Timestamp)obj;
        }
        return null;
    }

    public double getCashMoney(int businId, int type) {
        double cmoney = 0.0;
        StringBuffer sql = new StringBuffer();
        if (type == 1 || type == 2) {
            sql.append("select round(sum(ifnull(cash.c_money,0) * ifnull(cash.c_count,0)),2)").append(" from t_cash cash where cash.c_type = ").append(type).append(" and cash.c_businId = ").append(businId);
        } else {
            sql.append("select round(sum(ifnull(rate.c_money,0)*ifnull(rate.c_count,0) + ifnull(rate.c_money,0)*ifnull(rate.c_count,0)*r.c_rate/100),2)").append(" from t_cash_rate rate, t_rate r where rate.c_rate = r.c_id and rate.c_businId = ").append(businId);
        }
        Object oo = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
        if (oo != null) {
            return Double.parseDouble(oo.toString());
        }
        return cmoney;
    }

    public double getCostMoney(int businId, int type) {
        double cmoney = 0.0;
        StringBuffer sql = new StringBuffer();
        if (type == 1) {
            sql.append("select sum(case when ifnull(cost.c_groupid,0) = 0 then ifnull(cost.c_money,0) else 0 end").append(" from t_cost cost where cost.c_businid = ").append(businId);
        } else {
            sql.append("select sum(ifnull(gg.cmoney,0))").append(" from v_businProfitReport_group gg where gg.businid = ").append(businId);
        }
        Object oo = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
        if (oo != null) {
            return Double.parseDouble(oo.toString());
        }
        return cmoney;
    }

	public void saveBusinHis(TBusinHis his) {
		this.getHibernateTemplate().save(his);
	}
}


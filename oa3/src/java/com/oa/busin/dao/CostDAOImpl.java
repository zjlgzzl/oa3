/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCost
 *  com.common.entity.VCostGroupSum
 *  com.common.util.CommonUtil
 *  com.oa.busin.dao.CostDAO
 *  com.oa.busin.dao.CostDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.busin.dao;

import com.common.entity.TCost;
import com.common.entity.VCostGroupSum;
import com.common.util.CommonUtil;
import com.oa.busin.dao.CostDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CostDAOImpl
extends HibernateDaoSupport
implements CostDAO {
    public List<TCost> getList(int businId, short groupId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from TCost cost left join fetch cost.TCostItem left join fetch cost.TCostGroup where cost.TBusin.CId = ").append(businId);
        if (CommonUtil.getFun226() == 0) {
            hql.append(" and cost.TUserByCCreateUserid.CId = ").append(CommonUtil.getUserId());
        }
        hql.append(" and not exists ").append(" (select 1 from TCostUser uu where uu.CCostid = cost.TCostItem.CId and ").append(" uu.CUserid = ").append(CommonUtil.getUserId()).append(")");
        if (groupId != 9) {
            if (groupId == 0) {
                hql.append(" and cost.TCostGroup is null");
            } else {
                hql.append(" and cost.TCostGroup is not null");
            }
        }
        hql.append(" order by cost.COrder asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveCost(TCost entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void deleteCost(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteCostByBusinid(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int getFAuditCount(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TCost where CState = 2 and TBusin.CId = ").append(businId);
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public void updateCostState(Serializable businId, short state) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_cost set c_state = ").append(state).append(" where c_businid = ").append(businId).append(" and c_state = 2");
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateCostDetailState(Serializable id, short state) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_cost set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public Object[] getCostInfo(int id) {
        Object[] obj = new Object[9];
        StringBuffer sql = new StringBuffer();
        sql.append("select cost.TCostItem.CId, cost.CMoney, cost.CRe, cost.CRemarks,").append("  cost.CRemarks2, cost.CRemarks3, cost.CRemarks5, cost.CRemarks4, cost.CRiel ").append(" from TCost cost where cost.CId = ").append(id).append(" and cost.CId not in (198,199)");
        List<Object[]> list = this.getHibernateTemplate().find(sql.toString());
        if (list != null && list.size() > 0) {
            for (Object[] object : list) {
                obj[0] = object[0];
                obj[1] = object[1];
                obj[2] = object[2];
                obj[3] = object[3];
                obj[4] = object[4];
                obj[5] = object[5];
                obj[6] = object[6];
                obj[7] = object[7];
                obj[8] = object[8];
            }
        }
        return obj;
    }

    public TCost getCostById(int id) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TCost where CId = ").append(id);
        return (TCost)this.getHibernateTemplate().find(hql.toString()).get(0);
    }

    public List<VCostGroupSum> getCostGroupSumByBusinId(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from VCostGroupSum ss where ss.id.businId = ").append(businId).append(" order by ss.id.infoId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public VCostGroupSum getCostGroupSumByBusinId(int businId, int groupId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from VCostGroupSum ss where ss.id.businId = ").append(businId).append(" and ss.id.groupId = ").append(groupId).append(" order by ss.id.groupId asc");
        List list = this.getHibernateTemplate().find(hql.toString());
        if (list != null && list.size() > 0) {
            return (VCostGroupSum)list.get(0);
        }
        return null;
    }
}


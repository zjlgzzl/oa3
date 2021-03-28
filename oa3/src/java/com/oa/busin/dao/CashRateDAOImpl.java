/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCashRate
 *  com.oa.busin.dao.CashRateDAO
 *  com.oa.busin.dao.CashRateDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.busin.dao;

import com.common.entity.TCashRate;
import com.oa.busin.dao.CashRateDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CashRateDAOImpl
extends HibernateDaoSupport
implements CashRateDAO {
    public List<TCashRate> getList(int businId, short type) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from TCashRate rate left outer join fetch rate.TCostItem left outer join fetch rate.TRate where rate.CType = ").append(type).append(" and rate.TBusin.CId = ").append(businId).append(" order by rate.COrder asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveRate(TCashRate entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void deleteRate(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cash_rate where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteRateByBusinid(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cash_rate where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public Object[] getCashInfo(int id) {
        Object[] obj = new Object[4];
        StringBuffer sql = new StringBuffer();
        sql.append("select cost.TCostItem.CId, cost.CMoney, cost.CCount, cost.TRate.CId").append(" from TCashRate cost where cost.CId = ").append(id);
        List<Object[]> list = this.getHibernateTemplate().find(sql.toString());
        for (Object[] object : list) {
            obj[0] = object[0];
            obj[1] = object[1];
            obj[2] = object[2];
            obj[3] = object[3];
        }
        return obj;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCash
 *  com.oa.busin.dao.CashDAO
 *  com.oa.busin.dao.CashDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.busin.dao;

import com.common.entity.TCash;
import com.oa.busin.dao.CashDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CashDAOImpl
extends HibernateDaoSupport
implements CashDAO {
    public List<TCash> getList(int businId, short type) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from TCash cash left outer join fetch cash.TCostItem where cash.CType = ").append(type).append(" and cash.TBusin.CId = ").append(businId).append(" order by cash.COrder asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveCash(TCash entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void deleteCash(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cash where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteCashByBusinid(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cash where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateCashDetailState(Serializable id, short state) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_cash set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public Object[] getCashInfo(int id) {
        Object[] obj = new Object[3];
        StringBuffer sql = new StringBuffer();
        sql.append("select cost.TCostItem.CId, cost.CMoney, cost.CCount").append(" from TCash cost where cost.CId = ").append(id);
        List<Object[]> list = this.getHibernateTemplate().find(sql.toString());
        for (Object[] object : list) {
            obj[0] = object[0];
            obj[1] = object[1];
            obj[2] = object[2];
        }
        return obj;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TService
 *  com.oa.busin.dao.BusinServiceDAO
 *  com.oa.busin.dao.BusinServiceDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.busin.dao;

import com.common.entity.TService;
import com.oa.busin.dao.BusinServiceDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusinServiceDAOImpl
extends HibernateDaoSupport
implements BusinServiceDAO {
    public List<TService> getList(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from TService where TBusin.CId = ").append(businId);
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveService(TService entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void deleteService(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_service where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteServiceByBusinid(Serializable businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_service where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }
}


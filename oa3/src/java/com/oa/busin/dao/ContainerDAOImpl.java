/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainer
 *  com.oa.busin.dao.ContainerDAO
 *  com.oa.busin.dao.ContainerDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.busin.dao;

import com.common.entity.TContainer;
import com.oa.busin.dao.ContainerDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ContainerDAOImpl
extends HibernateDaoSupport
implements ContainerDAO {
    public List<TContainer> getList(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from TContainer where TBusin.CId = ").append(businId);
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveContainer(TContainer entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public void deleteContainer(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_container where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void deleteContainerByBusinid(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_container where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public Object[] getContainerInfo(int id) {
        Object[] obj = new Object[6];
        StringBuffer sql = new StringBuffer();
        sql.append("select cost.CContainerNum, cost.CContainerType, cost.CCount,").append(" cost.CRemarks, cost.CRemarks2, cost.CTrucker ").append(" from TContainer cost where cost.CId = ").append(id);
        List<Object[]> list = this.getHibernateTemplate().find(sql.toString());
        for (Object[] object : list) {
            obj[0] = object[0];
            obj[1] = object[1];
            obj[2] = object[2];
            obj[3] = object[3];
            obj[4] = object[4];
            obj[5] = object[5];
        }
        return obj;
    }
}


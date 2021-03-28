/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TRate
 *  com.common.util.PageBean
 *  com.oa.rate.dao.RateDAO
 *  com.oa.rate.dao.RateDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.rate.dao;

import com.common.entity.TRate;
import com.common.util.PageBean;
import com.oa.rate.dao.RateDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RateDAOImpl
extends HibernateDaoSupport
implements RateDAO {
    public String getHql(TRate query, int flag) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TRate");
        if (flag == 1) {
            return hql.append(" order by CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TRate> getList(TRate query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TRate query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TRate entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TRate getEntityById(Serializable id) {
        return (TRate)this.getHibernateTemplate().get(TRate.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_rate where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkName(Serializable id, double name) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TRate where CRate = ").append(name);
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public List<TRate> getValidList() {
        String hql = "from TRate order by CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        StringBuffer hql = new StringBuffer();
        int count = 0;
        hql.append("select count(*) from TCashRate where TRate.CId = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        return count;
    }
}


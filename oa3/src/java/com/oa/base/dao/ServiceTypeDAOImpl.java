/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TServiceType
 *  com.common.util.PageBean
 *  com.oa.base.dao.ServiceTypeDAO
 *  com.oa.base.dao.ServiceTypeDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.base.dao;

import com.common.entity.TServiceType;
import com.common.util.PageBean;
import com.oa.base.dao.ServiceTypeDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ServiceTypeDAOImpl
extends HibernateDaoSupport
implements ServiceTypeDAO {
    public String getHql(TServiceType query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from TServiceType");
        if (query != null && query.getCName() != null && !"".equals(query.getCName().trim())) {
            whereStr.append(" and CName like '%").append(query.getCName()).append("%'");
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TServiceType> getList(TServiceType query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TServiceType query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TServiceType entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TServiceType getEntityById(Serializable id) {
        return (TServiceType)this.getHibernateTemplate().get(TServiceType.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_service_type where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkName(Serializable id, String name) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TServiceType where CName = '").append(name).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update t_service_type set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).executeUpdate();
    }

    public List<TServiceType> getValidList() {
        String hql = "from TServiceType where CState = 1 order by CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        String hql = "select count(*) from TServiceItem where TServiceType.CId = " + id;
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }
}


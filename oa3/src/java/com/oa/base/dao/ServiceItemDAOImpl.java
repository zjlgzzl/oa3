/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TServiceItem
 *  com.common.util.PageBean
 *  com.oa.base.dao.ServiceItemDAO
 *  com.oa.base.dao.ServiceItemDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.base.dao;

import com.common.entity.TServiceItem;
import com.common.util.PageBean;
import com.oa.base.dao.ServiceItemDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ServiceItemDAOImpl
extends HibernateDaoSupport
implements ServiceItemDAO {
    public String getHql(TServiceItem query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from TServiceItem item");
        if (flag == 1) {
            hql.append(" inner join fetch item.TServiceType ");
        } else {
            hql.append(" inner join item.TServiceType ");
        }
        if (query != null && query.getCName() != null && !"".equals(query.getCName().trim())) {
            whereStr.append(" and item.CName like '%").append(query.getCName()).append("%'");
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by item.CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TServiceItem> getList(TServiceItem query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TServiceItem query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TServiceItem entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TServiceItem getEntityById(Serializable id) {
        return (TServiceItem)this.getHibernateTemplate().get(TServiceItem.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_service_item where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkName(Serializable typeId, Serializable id, String name) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TServiceItem where TServiceType.CId = ").append(typeId).append(" and CName = '").append(name).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update t_service_item set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).executeUpdate();
    }

    public List<TServiceItem> getValidList(int typeId) {
        String hql = "from TServiceItem where CState = 1 and TServiceType.CId = " + typeId + "order by CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        String hql = "select count(*) from TService where TServiceItem.CId = " + id;
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }
}


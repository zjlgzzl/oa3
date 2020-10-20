/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerModel
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConModelDAO
 *  com.oa.container.dao.ConModelDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.container.dao;

import com.common.entity.TContainerModel;
import com.common.util.PageBean;
import com.oa.container.dao.ConModelDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ConModelDAOImpl
extends HibernateDaoSupport
implements ConModelDAO {
    public String getHql(TContainerModel query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from TContainerModel");
        if (query != null && query.getCCode() != null && !"".equals(query.getCCode().trim())) {
            whereStr.append(" and CCode like '%").append(query.getCCode()).append("%'");
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TContainerModel> getList(TContainerModel query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TContainerModel query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TContainerModel entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TContainerModel getEntityById(Serializable id) {
        return (TContainerModel)this.getHibernateTemplate().get(TContainerModel.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_container_model where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkCode(Serializable id, String code) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TContainerModel where CCode = '").append(code).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public List<TContainerModel> getValidList() {
        String hql = "from TContainerModel where CState = 1 order by CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        String hql = "select count(*) from TContainerInfo where TContainerModel.CId  = " + id;
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TType
 *  com.common.util.PageBean
 *  com.oa.hscode.dao.HsCodeDAO
 *  com.oa.hscode.dao.HsCodeDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.type.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.entity.TType;
import com.common.util.PageBean;

public class TypeDAOImpl
extends HibernateDaoSupport
implements TypeDAO {
    public String getHql(TType query, int flag) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TType hh where 1=1");
        if (query != null) {
            if (query.getName() != null && !"".equals(query.getName().trim())) {
            	hql.append(" and hh.name like '%").append(query.getName().trim()).append("%'");
            }
        }
        if (flag == 1) {
            return hql.append(" order by hh.createDate desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TType> getList(TType query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TType query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TType entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TType getEntityById(Integer id) {
        return (TType)this.getHibernateTemplate().get(TType.class, id);
    }

    public void deleteEntity(Integer id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_type where id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

	public int checkDup(String var1, Integer id) {
		StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TType where name = '").append(var1).append("'");
        if (id != null && id > 0) {
            hql.append(" and id <> ").append(id);
        }
        return Integer.parseInt("" + this.getHibernateTemplate().find(hql.toString()).get(0));
	}

}


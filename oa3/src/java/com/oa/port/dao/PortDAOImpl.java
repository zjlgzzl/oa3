/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TPort
 *  com.common.util.PageBean
 *  com.oa.hscode.dao.HsCodeDAO
 *  com.oa.hscode.dao.HsCodeDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.port.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.entity.TPort;
import com.common.util.PageBean;

public class PortDAOImpl
extends HibernateDaoSupport
implements PortDAO {
    public String getHql(TPort query, int flag) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TPort hh where 1=1");
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

    public List<TPort> getList(TPort query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TPort query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TPort entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TPort getEntityById(Integer id) {
        return (TPort)this.getHibernateTemplate().get(TPort.class, id);
    }

    public void deleteEntity(Integer id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_port where id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

	public int checkDup(String var1, Integer id) {
		StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TPort where name = '").append(var1).append("'");
        if (id != null && id > 0) {
            hql.append(" and id <> ").append(id);
        }
        return Integer.parseInt("" + this.getHibernateTemplate().find(hql.toString()).get(0));
	}

}


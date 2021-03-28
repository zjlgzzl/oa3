/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TAgent
 *  com.common.util.PageBean
 *  com.oa.hscode.dao.HsCodeDAO
 *  com.oa.hscode.dao.HsCodeDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.agent.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.entity.TAgent;
import com.common.util.PageBean;

public class AgentDAOImpl
extends HibernateDaoSupport
implements AgentDAO {
    public String getHql(TAgent query, int flag) {
        StringBuffer hql = new StringBuffer();
        hql.append("from TAgent hh where 1=1");
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

    public List<TAgent> getList(TAgent query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TAgent query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TAgent entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TAgent getEntityById(Integer id) {
        return (TAgent)this.getHibernateTemplate().get(TAgent.class, id);
    }

    public void deleteEntity(Integer id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_agent where id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

	public int checkDup(String var1, Integer id) {
		StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TAgent where name = '").append(var1).append("'");
        if (id != null && id > 0) {
            hql.append(" and id <> ").append(id);
        }
        return Integer.parseInt("" + this.getHibernateTemplate().find(hql.toString()).get(0));
	}

}


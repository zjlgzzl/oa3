/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.THscode
 *  com.common.util.PageBean
 *  com.oa.hscode.dao.HsCodeDAO
 *  com.oa.hscode.dao.HsCodeDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.hscode.dao;

import com.common.entity.THscode;
import com.common.util.PageBean;
import com.oa.hscode.dao.HsCodeDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HsCodeDAOImpl
extends HibernateDaoSupport
implements HsCodeDAO {
    public String getHql(THscode query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        String fetch = "";
        if (flag == 1) {
            fetch = "fetch";
        }
        hql.append("from THscode hh inner join ").append(fetch).append(" hh.TUser");
        if (query != null) {
            if (query.getCDescription() != null && !"".equals(query.getCDescription().trim())) {
                where.append(" and hh.CDescription like '%").append(query.getCDescription().trim()).append("%'");
            }
            if (query.getCName() != null && !"".equals(query.getCName().trim())) {
                where.append(" and hh.CName like '%").append(query.getCName().trim()).append("%'");
            }
            if (query.getCHscode() != null && !"".equals(query.getCHscode().trim())) {
                where.append(" and hh.CHscode like '%").append(query.getCHscode().trim()).append("%'");
            }
            if (query.getCDetails() != null && !"".equals(query.getCDetails().trim())) {
                where.append(" and hh.CDetails like '%").append(query.getCDetails().trim()).append("%'");
            }
        }
        where.append(" and hh.CType = 1");
        if (where != null && !"".equals(where.toString().trim())) {
            hql.append(" where ").append(where.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by hh.CCreateDate desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<THscode> getList(THscode query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(THscode query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public String getHql2(THscode query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        String fetch = "";
        if (flag == 1) {
            fetch = "fetch";
        }
        hql.append("from THscode hh inner join ").append(fetch).append(" hh.TUser");
        if (query != null) {
            if (query.getCDescription() != null && !"".equals(query.getCDescription().trim())) {
                where.append(" and hh.CDescription like '%").append(query.getCDescription().trim()).append("%'");
            }
            if (query.getCName() != null && !"".equals(query.getCName().trim())) {
                where.append(" and hh.CName like '%").append(query.getCName().trim()).append("%'");
            }
            if (query.getCHscode() != null && !"".equals(query.getCHscode().trim())) {
                where.append(" and hh.CHscode like '%").append(query.getCHscode().trim()).append("%'");
            }
            if (query.getCDetails() != null && !"".equals(query.getCDetails().trim())) {
                where.append(" and hh.CDetails like '%").append(query.getCDetails().trim()).append("%'");
            }
        }
        where.append(" and hh.CType = 2");
        if (where != null && !"".equals(where.toString().trim())) {
            hql.append(" where ").append(where.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by hh.CCreateDate desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<THscode> getList2(THscode query, int pageNow, int pageSize) {
        String hql = this.getHql2(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages2(THscode query, int pageNow, int pageSize) {
        String hql = this.getHql2(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(THscode entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public THscode getEntityById(Serializable id) {
        return (THscode)this.getHibernateTemplate().get(THscode.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_hscode where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkDup(String des, Integer id) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from THscode where CDescription = '").append(des).append("'");
        if (id != null && id > 0) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt("" + this.getHibernateTemplate().find(hql.toString()).get(0));
    }
}


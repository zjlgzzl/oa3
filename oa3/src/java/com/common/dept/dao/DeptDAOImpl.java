/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.dept.dao.DeptDAO
 *  com.common.dept.dao.DeptDAOImpl
 *  com.common.entity.TDept
 *  com.common.util.PageBean
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.common.dept.dao;

import com.common.dept.dao.DeptDAO;
import com.common.entity.TDept;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DeptDAOImpl
extends HibernateDaoSupport
implements DeptDAO {
    public String getHql(TDept query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from TDept");
        if (query != null) {
            if (query.getCCode() != null && !"".equals(query.getCCode().trim())) {
                whereStr.append(" and CCode like '%").append(query.getCCode()).append("%'");
            }
            if (query.getCName() != null && !"".equals(query.getCName().trim())) {
                whereStr.append(" and CName like '%").append(query.getCName()).append("%'");
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TDept> getList(TDept query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TDept query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TDept entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TDept getEntityById(Serializable id) {
        return (TDept)this.getHibernateTemplate().get(TDept.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_dept where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkCode(Serializable id, String code) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TDept where CCode = '").append(code).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public int checkName(Serializable id, String name) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TDept where CName = '").append(name).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update t_dept set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).executeUpdate();
    }

    public List<TDept> getValidList() {
        String hql = "from TDept where CState = 1 order by CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        String hql = "select count(*) from TEmp where TDept.CId = " + id;
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }
}


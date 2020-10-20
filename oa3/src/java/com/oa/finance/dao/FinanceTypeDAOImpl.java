/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinancetype
 *  com.common.entity.TFinancetypeUser
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.finance.dao.FinanceTypeDAO
 *  com.oa.finance.dao.FinanceTypeDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.finance.dao;

import com.common.entity.TFinancetype;
import com.common.entity.TFinancetypeUser;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.finance.dao.FinanceTypeDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FinanceTypeDAOImpl
extends HibernateDaoSupport
implements FinanceTypeDAO {
    public String getHql(TFinancetype query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from TFinancetype tt");
        if (query != null) {
            if (query.getCName() != null && !"".equals(query.getCName().trim())) {
                whereStr.append(" and tt.CName like '%").append(query.getCName()).append("%'");
            }
            if (query.getCType() != null) {
                whereStr.append(" and tt.CType = ").append(query.getCType());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by tt.CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TFinancetype> getList(TFinancetype query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TFinancetype query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TFinancetype entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TFinancetype getEntityById(Serializable id) {
        return (TFinancetype)this.getHibernateTemplate().get(TFinancetype.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_financetype where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkName(Serializable id, String name) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TFinancetype where CName = '").append(name).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public List<TFinancetype> getValidList(int utype) {
        String hql = "from TFinancetype tt where tt.CState = 1 and tt.CType = " + utype;
        hql = String.valueOf(hql) + " and not exists (select 1 from TFinancetypeUser uu where uu.CTypeid = tt.CId and uu.CType = " + utype + " and uu.CUserid = " + CommonUtil.getUserId() + ")";
        hql = String.valueOf(hql) + " order by tt.CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TFinance where TFinancetype.CId = ").append(id);
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public List<Integer> getUserList(int typeid) {
        StringBuffer hql = new StringBuffer();
        hql.append("select CUserid from TFinancetypeUser where CTypeid = ").append(typeid);
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void deleteUser(int typeid) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_financetype_user where c_typeid = ").append(typeid);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void saveUser(TFinancetypeUser entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }
}


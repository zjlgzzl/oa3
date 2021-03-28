/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostItem
 *  com.common.entity.TCostUser
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.dao.CostItemDAO
 *  com.oa.base.dao.CostItemDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.base.dao;

import com.common.entity.TCostItem;
import com.common.entity.TCostUser;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.CostItemDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CostItemDAOImpl
extends HibernateDaoSupport
implements CostItemDAO {
    public String getHql(TCostItem query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from TCostItem");
        if (query != null) {
            if (query.getCName() != null && !"".equals(query.getCName().trim())) {
                whereStr.append(" and CName like :name");
            }
            if (query.getCType() != null) {
                whereStr.append(" and CType = ").append(query.getCType());
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

    public List<TCostItem> getList(TCostItem query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        if (query != null && query.getCName() != null && !"".equals(query.getCName().trim())) {
            return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("name", "%" + query.getCName() + "%").setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
        }
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TCostItem query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = 0;
        rowCount = query != null ? (query.getCName() != null && !"".equals(query.getCName().trim()) ? Integer.parseInt("" + this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("name", "%" + query.getCName() + "%").list().get(0)) : Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0))) : Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TCostItem entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TCostItem getEntityById(Serializable id) {
        return (TCostItem)this.getHibernateTemplate().get(TCostItem.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost_item where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkName(int typeId, Serializable id, String name) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TCostItem where CName = :name");
        hql.append(" and CType = ").append(typeId);
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql.toString()).setString("name", name).list().iterator().next().toString());
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update t_cost_item set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).executeUpdate();
    }

    public List<TCostItem> getValidList() {
        String hql = "from TCostItem cost where cost.CState = 1 and not exists  (select 1 from TCostUser uu where uu.CCostid = cost.CId and   uu.CUserid = " + CommonUtil.getUserId() + ")" + "  order by cost.CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        String hql = "select count(*) from TCost where TCostItem.CId = " + id;
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public int checkRef2(Serializable id) {
        int count = 0;
        int sum = 0;
        String hql = "select count(*) from TCash where TCostItem.CId = " + id;
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        sum += count;
        hql = "select count(*) from TCashRate where TCostItem.CId = " + id;
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        sum += count;
        return count;
    }

    public List<Integer> getUserList(int costid) {
        StringBuffer hql = new StringBuffer();
        hql.append("select CUserid from TCostUser where CCostid = ").append(costid);
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void deleteUser(int costid) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_cost_user where c_costid = ").append(costid);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void saveUser(TCostUser entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }
}


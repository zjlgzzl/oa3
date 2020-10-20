/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.dao.RoleDAO
 *  com.common.auth.dao.RoleDAOImpl
 *  com.common.entity.TRole
 *  com.common.entity.VUserFun
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.common.auth.dao;

import com.common.auth.dao.RoleDAO;
import com.common.entity.TRole;
import com.common.entity.VUserFun;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RoleDAOImpl
extends HibernateDaoSupport
implements RoleDAO {
    public List<Integer> getList(int userId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select CRoleid from TRole where CUserid = ").append(userId);
        return this.getHibernateTemplate().find(hql.toString());
    }

    public List<VUserFun> getUserFunList(int userId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from VUserFun where funid not in (204,209,507) and userid = ").append(userId).append(" order by ordernum asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveRole(TRole role) throws Exception {
        this.getHibernateTemplate().save((Object)role);
    }

    public void deleteRole(int userId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_role where c_userid = ").append(userId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }
}


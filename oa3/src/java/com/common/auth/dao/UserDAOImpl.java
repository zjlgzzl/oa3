/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.dao.UserDAO
 *  com.common.auth.dao.UserDAOImpl
 *  com.common.entity.TUser
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.common.auth.dao;

import com.common.auth.dao.UserDAO;
import com.common.entity.TUser;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDAOImpl
extends HibernateDaoSupport
implements UserDAO {
    public TUser getEntityById(Serializable id) {
        return (TUser)this.getHibernateTemplate().get(TUser.class, id);
    }

    public TUser findByUsername(String userName) {
        String hql = "from TUser where CUsername = ?";
        List userList = this.getHibernateTemplate().find(hql, (Object)userName);
        if (userList != null && userList.size() > 0) {
            return (TUser)userList.get(0);
        }
        return null;
    }

    public TUser findByEmpid(Serializable id) {
        String hql = "from TUser where CEmpid = ?";
        List userList = this.getHibernateTemplate().find(hql, (Object)id);
        if (userList != null && userList.size() > 0) {
            return (TUser)userList.get(0);
        }
        return null;
    }

    public void saveEntity(TUser user) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)user);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_user where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public void updateState(Serializable id, short state) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_user set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkDup(Serializable id, String userName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TUser where CUsername = '").append(userName).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public int checkRef(Serializable id) {
        return 0;
    }

	@Override
	public List<TUser> findUserList() {
		 String hql = "from TUser";
		 return this.getHibernateTemplate().find(hql);
	}
}


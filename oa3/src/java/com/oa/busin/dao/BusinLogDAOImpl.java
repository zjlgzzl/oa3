/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusinLog
 *  com.common.entity.VLog
 *  com.oa.busin.dao.BusinLogDAO
 *  com.oa.busin.dao.BusinLogDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.busin.dao;

import com.common.entity.TBusinLog;
import com.common.entity.VLog;
import com.oa.busin.dao.BusinLogDAO;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BusinLogDAOImpl
extends HibernateDaoSupport
implements BusinLogDAO {
    public List<VLog> getlist(int businId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from VLog where businId = ").append(businId).append(" order by logId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public void saveEntity(TBusinLog entity) throws Exception {
        this.getHibernateTemplate().save((Object)entity);
    }

    public void deleteEntity(int businId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_busin_log where c_businid = ").append(businId);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }
}


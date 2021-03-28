/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerInfo
 *  com.common.entity.VConview
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConInfoDAO
 *  com.oa.container.dao.ConInfoDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.container.dao;

import com.common.entity.TContainerInfo;
import com.common.entity.VConview;
import com.common.util.PageBean;
import com.oa.container.dao.ConInfoDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ConInfoDAOImpl
extends HibernateDaoSupport
implements ConInfoDAO {
    public String getHql(TContainerInfo query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        if (flag == 1) {
            hql.append("from TContainerInfo info inner join fetch info.TContainerModel");
        } else {
            hql.append("from TContainerInfo info");
        }
        if (query != null && query.getCCode() != null && !"".equals(query.getCCode().trim())) {
            whereStr.append(" and info.CCode like '%").append(query.getCCode()).append("%'");
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by info.CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TContainerInfo> getList(TContainerInfo query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TContainerInfo query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TContainerInfo entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TContainerInfo getEntityById(Serializable id) {
        return (TContainerInfo)this.getHibernateTemplate().get(TContainerInfo.class, id);
    }

    public TContainerInfo getEntityById2(Serializable id) {
        String hql = "from TContainerInfo info inner join fetch info.TContainerModel where info.CId = " + id;
        return (TContainerInfo)this.getHibernateTemplate().find(hql).get(0);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_container_info where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkCode(Serializable id, String code) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TContainerInfo where CCode = '").append(code).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public List<VConview> getValidList(Serializable id) {
        String hql = "";
        hql = Integer.parseInt(id.toString()) == -1 ? "from VConview where COpertype in (1,3)" : "from VConview where COpertype in (1,3) or infoId = " + id;
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        String hql = "select count(*) from TContainerRecord where TContainerInfo.CId  = " + id;
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }
}


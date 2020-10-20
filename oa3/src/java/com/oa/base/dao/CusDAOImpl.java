/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCustomer
 *  com.common.util.PageBean
 *  com.oa.base.dao.CusDAO
 *  com.oa.base.dao.CusDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.base.dao;

import com.common.entity.TCustomer;
import com.common.util.PageBean;
import com.oa.base.dao.CusDAO;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CusDAOImpl
extends HibernateDaoSupport
implements CusDAO {
    public String getHql(TCustomer query, int flag) throws Exception {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from TCustomer");
        if (query != null) {
        	if(query.getCName() != null && !"".equals(query.getCName().trim())) {
        		String cname = query.getCName();
        		if (cname.indexOf("'") > -1) {
        			cname = cname.replaceAll("'", "''");
        		}
        		whereStr.append(" and (CName like '%").append(cname).append("%' or").append(" CAddr like '%").append(cname).append("%')");
        	}
        	if(StringUtils.isNotBlank(query.getStartDate())) {
        		whereStr.append(" and CCreateDate >= '").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd-MM-yyyy").parse(query.getStartDate()))).append("'");
        	}
        	if(StringUtils.isNotBlank(query.getEndDate())) {
        		whereStr.append(" and CCreateDate <= '").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd-MM-yyyy").parse(query.getEndDate()))).append("'");
        	}
        	if(query.getSalerId() != null) {
        		whereStr.append(" and salerId = ").append(query.getSalerId()).append("");
        	}
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by CBlack desc, CId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<TCustomer> getList(TCustomer query, int pageNow, int pageSize) throws Exception {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(TCustomer query, int pageNow, int pageSize) throws Exception {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TCustomer entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TCustomer getEntityById(Serializable id) {
        return (TCustomer)this.getHibernateTemplate().get(TCustomer.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_customer where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkName(Serializable id, String name) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TCustomer where CName = '").append(name).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update t_customer set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).executeUpdate();
    }

    public List<TCustomer> getValidList() {
        String hql = "from TCustomer where CState = 1 order by CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        String hql = "select count(*) from TBusin where TCustomerByCCusid.CId = " + id;
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerRecord
 *  com.common.entity.VConcompute
 *  com.common.entity.VContainerRecord
 *  com.common.entity.VConview
 *  com.common.util.PageBean
 *  com.oa.container.dao.ConRecordDAO
 *  com.oa.container.dao.ConRecordDAOImpl
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.oa.container.dao;

import com.common.entity.TContainerRecord;
import com.common.entity.VConcompute;
import com.common.entity.VContainerRecord;
import com.common.entity.VConview;
import com.common.util.PageBean;
import com.oa.container.dao.ConRecordDAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ConRecordDAOImpl
extends HibernateDaoSupport
implements ConRecordDAO {
    public String getHql(VContainerRecord query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VContainerRecord");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                whereStr.append(" and cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
            if (obj.length >= 3 && obj[2] != null) {
                if (Integer.parseInt(obj[2].toString()) == 1) {
                    whereStr.append(" and cstate = 1");
                } else {
                    whereStr.append(" and cstate in (2,3)");
                }
            }
        }
        if (query != null) {
            if (query.getCusname() != null && !"".equals(query.getCusname().trim())) {
                whereStr.append(" and cusname like '%").append(query.getCusname()).append("%'");
            }
            if (query.getModelCode() != null && !"".equals(query.getModelCode().trim())) {
                whereStr.append(" and modelCode like '%").append(query.getModelCode()).append("%'");
            }
            if (query.getCstate() != null && query.getCstate() > 0) {
                whereStr.append(" and cstate = ").append(query.getCstate());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by cdate desc, recordId desc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VContainerRecord> getList(VContainerRecord query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(VContainerRecord query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TContainerRecord entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TContainerRecord getEntityById(Serializable id) {
        return (TContainerRecord)this.getHibernateTemplate().get(TContainerRecord.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_container_record where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkCode(Serializable id, String code) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TContainerRecord where CCode = '").append(code).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public List<TContainerRecord> getValidList() {
        String hql = "from TContainerRecord where CState = 1 order by CId asc";
        return this.getHibernateTemplate().find(hql);
    }

    public int checkRef(Serializable id) {
        return 0;
    }

    public String getReportHql(VConview query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VConview");
        if (query != null) {
            if (query.getInfoCode() != null && !"".equals(query.getInfoCode().trim())) {
                whereStr.append(" and infoCode like '%").append(query.getInfoCode()).append("%'");
            }
            if (query.getCOpertype() != null && query.getCOpertype() != -1) {
                whereStr.append(" and COpertype = ").append(query.getCOpertype());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by infoId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VConview> getReportList(VConview query, int pageNow, int pageSize) {
        String hql = this.getReportHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getReportPages(VConview query, int pageNow, int pageSize) {
        String hql = this.getReportHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public String getComputeHql(VConcompute query, Object[] obj, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VConcompute");
        if (obj != null && obj.length > 0) {
            if (obj[0] != null && !"".equals(obj[0].toString().trim())) {
                whereStr.append(" and cdate >= '").append(obj[0].toString()).append(" 00:00:00'");
            }
            if (obj[1] != null && !"".equals(obj[1].toString().trim())) {
                whereStr.append(" and cdate <= '").append(obj[1].toString()).append(" 23:59:59'");
            }
        }
        if (query != null) {
            if (query.getInfoCode() != null && !"".equals(query.getInfoCode().trim())) {
                whereStr.append(" and infoCode like '%").append(query.getInfoCode()).append("%'");
            }
            if (query.getCusname() != null && !"".equals(query.getCusname().trim())) {
                whereStr.append(" and cusname like '%").append(query.getCusname()).append("%'");
            }
            if (query.getEmpname() != null && !"".equals(query.getEmpname().trim())) {
                whereStr.append(" and empname like '%").append(query.getEmpname()).append("%'");
            }
            if (query.getOpertype() != null && query.getOpertype() != -1) {
                whereStr.append(" and opertype = ").append(query.getOpertype());
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by cdate desc, infoCode asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VConcompute> getComputeList(VConcompute query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getComputeHql(query, obj, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getComputePages(VConcompute query, Object[] obj, int pageNow, int pageSize) {
        String hql = this.getComputeHql(query, obj, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public double getConCost(VConcompute query, Object[] obj) {
        String sql = "select ifnull(sum(c_price),0) from t_container_info";
        return Double.parseDouble(this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult().toString());
    }

    public void updateState(int id, short state) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_container_record set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }
}


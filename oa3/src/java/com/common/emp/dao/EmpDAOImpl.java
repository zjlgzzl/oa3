/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.emp.dao.EmpDAO
 *  com.common.emp.dao.EmpDAOImpl
 *  com.common.entity.TEmp
 *  com.common.entity.VEmp
 *  com.common.util.PageBean
 *  org.springframework.orm.hibernate3.support.HibernateDaoSupport
 */
package com.common.emp.dao;

import com.common.auth.action.UserInfo;
import com.common.emp.dao.EmpDAO;
import com.common.entity.TEmp;
import com.common.entity.VEmp;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EmpDAOImpl
extends HibernateDaoSupport
implements EmpDAO {
    public String getHql(VEmp query, int flag) {
        StringBuffer hql = new StringBuffer();
        StringBuffer whereStr = new StringBuffer();
        hql.append("from VEmp");
        if (query != null) {
            if (query.getEmpCode() != null && !"".equals(query.getEmpCode().trim())) {
                whereStr.append(" and empCode like '%").append(query.getEmpCode()).append("%'");
            }
            if (query.getEmpName() != null && !"".equals(query.getEmpName().trim())) {
                whereStr.append(" and empName like '%").append(query.getEmpName()).append("%'");
            }
        }
        if (whereStr != null && !"".equals(whereStr.toString().trim())) {
            hql.append(" where ").append(whereStr.toString().substring(4));
        }
        if (flag == 1) {
            return hql.append(" order by deptId asc, empId asc").toString();
        }
        return "select count(*) " + hql.toString();
    }

    public List<VEmp> getList(VEmp query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 1);
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public PageBean getPages(VEmp query, int pageNow, int pageSize) {
        String hql = this.getHql(query, 2);
        int rowCount = Integer.parseInt("" + this.getHibernateTemplate().find(hql).get(0));
        int pageCount = (rowCount + (pageSize - 1)) / pageSize;
        PageBean pageBean = new PageBean(pageCount, pageNow);
        pageBean.setRowCount(rowCount);
        return pageBean;
    }

    public void saveEntity(TEmp entity) throws Exception {
        this.getHibernateTemplate().saveOrUpdate((Object)entity);
    }

    public TEmp getEntityById(Serializable id) {
        return (TEmp)this.getHibernateTemplate().get(TEmp.class, id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_emp where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).executeUpdate();
    }

    public int checkCode(Serializable id, String code) {
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from TEmp where CCode = '").append(code).append("'");
        if (id != null) {
            hql.append(" and CId <> ").append(id);
        }
        return Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
    }

    public int checkRef(Serializable id) {
        StringBuffer hql = new StringBuffer();
        int count = 0;
        hql.append("select count(*) from TUser where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TDept where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TEmp where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TCostItem where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TAccItem where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TCustomer where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TServiceType where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TServiceItem where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TBusin where TUser.CId = ").append(id).append(" or CLastUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TCost where TUserByCUserid.CId = ").append(id).append(" or TUserByCCreateUserid.CId = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TCash where TUserByCUserid.CId = ").append(id).append(" or TUserByCCreateUserid.CId = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TBusinLog where TUser.CId = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        hql.setLength(0);
        hql.append("select count(*) from TBusinType where CCreateUserid = ").append(id);
        count = Integer.parseInt(this.getHibernateTemplate().find(hql.toString()).iterator().next().toString());
        if (count > 0) {
            return count;
        }
        return 0;
    }

    public void updateState(Serializable id, short state) throws Exception {
        StringBuffer hql = new StringBuffer();
        hql.append("update t_emp set c_state = ").append(state).append(" where c_id = ").append(id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(hql.toString()).executeUpdate();
    }

    public List<VEmp> getValidList() {
        StringBuffer hql = new StringBuffer();
        hql.append("from VEmp where state = 1");
        if (!"admin".equals(UserInfo.getUserName())) {
            hql.append(" and deptId = " + UserInfo.getUppId());
        }
        hql.append(" order by empId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }

    public List<VEmp> getValidAllList() {
        StringBuffer hql = new StringBuffer();
        hql.append("from VEmp where state = 1");
        hql.append(" order by empId asc");
        return this.getHibernateTemplate().find(hql.toString());
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.dept.dao.DeptDAO
 *  com.common.entity.TDept
 *  com.common.util.PageBean
 */
package com.common.dept.dao;

import com.common.entity.TDept;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface DeptDAO {
    public List<TDept> getList(TDept var1, int var2, int var3);

    public PageBean getPages(TDept var1, int var2, int var3);

    public void saveEntity(TDept var1) throws Exception;

    public TDept getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkCode(Serializable var1, String var2);

    public int checkName(Serializable var1, String var2);

    public void updateEntityState(Serializable var1, short var2) throws Exception;

    public List<TDept> getValidList();

    public int checkRef(Serializable var1);
}


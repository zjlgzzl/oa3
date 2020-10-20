/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.emp.dao.EmpDAO
 *  com.common.entity.TEmp
 *  com.common.entity.VEmp
 *  com.common.util.PageBean
 */
package com.common.emp.dao;

import com.common.entity.TEmp;
import com.common.entity.VEmp;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface EmpDAO {
    public List<VEmp> getList(VEmp var1, int var2, int var3);

    public PageBean getPages(VEmp var1, int var2, int var3);

    public void saveEntity(TEmp var1) throws Exception;

    public TEmp getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkCode(Serializable var1, String var2);

    public int checkRef(Serializable var1);

    public void updateState(Serializable var1, short var2) throws Exception;

    public List<VEmp> getValidList();

    public List<VEmp> getValidAllList();
}


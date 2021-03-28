/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.dao.RoleDAO
 *  com.common.entity.TRole
 *  com.common.entity.VUserFun
 */
package com.common.auth.dao;

import com.common.entity.TRole;
import com.common.entity.VUserFun;
import java.util.List;

public interface RoleDAO {
    public List<Integer> getList(int var1);

    public List<VUserFun> getUserFunList(int var1);

    public void saveRole(TRole var1) throws Exception;

    public void deleteRole(int var1) throws Exception;
}


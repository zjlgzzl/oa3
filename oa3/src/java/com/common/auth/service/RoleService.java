/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.service.RoleService
 *  com.common.entity.TRole
 *  com.common.entity.VUserFun
 *  net.sf.json.JSONArray
 */
package com.common.auth.service;

import com.common.entity.TRole;
import com.common.entity.VUserFun;
import java.util.List;
import net.sf.json.JSONArray;

public interface RoleService {
    public List<Integer> getList(int var1);

    public JSONArray getUserFunArray(int var1);

    public List<VUserFun> getUserFunList(int var1);

    public void saveRole(TRole var1) throws Exception;

    public void deleteRole(int var1) throws Exception;
}


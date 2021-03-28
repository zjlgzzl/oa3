/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.service.UserService
 *  com.common.entity.TUser
 */
package com.common.auth.service;

import java.io.Serializable;
import java.util.List;

import com.common.entity.TUser;

public interface UserService {
    public TUser getEntityById(Serializable var1);

    public TUser findByUsername(String var1);

    public TUser findByEmpid(Serializable var1);

    public void saveEntity(TUser var1) throws Exception;

    public void deleteEntity(Serializable var1) throws Exception;

    public void updateState(Serializable var1, short var2) throws Exception;

    public int checkDup(Serializable var1, String var2);

    public int checkRef(Serializable var1);

    public void updateUserPwd(Serializable var1, String var2) throws Exception;
    
    public List<TUser> findUserList();
}


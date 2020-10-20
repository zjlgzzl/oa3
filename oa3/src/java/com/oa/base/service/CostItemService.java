/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostItem
 *  com.common.entity.TCostUser
 *  com.common.util.PageBean
 *  com.oa.base.service.CostItemService
 */
package com.oa.base.service;

import com.common.entity.TCostItem;
import com.common.entity.TCostUser;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface CostItemService {
    public List<TCostItem> getList(TCostItem var1, int var2, int var3);

    public PageBean getPages(TCostItem var1, int var2, int var3);

    public void saveEntity(TCostItem var1, Integer[] var2) throws Exception;

    public TCostItem getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(int var1, Serializable var2, String var3);

    public void updateEntityState(Serializable var1, short var2) throws Exception;

    public List<TCostItem> getValidList();

    public int checkRef(Serializable var1);

    public int checkRef2(Serializable var1);

    public List<Integer> getUserList(int var1);

    public void deleteUser(int var1) throws Exception;

    public void saveUser(TCostUser var1) throws Exception;
}


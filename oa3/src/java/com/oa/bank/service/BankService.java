/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBank
 *  com.common.util.PageBean
 *  com.oa.bank.service.BankService
 */
package com.oa.bank.service;

import com.common.entity.TBank;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface BankService {
    public List<TBank> getList(TBank var1, int var2, int var3);

    public PageBean getPages(TBank var1, int var2, int var3);

    public void saveEntity(TBank var1) throws Exception;

    public TBank getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkName(Serializable var1, String var2);

    public List<TBank> getValidList();

    public int checkRef(Serializable var1);
}


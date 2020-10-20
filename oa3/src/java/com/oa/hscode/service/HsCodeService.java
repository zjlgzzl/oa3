/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.THscode
 *  com.common.util.PageBean
 *  com.oa.hscode.service.HsCodeService
 */
package com.oa.hscode.service;

import com.common.entity.THscode;
import com.common.util.PageBean;
import java.io.Serializable;
import java.util.List;

public interface HsCodeService {
    public List<THscode> getList(THscode var1, int var2, int var3);

    public PageBean getPages(THscode var1, int var2, int var3);

    public List<THscode> getList2(THscode var1, int var2, int var3);

    public PageBean getPages2(THscode var1, int var2, int var3);

    public void saveEntity(THscode var1) throws Exception;

    public THscode getEntityById(Serializable var1);

    public void deleteEntity(Serializable var1) throws Exception;

    public int checkDup(String var1, Integer var2);
}


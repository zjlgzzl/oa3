/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCash
 *  com.oa.busin.service.CashService
 */
package com.oa.busin.service;

import com.common.entity.TCash;
import java.util.List;

public interface CashService {
    public List<TCash> getList(int var1, short var2);

    public void updateCashDetailState(List<TCash> var1, short var2) throws Exception;
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TService
 *  com.oa.busin.dao.BusinServiceDAO
 */
package com.oa.busin.dao;

import com.common.entity.TService;
import java.io.Serializable;
import java.util.List;

public interface BusinServiceDAO {
    public List<TService> getList(int var1);

    public void saveService(TService var1) throws Exception;

    public void deleteService(Serializable var1) throws Exception;

    public void deleteServiceByBusinid(Serializable var1) throws Exception;
}


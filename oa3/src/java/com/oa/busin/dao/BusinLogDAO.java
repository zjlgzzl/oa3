/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusinLog
 *  com.common.entity.VLog
 *  com.oa.busin.dao.BusinLogDAO
 */
package com.oa.busin.dao;

import com.common.entity.TBusinLog;
import com.common.entity.VLog;
import java.util.List;

public interface BusinLogDAO {
    public List<VLog> getlist(int var1);

    public void saveEntity(TBusinLog var1) throws Exception;

    public void deleteEntity(int var1) throws Exception;
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCost
 *  com.common.entity.VCostGroupSum
 *  com.oa.busin.dao.CostDAO
 */
package com.oa.busin.dao;

import com.common.entity.TCost;
import com.common.entity.VCostGroupSum;
import java.io.Serializable;
import java.util.List;

public interface CostDAO {
    public List<TCost> getList(int var1, short var2);

    public void saveCost(TCost var1) throws Exception;

    public void deleteCost(Serializable var1) throws Exception;

    public void deleteCostByBusinid(int var1) throws Exception;

    public int getFAuditCount(int var1);

    public void updateCostState(Serializable var1, short var2) throws Exception;

    public void updateCostDetailState(Serializable var1, short var2) throws Exception;

    public Object[] getCostInfo(int var1);

    public TCost getCostById(int var1);

    public List<VCostGroupSum> getCostGroupSumByBusinId(int var1);

    public VCostGroupSum getCostGroupSumByBusinId(int var1, int var2);
}


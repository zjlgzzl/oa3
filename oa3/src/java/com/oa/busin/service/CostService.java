/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCost
 *  com.common.entity.VCostGroupSum
 *  com.oa.busin.service.CostService
 */
package com.oa.busin.service;

import com.common.entity.TCost;
import com.common.entity.VCostGroupSum;
import java.util.List;

public interface CostService {
    public List<TCost> getList(int var1, short var2);

    public int getFAuditCount(int var1);

    public void updateCostDetailState(List<TCost> var1, short var2) throws Exception;

    public TCost getCostById(int var1);

    public void saveCost(TCost var1) throws Exception;

    public void saveCost(int[] var1, short var2) throws Exception;

    public void saveCostHidden(int[] var1, short var2) throws Exception;

    public void saveCostBaoxiao(int[] var1, short var2) throws Exception;

    public List<VCostGroupSum> getCostGroupSumByBusinId(int var1);

    public VCostGroupSum getCostGroupSumByBusinId(int var1, int var2);
}


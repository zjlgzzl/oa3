/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VCostGroupSum
 *  com.common.entity.VCostGroupSumId
 */
package com.common.entity;

import com.common.entity.VCostGroupSumId;
import java.io.Serializable;

public class VCostGroupSum
implements Serializable {
    private VCostGroupSumId id;

    public VCostGroupSum() {
    }

    public VCostGroupSum(VCostGroupSumId id) {
        this.id = id;
    }

    public VCostGroupSumId getId() {
        return this.id;
    }

    public void setId(VCostGroupSumId id) {
        this.id = id;
    }
}


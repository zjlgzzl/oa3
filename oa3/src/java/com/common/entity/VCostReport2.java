/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VCostReport2
 *  com.common.entity.VCostReport2Id
 */
package com.common.entity;

import com.common.entity.VCostReport2Id;
import java.io.Serializable;

public class VCostReport2
implements Serializable {
    private VCostReport2Id id;

    public VCostReport2() {
    }

    public VCostReport2(VCostReport2Id id) {
        this.id = id;
    }

    public VCostReport2Id getId() {
        return this.id;
    }

    public void setId(VCostReport2Id id) {
        this.id = id;
    }
}


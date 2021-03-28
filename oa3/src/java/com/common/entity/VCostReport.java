/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VCostReport
 *  com.common.entity.VCostReportId
 */
package com.common.entity;

import com.common.entity.VCostReportId;
import java.io.Serializable;

public class VCostReport
implements Serializable {
    private VCostReportId id;

    public VCostReport() {
    }

    public VCostReport(VCostReportId id) {
        this.id = id;
    }

    public VCostReportId getId() {
        return this.id;
    }

    public void setId(VCostReportId id) {
        this.id = id;
    }
}


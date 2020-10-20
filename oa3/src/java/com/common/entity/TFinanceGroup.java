/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinanceGroup
 */
package com.common.entity;

import java.io.Serializable;

public class TFinanceGroup
implements Serializable {
    private Integer CId;
    private String CName;
    private String CRemarks;

    public TFinanceGroup() {
    }

    public TFinanceGroup(String CName, String CRemarks) {
        this.CName = CName;
        this.CRemarks = CRemarks;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public String getCName() {
        return this.CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }
}


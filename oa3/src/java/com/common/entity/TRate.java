/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TRate
 */
package com.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TRate
implements Serializable {
    private Integer CId;
    private Double CRate;
    private String CRemarks;
    private Set TCashRates = new HashSet(0);

    public TRate() {
    }

    public TRate(Double CRate, String CRemarks, Set TCashRates) {
        this.CRate = CRate;
        this.CRemarks = CRemarks;
        this.TCashRates = TCashRates;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public Double getCRate() {
        return this.CRate;
    }

    public void setCRate(Double CRate) {
        this.CRate = CRate;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public Set getTCashRates() {
        return this.TCashRates;
    }

    public void setTCashRates(Set TCashRates) {
        this.TCashRates = TCashRates;
    }
}


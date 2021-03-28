/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinprofitcusreportId
 */
package com.common.entity;

import java.io.Serializable;

public class VBusinprofitcusreportId
implements Serializable {
    private Integer cusId;
    private String cusName;
    private Double cmoney;

    public VBusinprofitcusreportId() {
    }

    public VBusinprofitcusreportId(Integer cusId) {
        this.cusId = cusId;
    }

    public VBusinprofitcusreportId(Integer cusId, String cusName, Double cmoney) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cmoney = cmoney;
    }

    public Integer getCusId() {
        return this.cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VBusinprofitcusreportId)) {
            return false;
        }
        VBusinprofitcusreportId castOther = (VBusinprofitcusreportId)other;
        return (this.getCusId() == castOther.getCusId() || this.getCusId() != null && castOther.getCusId() != null && this.getCusId().equals(castOther.getCusId())) && (this.getCusName() == castOther.getCusName() || this.getCusName() != null && castOther.getCusName() != null && this.getCusName().equals(castOther.getCusName())) && (this.getCmoney() == castOther.getCmoney() || this.getCmoney() != null && castOther.getCmoney() != null && this.getCmoney().equals(castOther.getCmoney()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getCusId() == null ? 0 : this.getCusId().hashCode());
        result = 37 * result + (this.getCusName() == null ? 0 : this.getCusName().hashCode());
        result = 37 * result + (this.getCmoney() == null ? 0 : this.getCmoney().hashCode());
        return result;
    }
}


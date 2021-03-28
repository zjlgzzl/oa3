/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VOutReportId
 */
package com.common.entity;

import java.io.Serializable;

public class VOutReportId
implements Serializable {
    private Integer typeid;
    private String typename;
    private Double cmoney;

    public VOutReportId() {
    }

    public VOutReportId(Integer typeid) {
        this.typeid = typeid;
    }

    public VOutReportId(Integer typeid, String typename, Double cmoney) {
        this.typeid = typeid;
        this.typename = typename;
        this.cmoney = cmoney;
    }

    public Integer getTypeid() {
        return this.typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return this.typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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
        if (!(other instanceof VOutReportId)) {
            return false;
        }
        VOutReportId castOther = (VOutReportId)other;
        return (this.getTypeid() == castOther.getTypeid() || this.getTypeid() != null && castOther.getTypeid() != null && this.getTypeid().equals(castOther.getTypeid())) && (this.getTypename() == castOther.getTypename() || this.getTypename() != null && castOther.getTypename() != null && this.getTypename().equals(castOther.getTypename())) && (this.getCmoney() == castOther.getCmoney() || this.getCmoney() != null && castOther.getCmoney() != null && this.getCmoney().equals(castOther.getCmoney()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getTypeid() == null ? 0 : this.getTypeid().hashCode());
        result = 37 * result + (this.getTypename() == null ? 0 : this.getTypename().hashCode());
        result = 37 * result + (this.getCmoney() == null ? 0 : this.getCmoney().hashCode());
        return result;
    }
}


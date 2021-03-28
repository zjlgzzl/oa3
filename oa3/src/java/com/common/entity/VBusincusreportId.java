/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusincusreportId
 */
package com.common.entity;

import java.io.Serializable;

public class VBusincusreportId
implements Serializable {
    private Integer cusid;
    private String cusname;
    private String cusname2;

    public VBusincusreportId() {
    }

    public VBusincusreportId(Integer cusid, String cusname, String cusname2) {
        this.cusid = cusid;
        this.cusname = cusname;
        this.cusname2 = cusname2;
    }

    public Integer getCusid() {
        return this.cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getCusname() {
        return this.cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getCusname2() {
        return this.cusname2;
    }

    public void setCusname2(String cusname2) {
        this.cusname2 = cusname2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VBusincusreportId)) {
            return false;
        }
        VBusincusreportId castOther = (VBusincusreportId)other;
        return (this.getCusid() == castOther.getCusid() || this.getCusid() != null && castOther.getCusid() != null && this.getCusid().equals(castOther.getCusid())) && (this.getCusname() == castOther.getCusname() || this.getCusname() != null && castOther.getCusname() != null && this.getCusname().equals(castOther.getCusname())) && (this.getCusname2() == castOther.getCusname2() || this.getCusname2() != null && castOther.getCusname2() != null && this.getCusname2().equals(castOther.getCusname2()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getCusid() == null ? 0 : this.getCusid().hashCode());
        result = 37 * result + (this.getCusname() == null ? 0 : this.getCusname().hashCode());
        result = 37 * result + (this.getCusname2() == null ? 0 : this.getCusname2().hashCode());
        return result;
    }
}


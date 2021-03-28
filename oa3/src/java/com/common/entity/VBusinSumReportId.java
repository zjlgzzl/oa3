/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinSumReportId
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VBusinSumReportId
implements Serializable {
    private Integer businId;
    private String ccode;
    private Integer cusid;
    private String cusName;
    private Integer eid;
    private String ename;
    private Timestamp createdate;

    public VBusinSumReportId() {
    }

    public VBusinSumReportId(Integer businId, Integer cusid, Integer eid) {
        this.businId = businId;
        this.cusid = cusid;
        this.eid = eid;
    }

    public VBusinSumReportId(Integer businId, String ccode, Integer cusid, String cusName, Integer eid, String ename, Timestamp createdate) {
        this.businId = businId;
        this.ccode = ccode;
        this.cusid = cusid;
        this.cusName = cusName;
        this.eid = eid;
        this.ename = ename;
        this.createdate = createdate;
    }

    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public String getCcode() {
        return this.ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public Integer getCusid() {
        return this.cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Integer getEid() {
        return this.eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Timestamp getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VBusinSumReportId)) {
            return false;
        }
        VBusinSumReportId castOther = (VBusinSumReportId)other;
        return (this.getBusinId() == castOther.getBusinId() || this.getBusinId() != null && castOther.getBusinId() != null && this.getBusinId().equals(castOther.getBusinId())) && (this.getCcode() == castOther.getCcode() || this.getCcode() != null && castOther.getCcode() != null && this.getCcode().equals(castOther.getCcode())) && (this.getCusid() == castOther.getCusid() || this.getCusid() != null && castOther.getCusid() != null && this.getCusid().equals(castOther.getCusid())) && (this.getCusName() == castOther.getCusName() || this.getCusName() != null && castOther.getCusName() != null && this.getCusName().equals(castOther.getCusName())) && (this.getEid() == castOther.getEid() || this.getEid() != null && castOther.getEid() != null && this.getEid().equals(castOther.getEid())) && (this.getEname() == castOther.getEname() || this.getEname() != null && castOther.getEname() != null && this.getEname().equals(castOther.getEname())) && (this.getCreatedate() == castOther.getCreatedate() || this.getCreatedate() != null && castOther.getCreatedate() != null && this.getCreatedate().equals(castOther.getCreatedate()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getBusinId() == null ? 0 : this.getBusinId().hashCode());
        result = 37 * result + (this.getCcode() == null ? 0 : this.getCcode().hashCode());
        result = 37 * result + (this.getCusid() == null ? 0 : this.getCusid().hashCode());
        result = 37 * result + (this.getCusName() == null ? 0 : this.getCusName().hashCode());
        result = 37 * result + (this.getEid() == null ? 0 : this.getEid().hashCode());
        result = 37 * result + (this.getEname() == null ? 0 : this.getEname().hashCode());
        result = 37 * result + (this.getCreatedate() == null ? 0 : this.getCreatedate().hashCode());
        return result;
    }
}


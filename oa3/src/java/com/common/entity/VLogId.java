/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VLogId
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VLogId
implements Serializable {
    private Integer logId;
    private Integer businId;
    private Timestamp cdate;
    private String empName;
    private String cnote;
    private String cresult;

    public VLogId() {
    }

    public VLogId(Integer logId) {
        this.logId = logId;
    }

    public VLogId(Integer logId, Integer businId, Timestamp cdate, String empName, String cnote, String cresult) {
        this.logId = logId;
        this.businId = businId;
        this.cdate = cdate;
        this.empName = empName;
        this.cnote = cnote;
        this.cresult = cresult;
    }

    public Integer getLogId() {
        return this.logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public Timestamp getCdate() {
        return this.cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCnote() {
        return this.cnote;
    }

    public void setCnote(String cnote) {
        this.cnote = cnote;
    }

    public String getCresult() {
        return this.cresult;
    }

    public void setCresult(String cresult) {
        this.cresult = cresult;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VLogId)) {
            return false;
        }
        VLogId castOther = (VLogId)other;
        return (this.getLogId() == castOther.getLogId() || this.getLogId() != null && castOther.getLogId() != null && this.getLogId().equals(castOther.getLogId())) && (this.getBusinId() == castOther.getBusinId() || this.getBusinId() != null && castOther.getBusinId() != null && this.getBusinId().equals(castOther.getBusinId())) && (this.getCdate() == castOther.getCdate() || this.getCdate() != null && castOther.getCdate() != null && this.getCdate().equals(castOther.getCdate())) && (this.getEmpName() == castOther.getEmpName() || this.getEmpName() != null && castOther.getEmpName() != null && this.getEmpName().equals(castOther.getEmpName())) && (this.getCnote() == castOther.getCnote() || this.getCnote() != null && castOther.getCnote() != null && this.getCnote().equals(castOther.getCnote())) && (this.getCresult() == castOther.getCresult() || this.getCresult() != null && castOther.getCresult() != null && this.getCresult().equals(castOther.getCresult()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getLogId() == null ? 0 : this.getLogId().hashCode());
        result = 37 * result + (this.getBusinId() == null ? 0 : this.getBusinId().hashCode());
        result = 37 * result + (this.getCdate() == null ? 0 : this.getCdate().hashCode());
        result = 37 * result + (this.getEmpName() == null ? 0 : this.getEmpName().hashCode());
        result = 37 * result + (this.getCnote() == null ? 0 : this.getCnote().hashCode());
        result = 37 * result + (this.getCresult() == null ? 0 : this.getCresult().hashCode());
        return result;
    }
}


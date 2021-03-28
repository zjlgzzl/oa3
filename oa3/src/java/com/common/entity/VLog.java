/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VLog
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VLog
implements Serializable {
    private Integer logId;
    private Integer businId;
    private Timestamp cdate;
    private String empName;
    private String cnote;
    private String cresult;
    private Short cresultFlag;
    private Short newCostFlag;

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

    public Short getCresultFlag() {
        return this.cresultFlag;
    }

    public void setCresultFlag(Short cresultFlag) {
        this.cresultFlag = cresultFlag;
    }

    public Short getNewCostFlag() {
        return this.newCostFlag;
    }

    public void setNewCostFlag(Short newCostFlag) {
        this.newCostFlag = newCostFlag;
    }
}


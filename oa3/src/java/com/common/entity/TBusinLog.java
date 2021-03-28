/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusin
 *  com.common.entity.TBusinLog
 *  com.common.entity.TUser
 */
package com.common.entity;

import com.common.entity.TBusin;
import com.common.entity.TUser;
import java.io.Serializable;
import java.sql.Timestamp;

public class TBusinLog
implements Serializable {
    private Integer CId;
    private TUser TUser;
    private TBusin TBusin;
    private Timestamp CDate;
    private String CNote;
    private Short CResult;
    private Short CNewCostFlag;

    public TBusinLog() {
    }

    public TBusinLog(TUser TUser2, TBusin TBusin2, Timestamp CDate, String CNote, Short CResult, Short CNewCostFlag) {
        this.TUser = TUser2;
        this.TBusin = TBusin2;
        this.CDate = CDate;
        this.CNote = CNote;
        this.CResult = CResult;
        this.CNewCostFlag = CNewCostFlag;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TUser getTUser() {
        return this.TUser;
    }

    public void setTUser(TUser TUser2) {
        this.TUser = TUser2;
    }

    public TBusin getTBusin() {
        return this.TBusin;
    }

    public void setTBusin(TBusin TBusin2) {
        this.TBusin = TBusin2;
    }

    public Timestamp getCDate() {
        return this.CDate;
    }

    public void setCDate(Timestamp CDate) {
        this.CDate = CDate;
    }

    public String getCNote() {
        return this.CNote;
    }

    public void setCNote(String CNote) {
        this.CNote = CNote;
    }

    public Short getCResult() {
        return this.CResult;
    }

    public void setCResult(Short CResult) {
        this.CResult = CResult;
    }

    public Short getCNewCostFlag() {
        return this.CNewCostFlag;
    }

    public void setCNewCostFlag(Short CNewCostFlag) {
        this.CNewCostFlag = CNewCostFlag;
    }
}


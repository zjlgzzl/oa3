/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostGroup
 *  com.common.entity.TCostGroupInfo
 */
package com.common.entity;

import com.common.entity.TCostGroup;
import java.io.Serializable;
import java.sql.Timestamp;

public class TCostGroupInfo
implements Serializable {
    private Integer CId;
    private TCostGroup TCostGroup;
    private Integer CBusinId;
    private Timestamp CCostDate;
    private Integer CApplyBy;
    private Double CExchange;
    private String CRefNo;
    private String CCostDate_1;

    public TCostGroupInfo() {
    }

    public TCostGroupInfo(TCostGroup TCostGroup2, Integer CBusinId, Timestamp CCostDate, Integer CApplyBy, Double CExchange, String CRefNo, String CCostDate_1) {
        this.TCostGroup = TCostGroup2;
        this.CBusinId = CBusinId;
        this.CCostDate = CCostDate;
        this.CApplyBy = CApplyBy;
        this.CExchange = CExchange;
        this.CRefNo = CRefNo;
        this.CCostDate_1 = CCostDate_1;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TCostGroup getTCostGroup() {
        return this.TCostGroup;
    }

    public void setTCostGroup(TCostGroup TCostGroup2) {
        this.TCostGroup = TCostGroup2;
    }

    public Integer getCBusinId() {
        return this.CBusinId;
    }

    public void setCBusinId(Integer CBusinId) {
        this.CBusinId = CBusinId;
    }

    public Timestamp getCCostDate() {
        return this.CCostDate;
    }

    public void setCCostDate(Timestamp CCostDate) {
        this.CCostDate = CCostDate;
    }

    public Integer getCApplyBy() {
        return this.CApplyBy;
    }

    public void setCApplyBy(Integer CApplyBy) {
        this.CApplyBy = CApplyBy;
    }

    public Double getCExchange() {
        return this.CExchange;
    }

    public void setCExchange(Double CExchange) {
        this.CExchange = CExchange;
    }

    public String getCRefNo() {
        return this.CRefNo;
    }

    public void setCRefNo(String CRefNo) {
        this.CRefNo = CRefNo;
    }

    public String getCCostDate_1() {
        return this.CCostDate_1;
    }

    public void setCCostDate_1(String CCostDate_1) {
        this.CCostDate_1 = CCostDate_1;
    }
}


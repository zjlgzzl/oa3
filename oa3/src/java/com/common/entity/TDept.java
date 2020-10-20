/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TDept
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TDept
implements Serializable {
    private Integer CId;
    private String CCode;
    private String CName;
    private String CRemarks;
    private Integer CCreateUserid;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private Short CState;
    private Set TEmps = new HashSet(0);

    public TDept() {
    }

    public TDept(Short CState) {
        this.CState = CState;
    }

    public TDept(String CCode, String CName, String CRemarks, Integer CCreateUserid, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, Short CState, Set TEmps) {
        this.CCode = CCode;
        this.CName = CName;
        this.CRemarks = CRemarks;
        this.CCreateUserid = CCreateUserid;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.CState = CState;
        this.TEmps = TEmps;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public String getCCode() {
        return this.CCode;
    }

    public void setCCode(String CCode) {
        this.CCode = CCode;
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

    public Integer getCCreateUserid() {
        return this.CCreateUserid;
    }

    public void setCCreateUserid(Integer CCreateUserid) {
        this.CCreateUserid = CCreateUserid;
    }

    public Timestamp getCCreateDate() {
        return this.CCreateDate;
    }

    public void setCCreateDate(Timestamp CCreateDate) {
        this.CCreateDate = CCreateDate;
    }

    public Integer getCLastUserid() {
        return this.CLastUserid;
    }

    public void setCLastUserid(Integer CLastUserid) {
        this.CLastUserid = CLastUserid;
    }

    public Timestamp getCLastDate() {
        return this.CLastDate;
    }

    public void setCLastDate(Timestamp CLastDate) {
        this.CLastDate = CLastDate;
    }

    public Short getCState() {
        return this.CState;
    }

    public void setCState(Short CState) {
        this.CState = CState;
    }

    public Set getTEmps() {
        return this.TEmps;
    }

    public void setTEmps(Set TEmps) {
        this.TEmps = TEmps;
    }
}


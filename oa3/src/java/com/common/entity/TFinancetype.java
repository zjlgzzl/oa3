/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinancetype
 */
package com.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TFinancetype
implements Serializable {
    private Integer CId;
    private String CName;
    private Integer CCreateUserid;
    private Short CState;
    private String CRemarks;
    private Short CType;
    private Set TFinances = new HashSet(0);

    public TFinancetype() {
    }

    public TFinancetype(String CName, Integer CCreateUserid, Short CState, String CRemarks, Short CType, Set TFinances) {
        this.CName = CName;
        this.CCreateUserid = CCreateUserid;
        this.CState = CState;
        this.CRemarks = CRemarks;
        this.CType = CType;
        this.TFinances = TFinances;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public String getCName() {
        return this.CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public Integer getCCreateUserid() {
        return this.CCreateUserid;
    }

    public void setCCreateUserid(Integer CCreateUserid) {
        this.CCreateUserid = CCreateUserid;
    }

    public Short getCState() {
        return this.CState;
    }

    public void setCState(Short CState) {
        this.CState = CState;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public Short getCType() {
        return this.CType;
    }

    public void setCType(Short CType) {
        this.CType = CType;
    }

    public Set getTFinances() {
        return this.TFinances;
    }

    public void setTFinances(Set TFinances) {
        this.TFinances = TFinances;
    }
}


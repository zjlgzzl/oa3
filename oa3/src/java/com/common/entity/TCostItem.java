/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostItem
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TCostItem
implements Serializable {
    private Integer CId;
    private String CName;
    private Short CState;
    private String CRemarks;
    private Integer CCreateUserid;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private Short CType;
    private Set TFinanceGroupItems = new HashSet(0);
    private Set TCashs = new HashSet(0);
    private Set TCashRates = new HashSet(0);
    private Set TCosts = new HashSet(0);
    private Set TCostGroupItems = new HashSet(0);

    public TCostItem() {
    }

    public TCostItem(String CName, Short CState, String CRemarks, Integer CCreateUserid, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, Short CType, Set TFinanceGroupItems, Set TCashs, Set TCashRates, Set TCosts, Set TCostGroupItems) {
        this.CName = CName;
        this.CState = CState;
        this.CRemarks = CRemarks;
        this.CCreateUserid = CCreateUserid;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.CType = CType;
        this.TFinanceGroupItems = TFinanceGroupItems;
        this.TCashs = TCashs;
        this.TCashRates = TCashRates;
        this.TCosts = TCosts;
        this.TCostGroupItems = TCostGroupItems;
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

    public Short getCType() {
        return this.CType;
    }

    public void setCType(Short CType) {
        this.CType = CType;
    }

    public Set getTFinanceGroupItems() {
        return this.TFinanceGroupItems;
    }

    public void setTFinanceGroupItems(Set TFinanceGroupItems) {
        this.TFinanceGroupItems = TFinanceGroupItems;
    }

    public Set getTCashs() {
        return this.TCashs;
    }

    public void setTCashs(Set TCashs) {
        this.TCashs = TCashs;
    }

    public Set getTCashRates() {
        return this.TCashRates;
    }

    public void setTCashRates(Set TCashRates) {
        this.TCashRates = TCashRates;
    }

    public Set getTCosts() {
        return this.TCosts;
    }

    public void setTCosts(Set TCosts) {
        this.TCosts = TCosts;
    }

    public Set getTCostGroupItems() {
        return this.TCostGroupItems;
    }

    public void setTCostGroupItems(Set TCostGroupItems) {
        this.TCostGroupItems = TCostGroupItems;
    }
}


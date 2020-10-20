/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TUser
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TUser
implements Serializable {
    private Integer CId;
    private String CUsername;
    private String CPassword;
    private Integer CEmpid;
    private Short CAdminflag;
    private Integer CCreateUserid;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private Short CState;
    private Set TBusins = new HashSet(0);
    private Set TCashsForCCreateUserid = new HashSet(0);
    private Set TContainerRecords = new HashSet(0);
    private Set TCashsForCUserid = new HashSet(0);
    private Set THscodes = new HashSet(0);
    private Set TFinances = new HashSet(0);
    private Set TBusinLogs = new HashSet(0);
    private Set TCashRates = new HashSet(0);
    private Set TCostsForCCreateUserid = new HashSet(0);
    private Set TCostsForCUserid = new HashSet(0);

    public TUser() {
    }

    public TUser(String CUsername, String CPassword, Short CState) {
        this.CUsername = CUsername;
        this.CPassword = CPassword;
        this.CState = CState;
    }

    public TUser(String CUsername, String CPassword, Integer CEmpid, Short CAdminflag, Integer CCreateUserid, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, Short CState, Set TBusins, Set TCashsForCCreateUserid, Set TContainerRecords, Set TCashsForCUserid, Set THscodes, Set TFinances, Set TBusinLogs, Set TCashRates, Set TCostsForCCreateUserid, Set TCostsForCUserid) {
        this.CUsername = CUsername;
        this.CPassword = CPassword;
        this.CEmpid = CEmpid;
        this.CAdminflag = CAdminflag;
        this.CCreateUserid = CCreateUserid;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.CState = CState;
        this.TBusins = TBusins;
        this.TCashsForCCreateUserid = TCashsForCCreateUserid;
        this.TContainerRecords = TContainerRecords;
        this.TCashsForCUserid = TCashsForCUserid;
        this.THscodes = THscodes;
        this.TFinances = TFinances;
        this.TBusinLogs = TBusinLogs;
        this.TCashRates = TCashRates;
        this.TCostsForCCreateUserid = TCostsForCCreateUserid;
        this.TCostsForCUserid = TCostsForCUserid;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public String getCUsername() {
        return this.CUsername;
    }

    public void setCUsername(String CUsername) {
        this.CUsername = CUsername;
    }

    public String getCPassword() {
        return this.CPassword;
    }

    public void setCPassword(String CPassword) {
        this.CPassword = CPassword;
    }

    public Integer getCEmpid() {
        return this.CEmpid;
    }

    public void setCEmpid(Integer CEmpid) {
        this.CEmpid = CEmpid;
    }

    public Short getCAdminflag() {
        return this.CAdminflag;
    }

    public void setCAdminflag(Short CAdminflag) {
        this.CAdminflag = CAdminflag;
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

    public Set getTBusins() {
        return this.TBusins;
    }

    public void setTBusins(Set TBusins) {
        this.TBusins = TBusins;
    }

    public Set getTCashsForCCreateUserid() {
        return this.TCashsForCCreateUserid;
    }

    public void setTCashsForCCreateUserid(Set TCashsForCCreateUserid) {
        this.TCashsForCCreateUserid = TCashsForCCreateUserid;
    }

    public Set getTContainerRecords() {
        return this.TContainerRecords;
    }

    public void setTContainerRecords(Set TContainerRecords) {
        this.TContainerRecords = TContainerRecords;
    }

    public Set getTCashsForCUserid() {
        return this.TCashsForCUserid;
    }

    public void setTCashsForCUserid(Set TCashsForCUserid) {
        this.TCashsForCUserid = TCashsForCUserid;
    }

    public Set getTHscodes() {
        return this.THscodes;
    }

    public void setTHscodes(Set THscodes) {
        this.THscodes = THscodes;
    }

    public Set getTFinances() {
        return this.TFinances;
    }

    public void setTFinances(Set TFinances) {
        this.TFinances = TFinances;
    }

    public Set getTBusinLogs() {
        return this.TBusinLogs;
    }

    public void setTBusinLogs(Set TBusinLogs) {
        this.TBusinLogs = TBusinLogs;
    }

    public Set getTCashRates() {
        return this.TCashRates;
    }

    public void setTCashRates(Set TCashRates) {
        this.TCashRates = TCashRates;
    }

    public Set getTCostsForCCreateUserid() {
        return this.TCostsForCCreateUserid;
    }

    public void setTCostsForCCreateUserid(Set TCostsForCCreateUserid) {
        this.TCostsForCCreateUserid = TCostsForCCreateUserid;
    }

    public Set getTCostsForCUserid() {
        return this.TCostsForCUserid;
    }

    public void setTCostsForCUserid(Set TCostsForCUserid) {
        this.TCostsForCUserid = TCostsForCUserid;
    }
}


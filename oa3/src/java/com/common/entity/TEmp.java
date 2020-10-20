/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TDept
 *  com.common.entity.TEmp
 */
package com.common.entity;

import com.common.entity.TDept;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TEmp
implements Serializable {
    private Integer CId;
    private TDept TDept;
    private String CCode;
    private String CName;
    private String CGender;
    private String CPhone;
    private Short CManageflag;
    private String CRemarks;
    private Integer CCreateUserid;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private Short CState;
    private Set TBusins = new HashSet(0);

    public TEmp() {
    }

    public TEmp(Short CState) {
        this.CState = CState;
    }

    public TEmp(TDept TDept2, String CCode, String CName, String CGender, String CPhone, Short CManageflag, String CRemarks, Integer CCreateUserid, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, Short CState, Set TBusins) {
        this.TDept = TDept2;
        this.CCode = CCode;
        this.CName = CName;
        this.CGender = CGender;
        this.CPhone = CPhone;
        this.CManageflag = CManageflag;
        this.CRemarks = CRemarks;
        this.CCreateUserid = CCreateUserid;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.CState = CState;
        this.TBusins = TBusins;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TDept getTDept() {
        return this.TDept;
    }

    public void setTDept(TDept TDept2) {
        this.TDept = TDept2;
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

    public String getCGender() {
        return this.CGender;
    }

    public void setCGender(String CGender) {
        this.CGender = CGender;
    }

    public String getCPhone() {
        return this.CPhone;
    }

    public void setCPhone(String CPhone) {
        this.CPhone = CPhone;
    }

    public Short getCManageflag() {
        return this.CManageflag;
    }

    public void setCManageflag(Short CManageflag) {
        this.CManageflag = CManageflag;
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

    public Set getTBusins() {
        return this.TBusins;
    }

    public void setTBusins(Set TBusins) {
        this.TBusins = TBusins;
    }
}


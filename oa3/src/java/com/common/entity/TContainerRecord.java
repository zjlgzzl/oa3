/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerInfo
 *  com.common.entity.TContainerRecord
 *  com.common.entity.TUser
 */
package com.common.entity;

import com.common.entity.TContainerInfo;
import com.common.entity.TUser;
import java.io.Serializable;
import java.sql.Timestamp;

public class TContainerRecord
implements Serializable {
    private Integer CId;
    private TUser TUser;
    private TContainerInfo TContainerInfo;
    private Timestamp CDate;
    private String CCusname;
    private Short COpertype;
    private Double CMoney;
    private String CRemarks;
    private String CTo;
    private Short CState;
    private String CRemarks2;

    public TContainerRecord() {
    }

    public TContainerRecord(TContainerInfo TContainerInfo2, Short COpertype) {
        this.TContainerInfo = TContainerInfo2;
        this.COpertype = COpertype;
    }

    public TContainerRecord(TUser TUser2, TContainerInfo TContainerInfo2, Timestamp CDate, String CCusname, Short COpertype, Double CMoney, String CRemarks, String CTo, Short CState, String CRemarks2) {
        this.TUser = TUser2;
        this.TContainerInfo = TContainerInfo2;
        this.CDate = CDate;
        this.CCusname = CCusname;
        this.COpertype = COpertype;
        this.CMoney = CMoney;
        this.CRemarks = CRemarks;
        this.CTo = CTo;
        this.CState = CState;
        this.CRemarks2 = CRemarks2;
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

    public TContainerInfo getTContainerInfo() {
        return this.TContainerInfo;
    }

    public void setTContainerInfo(TContainerInfo TContainerInfo2) {
        this.TContainerInfo = TContainerInfo2;
    }

    public Timestamp getCDate() {
        return this.CDate;
    }

    public void setCDate(Timestamp CDate) {
        this.CDate = CDate;
    }

    public String getCCusname() {
        return this.CCusname;
    }

    public void setCCusname(String CCusname) {
        this.CCusname = CCusname;
    }

    public Short getCOpertype() {
        return this.COpertype;
    }

    public void setCOpertype(Short COpertype) {
        this.COpertype = COpertype;
    }

    public Double getCMoney() {
        return this.CMoney;
    }

    public void setCMoney(Double CMoney) {
        this.CMoney = CMoney;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public String getCTo() {
        return this.CTo;
    }

    public void setCTo(String CTo) {
        this.CTo = CTo;
    }

    public Short getCState() {
        return this.CState;
    }

    public void setCState(Short CState) {
        this.CState = CState;
    }

    public String getCRemarks2() {
        return this.CRemarks2;
    }

    public void setCRemarks2(String CRemarks2) {
        this.CRemarks2 = CRemarks2;
    }
}


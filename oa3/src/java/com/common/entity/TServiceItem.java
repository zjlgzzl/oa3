/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TServiceItem
 *  com.common.entity.TServiceType
 */
package com.common.entity;

import com.common.entity.TServiceType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TServiceItem
implements Serializable {
    private Integer CId;
    private TServiceType TServiceType;
    private String CName;
    private Short CState;
    private String CRemarks;
    private Integer CCreateUserid;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private Set TServices = new HashSet(0);

    public TServiceItem() {
    }

    public TServiceItem(TServiceType TServiceType2, String CName, Short CState, String CRemarks, Integer CCreateUserid, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, Set TServices) {
        this.TServiceType = TServiceType2;
        this.CName = CName;
        this.CState = CState;
        this.CRemarks = CRemarks;
        this.CCreateUserid = CCreateUserid;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.TServices = TServices;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TServiceType getTServiceType() {
        return this.TServiceType;
    }

    public void setTServiceType(TServiceType TServiceType2) {
        this.TServiceType = TServiceType2;
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

    public Set getTServices() {
        return this.TServices;
    }

    public void setTServices(Set TServices) {
        this.TServices = TServices;
    }
}


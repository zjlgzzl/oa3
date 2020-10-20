/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerInfo
 *  com.common.entity.TContainerModel
 */
package com.common.entity;

import com.common.entity.TContainerModel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TContainerInfo
implements Serializable {
    private Integer CId;
    private TContainerModel TContainerModel;
    private String CCode;
    private Double CPrice;
    private Integer CCreateUserid;
    private Short CState;
    private String CRemarks;
    private Set TContainerRecords = new HashSet(0);

    public TContainerInfo() {
    }

    public TContainerInfo(TContainerModel TContainerModel2) {
        this.TContainerModel = TContainerModel2;
    }

    public TContainerInfo(TContainerModel TContainerModel2, String CCode, Double CPrice, Integer CCreateUserid, Short CState, String CRemarks, Set TContainerRecords) {
        this.TContainerModel = TContainerModel2;
        this.CCode = CCode;
        this.CPrice = CPrice;
        this.CCreateUserid = CCreateUserid;
        this.CState = CState;
        this.CRemarks = CRemarks;
        this.TContainerRecords = TContainerRecords;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TContainerModel getTContainerModel() {
        return this.TContainerModel;
    }

    public void setTContainerModel(TContainerModel TContainerModel2) {
        this.TContainerModel = TContainerModel2;
    }

    public String getCCode() {
        return this.CCode;
    }

    public void setCCode(String CCode) {
        this.CCode = CCode;
    }

    public Double getCPrice() {
        return this.CPrice;
    }

    public void setCPrice(Double CPrice) {
        this.CPrice = CPrice;
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

    public Set getTContainerRecords() {
        return this.TContainerRecords;
    }

    public void setTContainerRecords(Set TContainerRecords) {
        this.TContainerRecords = TContainerRecords;
    }
}


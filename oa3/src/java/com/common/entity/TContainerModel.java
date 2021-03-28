/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerModel
 */
package com.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TContainerModel
implements Serializable {
    private Integer CId;
    private String CCode;
    private Integer CCreateUserid;
    private Short CState;
    private String CRemarks;
    private Set TContainerInfos = new HashSet(0);

    public TContainerModel() {
    }

    public TContainerModel(String CCode, Integer CCreateUserid, Short CState, String CRemarks, Set TContainerInfos) {
        this.CCode = CCode;
        this.CCreateUserid = CCreateUserid;
        this.CState = CState;
        this.CRemarks = CRemarks;
        this.TContainerInfos = TContainerInfos;
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

    public Set getTContainerInfos() {
        return this.TContainerInfos;
    }

    public void setTContainerInfos(Set TContainerInfos) {
        this.TContainerInfos = TContainerInfos;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostUser
 */
package com.common.entity;

import java.io.Serializable;

public class TCostUser
implements Serializable {
    private Integer CId;
    private Integer CCostid;
    private Integer CUserid;
    private Integer CType;

    public TCostUser() {
    }

    public TCostUser(Integer CCostid, Integer CUserid, Integer CType) {
        this.CCostid = CCostid;
        this.CUserid = CUserid;
        this.CType = CType;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public Integer getCCostid() {
        return this.CCostid;
    }

    public void setCCostid(Integer CCostid) {
        this.CCostid = CCostid;
    }

    public Integer getCUserid() {
        return this.CUserid;
    }

    public void setCUserid(Integer CUserid) {
        this.CUserid = CUserid;
    }

    public Integer getCType() {
        return this.CType;
    }

    public void setCType(Integer CType) {
        this.CType = CType;
    }
}


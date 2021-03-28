/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinancetypeUser
 */
package com.common.entity;

import java.io.Serializable;

public class TFinancetypeUser
implements Serializable {
    private Integer CId;
    private Integer CTypeid;
    private Integer CUserid;
    private Short CType;

    public TFinancetypeUser() {
    }

    public TFinancetypeUser(Integer CTypeid) {
        this.CTypeid = CTypeid;
    }

    public TFinancetypeUser(Integer CTypeid, Integer CUserid, Short CType) {
        this.CTypeid = CTypeid;
        this.CUserid = CUserid;
        this.CType = CType;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public Integer getCTypeid() {
        return this.CTypeid;
    }

    public void setCTypeid(Integer CTypeid) {
        this.CTypeid = CTypeid;
    }

    public Integer getCUserid() {
        return this.CUserid;
    }

    public void setCUserid(Integer CUserid) {
        this.CUserid = CUserid;
    }

    public Short getCType() {
        return this.CType;
    }

    public void setCType(Short CType) {
        this.CType = CType;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TRole
 */
package com.common.entity;

import java.io.Serializable;

public class TRole
implements Serializable {
    private Integer CId;
    private Integer CUserid;
    private Integer CRoleid;

    public TRole() {
    }

    public TRole(Integer CUserid, Integer CRoleid) {
        this.CUserid = CUserid;
        this.CRoleid = CRoleid;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public Integer getCUserid() {
        return this.CUserid;
    }

    public void setCUserid(Integer CUserid) {
        this.CUserid = CUserid;
    }

    public Integer getCRoleid() {
        return this.CRoleid;
    }

    public void setCRoleid(Integer CRoleid) {
        this.CRoleid = CRoleid;
    }
}


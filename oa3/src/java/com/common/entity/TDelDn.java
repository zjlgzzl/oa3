/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TDelDn
 */
package com.common.entity;

import java.io.Serializable;

public class TDelDn
implements Serializable {
    private Integer CId;
    private Short CType;
    private String CCode;

    public TDelDn() {
    }

    public TDelDn(Short CType, String CCode) {
        this.CType = CType;
        this.CCode = CCode;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public Short getCType() {
        return this.CType;
    }

    public void setCType(Short CType) {
        this.CType = CType;
    }

    public String getCCode() {
        return this.CCode;
    }

    public void setCCode(String CCode) {
        this.CCode = CCode;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFunction
 */
package com.common.entity;

import java.io.Serializable;

public class TFunction
implements Serializable {
    private Integer CId;
    private Integer CUppid;
    private Integer CLevel;
    private String CName;
    private String CAction;

    public TFunction() {
    }

    public TFunction(Integer CUppid) {
        this.CUppid = CUppid;
    }

    public TFunction(Integer CUppid, Integer CLevel, String CName, String CAction) {
        this.CUppid = CUppid;
        this.CLevel = CLevel;
        this.CName = CName;
        this.CAction = CAction;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public Integer getCUppid() {
        return this.CUppid;
    }

    public void setCUppid(Integer CUppid) {
        this.CUppid = CUppid;
    }

    public Integer getCLevel() {
        return this.CLevel;
    }

    public void setCLevel(Integer CLevel) {
        this.CLevel = CLevel;
    }

    public String getCName() {
        return this.CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCAction() {
        return this.CAction;
    }

    public void setCAction(String CAction) {
        this.CAction = CAction;
    }
}


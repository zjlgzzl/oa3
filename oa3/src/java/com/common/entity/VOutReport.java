/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VOutReport
 */
package com.common.entity;

import java.io.Serializable;

public class VOutReport
implements Serializable {
    private Integer typeid;
    private String typename;
    private Double cmoney;

    public Integer getTypeid() {
        return this.typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return this.typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }
}


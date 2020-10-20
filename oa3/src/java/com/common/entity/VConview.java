/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VConview
 */
package com.common.entity;

import java.io.Serializable;

public class VConview
implements Serializable {
    private Integer infoId;
    private String infoCode;
    private String modelCode;
    private Double infoPrice;
    private Integer COpertype;
    private String remarks;
    private String CTo;

    public Integer getInfoId() {
        return this.infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getInfoCode() {
        return this.infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getModelCode() {
        return this.modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Double getInfoPrice() {
        return this.infoPrice;
    }

    public void setInfoPrice(Double infoPrice) {
        this.infoPrice = infoPrice;
    }

    public Integer getCOpertype() {
        return this.COpertype;
    }

    public void setCOpertype(Integer cOpertype) {
        this.COpertype = cOpertype;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCTo() {
        return this.CTo;
    }

    public void setCTo(String cTo) {
        this.CTo = cTo;
    }
}


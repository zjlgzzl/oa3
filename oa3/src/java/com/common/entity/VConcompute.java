/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VConcompute
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VConcompute
implements Serializable {
    private Integer recordId;
    private Integer infoId;
    private String infoCode;
    private String modelCode;
    private Double infoPrice;
    private Timestamp cdate;
    private String cusname;
    private Short opertype;
    private String opertypeName;
    private Double cmoney;
    private String empname;
    private String remarks;

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

    public Timestamp getCdate() {
        return this.cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }

    public String getCusname() {
        return this.cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public Short getOpertype() {
        return this.opertype;
    }

    public void setOpertype(Short opertype) {
        this.opertype = opertype;
    }

    public String getOpertypeName() {
        return this.opertypeName;
    }

    public void setOpertypeName(String opertypeName) {
        this.opertypeName = opertypeName;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public String getEmpname() {
        return this.empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}


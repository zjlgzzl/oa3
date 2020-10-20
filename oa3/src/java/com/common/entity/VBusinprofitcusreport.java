/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinprofitcusreport
 */
package com.common.entity;

import java.io.Serializable;

public class VBusinprofitcusreport
implements Serializable {
    private Integer cusId;
    private String cusName;
    private Double cmoney;
    private Double cmoney2;
    private String remarks;
    private String remarks2;
    private String payableDate;
    private Short flag;
    private Short flag2;
    private Integer saleId;
    private String saleName;

    public Integer getCusId() {
        return this.cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public Double getCmoney2() {
        return this.cmoney2;
    }

    public void setCmoney2(Double cmoney2) {
        this.cmoney2 = cmoney2;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Short getFlag() {
        return this.flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    public Short getFlag2() {
        return this.flag2;
    }

    public void setFlag2(Short flag2) {
        this.flag2 = flag2;
    }

    public String getPayableDate() {
        return this.payableDate;
    }

    public void setPayableDate(String payableDate) {
        this.payableDate = payableDate;
    }

    public String getRemarks2() {
        return this.remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public Integer getSaleId() {
        return this.saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String getSaleName() {
        return this.saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }
}


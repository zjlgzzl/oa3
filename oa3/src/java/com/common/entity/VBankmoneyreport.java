/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBankmoneyreport
 */
package com.common.entity;

import java.io.Serializable;

public class VBankmoneyreport
implements Serializable {
    private Integer bankId;
    private String bankName;
    private Double inMoney;
    private Double outMoney1;
    private Double outMoney2;
    private Double outMoney;
    private Double cmoney;
    private Double money2;
    private Double money4;

    public Integer getBankId() {
        return this.bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getInMoney() {
        return this.inMoney;
    }

    public void setInMoney(Double inMoney) {
        this.inMoney = inMoney;
    }

    public Double getOutMoney() {
        return this.outMoney;
    }

    public void setOutMoney(Double outMoney) {
        this.outMoney = outMoney;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public Double getOutMoney1() {
        return this.outMoney1;
    }

    public void setOutMoney1(Double outMoney1) {
        this.outMoney1 = outMoney1;
    }

    public Double getOutMoney2() {
        return this.outMoney2;
    }

    public void setOutMoney2(Double outMoney2) {
        this.outMoney2 = outMoney2;
    }

    public Double getMoney2() {
        return this.money2;
    }

    public void setMoney2(Double money2) {
        this.money2 = money2;
    }

    public Double getMoney4() {
        return this.money4;
    }

    public void setMoney4(Double money4) {
        this.money4 = money4;
    }
}


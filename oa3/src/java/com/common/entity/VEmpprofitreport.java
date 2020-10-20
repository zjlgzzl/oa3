/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VEmpprofitreport
 */
package com.common.entity;

import java.io.Serializable;

public class VEmpprofitreport
implements Serializable {
    private Integer empid;
    private String empName;
    private Double costMoney;
    private Double cashMoney;
    private Double cashMoney2;
    private Double cashMoney4;
    private Double receiveMoney;
    private Double profit;
    private Double profit2;

    public Integer getEmpid() {
        return this.empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getCostMoney() {
        return this.costMoney;
    }

    public void setCostMoney(Double costMoney) {
        this.costMoney = costMoney;
    }

    public Double getCashMoney() {
        return this.cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }

    public Double getReceiveMoney() {
        return this.receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Double getProfit() {
        return this.profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getProfit2() {
        return this.profit2;
    }

    public void setProfit2(Double profit2) {
        this.profit2 = profit2;
    }

    public Double getCashMoney2() {
        return this.cashMoney2;
    }

    public void setCashMoney2(Double cashMoney2) {
        this.cashMoney2 = cashMoney2;
    }

    public Double getCashMoney4() {
        return this.cashMoney4;
    }

    public void setCashMoney4(Double cashMoney4) {
        this.cashMoney4 = cashMoney4;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VEmpprofitreportId
 */
package com.common.entity;

import java.io.Serializable;

public class VEmpprofitreportId
implements Serializable {
    private Integer empid;
    private String empName;
    private Double costMoney;
    private Double cashMoney;
    private Double receiveMoney;
    private Double profit;
    private Double profit2;

    public VEmpprofitreportId() {
    }

    public VEmpprofitreportId(Integer empid, Double costMoney, Double receiveMoney, Double profit2) {
        this.empid = empid;
        this.costMoney = costMoney;
        this.receiveMoney = receiveMoney;
        this.profit2 = profit2;
    }

    public VEmpprofitreportId(Integer empid, String empName, Double costMoney, Double cashMoney, Double receiveMoney, Double profit, Double profit2) {
        this.empid = empid;
        this.empName = empName;
        this.costMoney = costMoney;
        this.cashMoney = cashMoney;
        this.receiveMoney = receiveMoney;
        this.profit = profit;
        this.profit2 = profit2;
    }

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

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VEmpprofitreportId)) {
            return false;
        }
        VEmpprofitreportId castOther = (VEmpprofitreportId)other;
        return (this.getEmpid() == castOther.getEmpid() || this.getEmpid() != null && castOther.getEmpid() != null && this.getEmpid().equals(castOther.getEmpid())) && (this.getEmpName() == castOther.getEmpName() || this.getEmpName() != null && castOther.getEmpName() != null && this.getEmpName().equals(castOther.getEmpName())) && (this.getCostMoney() == castOther.getCostMoney() || this.getCostMoney() != null && castOther.getCostMoney() != null && this.getCostMoney().equals(castOther.getCostMoney())) && (this.getCashMoney() == castOther.getCashMoney() || this.getCashMoney() != null && castOther.getCashMoney() != null && this.getCashMoney().equals(castOther.getCashMoney())) && (this.getReceiveMoney() == castOther.getReceiveMoney() || this.getReceiveMoney() != null && castOther.getReceiveMoney() != null && this.getReceiveMoney().equals(castOther.getReceiveMoney())) && (this.getProfit() == castOther.getProfit() || this.getProfit() != null && castOther.getProfit() != null && this.getProfit().equals(castOther.getProfit())) && (this.getProfit2() == castOther.getProfit2() || this.getProfit2() != null && castOther.getProfit2() != null && this.getProfit2().equals(castOther.getProfit2()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getEmpid() == null ? 0 : this.getEmpid().hashCode());
        result = 37 * result + (this.getEmpName() == null ? 0 : this.getEmpName().hashCode());
        result = 37 * result + (this.getCostMoney() == null ? 0 : this.getCostMoney().hashCode());
        result = 37 * result + (this.getCashMoney() == null ? 0 : this.getCashMoney().hashCode());
        result = 37 * result + (this.getReceiveMoney() == null ? 0 : this.getReceiveMoney().hashCode());
        result = 37 * result + (this.getProfit() == null ? 0 : this.getProfit().hashCode());
        result = 37 * result + (this.getProfit2() == null ? 0 : this.getProfit2().hashCode());
        return result;
    }
}


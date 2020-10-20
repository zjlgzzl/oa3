/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinprofitreport
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VBusinprofitreport
implements Serializable {
    private Timestamp cdate;
    private Integer cusId;
    private String cusName;
    private String billNo;
    private Double costMoney;
    private Double cashMoney;
    private Double cashMoney2;
    private Double cashMoney4;
    private Double receiveMoney;
    private Double profit;
    private Double profit2;
    private Short rate_archive;
    private Integer businId;
    private String conNum;
    private Integer empid;
    private String empName;
    private Short complete;
    private Integer typeid;
    private String typename;
    private Double wbMoney;
    private Double truck;
    private Double em;
    private Double vat;
    private Integer salerId;
    
    private String completeStr;
    private Integer recieveFlag;
    private String recieveFlagStr;

    public Timestamp getCdate() {
        return this.cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }

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

    public String getBillNo() {
        return this.billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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

    public Short getRate_archive() {
        return this.rate_archive;
    }

    public void setRate_archive(Short rate_archive) {
        this.rate_archive = rate_archive;
    }

    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public String getConNum() {
        return this.conNum;
    }

    public void setConNum(String conNum) {
        this.conNum = conNum;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getEmpid() {
        return this.empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public Double getCashMoney2() {
        return this.cashMoney2;
    }

    public void setCashMoney2(Double cashMoney2) {
        this.cashMoney2 = cashMoney2;
    }

    public Short getComplete() {
        return this.complete;
    }

    public void setComplete(Short complete) {
        this.complete = complete;
    }

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

    public Double getWbMoney() {
        return this.wbMoney;
    }

    public void setWbMoney(Double wbMoney) {
        this.wbMoney = wbMoney;
    }

    public Double getTruck() {
        return this.truck;
    }

    public void setTruck(Double truck) {
        this.truck = truck;
    }

    public Double getEm() {
        return this.em;
    }

    public void setEm(Double em) {
        this.em = em;
    }

    public Double getVat() {
        return this.vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getCashMoney4() {
        return this.cashMoney4;
    }

    public void setCashMoney4(Double cashMoney4) {
        this.cashMoney4 = cashMoney4;
    }

	public Integer getSalerId() {
		return salerId;
	}

	public void setSalerId(Integer salerId) {
		this.salerId = salerId;
	}

	public String getCompleteStr() {
		return completeStr;
	}

	public void setCompleteStr(String completeStr) {
		this.completeStr = completeStr;
	}

	public Integer getRecieveFlag() {
		return recieveFlag;
	}

	public void setRecieveFlag(Integer recieveFlag) {
		this.recieveFlag = recieveFlag;
	}

	public String getRecieveFlagStr() {
		return recieveFlagStr;
	}

	public void setRecieveFlagStr(String recieveFlagStr) {
		this.recieveFlagStr = recieveFlagStr;
	}
    
	
}


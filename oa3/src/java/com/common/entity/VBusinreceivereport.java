/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinreceivereport
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VBusinreceivereport
implements Serializable {
    private Integer businId;
    private Integer cusId;
    private String cusName;
    private String billNo;
    private Timestamp cdate;
    private Double cmoney;
    private Double cmoney2;
    private Integer empId;
    private String empName;
    private String reim;
    private String inv;
    private Short schedule_archive;
    private Double receiveMoney1;
    private Double receiveMoney2;
    private Double receiveMoney3;
    private Double receiveMoney4;
    private Double receiveMoney;
    private String nodate5;
    private String nodate7;
    private String remarks;
    private String remarks2;
    private Timestamp cdate1;
    private Timestamp cdate2;
    private Timestamp cdate3;
    private Timestamp cdate4;
    private String cnodate8;
    private Double money1;
    private Double money2;
    private Double money4;
    private Short payComplete;
    private Timestamp lastDate2;
    private String vatno;
    private String description;
    private String qtyoftruck;
    private String qtyofdox;
    private Integer cusId2;
    private String cusName2;
    private String fty;
    private String arrivalPort;
    private Short acNotice;
    private Integer salerId;

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

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public Double getReceiveMoney() {
        return this.receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Double getCmoney2() {
        return this.cmoney2;
    }

    public void setCmoney2(Double cmoney2) {
        this.cmoney2 = cmoney2;
    }

    public Integer getEmpId() {
        return this.empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Timestamp getCdate() {
        return this.cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }

    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public String getReim() {
        return this.reim;
    }

    public void setReim(String reim) {
        this.reim = reim;
    }

    public String getInv() {
        return this.inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
    }

    public Short getSchedule_archive() {
        return this.schedule_archive;
    }

    public void setSchedule_archive(Short schedule_archive) {
        this.schedule_archive = schedule_archive;
    }

    public Double getReceiveMoney2() {
        return this.receiveMoney2;
    }

    public void setReceiveMoney2(Double receiveMoney2) {
        this.receiveMoney2 = receiveMoney2;
    }

    public String getNodate5() {
        return this.nodate5;
    }

    public void setNodate5(String nodate5) {
        this.nodate5 = nodate5;
    }

    public String getNodate7() {
        return this.nodate7;
    }

    public void setNodate7(String nodate7) {
        this.nodate7 = nodate7;
    }

    public Double getReceiveMoney1() {
        return this.receiveMoney1;
    }

    public void setReceiveMoney1(Double receiveMoney1) {
        this.receiveMoney1 = receiveMoney1;
    }

    public Double getReceiveMoney3() {
        return this.receiveMoney3;
    }

    public void setReceiveMoney3(Double receiveMoney3) {
        this.receiveMoney3 = receiveMoney3;
    }

    public Double getReceiveMoney4() {
        return this.receiveMoney4;
    }

    public void setReceiveMoney4(Double receiveMoney4) {
        this.receiveMoney4 = receiveMoney4;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getCdate1() {
        return this.cdate1;
    }

    public void setCdate1(Timestamp cdate1) {
        this.cdate1 = cdate1;
    }

    public Timestamp getCdate2() {
        return this.cdate2;
    }

    public void setCdate2(Timestamp cdate2) {
        this.cdate2 = cdate2;
    }

    public Timestamp getCdate3() {
        return this.cdate3;
    }

    public void setCdate3(Timestamp cdate3) {
        this.cdate3 = cdate3;
    }

    public Timestamp getCdate4() {
        return this.cdate4;
    }

    public void setCdate4(Timestamp cdate4) {
        this.cdate4 = cdate4;
    }

    public String getRemarks2() {
        return this.remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public String getCnodate8() {
        return this.cnodate8;
    }

    public void setCnodate8(String cnodate8) {
        this.cnodate8 = cnodate8;
    }

    public Double getMoney1() {
        return this.money1;
    }

    public void setMoney1(Double money1) {
        this.money1 = money1;
    }

    public Double getMoney2() {
        return this.money2;
    }

    public void setMoney2(Double money2) {
        this.money2 = money2;
    }

    public Short getPayComplete() {
        return this.payComplete;
    }

    public void setPayComplete(Short payComplete) {
        this.payComplete = payComplete;
    }

    public Timestamp getLastDate2() {
        return this.lastDate2;
    }

    public void setLastDate2(Timestamp lastDate2) {
        this.lastDate2 = lastDate2;
    }

    public String getVatno() {
        return this.vatno;
    }

    public void setVatno(String vatno) {
        this.vatno = vatno;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQtyoftruck() {
        return this.qtyoftruck;
    }

    public void setQtyoftruck(String qtyoftruck) {
        this.qtyoftruck = qtyoftruck;
    }

    public String getQtyofdox() {
        return this.qtyofdox;
    }

    public void setQtyofdox(String qtyofdox) {
        this.qtyofdox = qtyofdox;
    }

    public Integer getCusId2() {
        return this.cusId2;
    }

    public void setCusId2(Integer cusId2) {
        this.cusId2 = cusId2;
    }

    public String getCusName2() {
        return this.cusName2;
    }

    public void setCusName2(String cusName2) {
        this.cusName2 = cusName2;
    }

    public Double getMoney4() {
        return this.money4;
    }

    public void setMoney4(Double money4) {
        this.money4 = money4;
    }

    public String getFty() {
        return this.fty;
    }

    public void setFty(String fty) {
        this.fty = fty;
    }

    public String getArrivalPort() {
        return this.arrivalPort;
    }

    public void setArrivalPort(String arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public Short getAcNotice() {
        return this.acNotice;
    }

    public void setAcNotice(Short acNotice) {
        this.acNotice = acNotice;
    }

	public Integer getSalerId() {
		return salerId;
	}

	public void setSalerId(Integer salerId) {
		this.salerId = salerId;
	}
    
}


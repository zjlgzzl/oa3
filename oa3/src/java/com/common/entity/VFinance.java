/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VFinance
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VFinance
implements Serializable {
    private Integer cid;
    private Timestamp cdate;
    private String billNo;
    private String financeNo;
    private String cpay;
    private Short isCash;
    private String isCashName;
    private String bankName;
    private String empName;
    private Double cmoney;
    private Short ctype;
    private Integer userId;
    private Short cstate;
    private String stateName;
    private Integer ftype;
    private String ftypeName;
    private String description;
    private String checkNo;
    private String remarks;
    private Short archivingFlag;
    private String archivingName;
    private String remarks2;

    public Integer getCid() {
        return this.cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Timestamp getCdate() {
        return this.cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }

    public String getBillNo() {
        return this.billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getFinanceNo() {
        return this.financeNo;
    }

    public void setFinanceNo(String financeNo) {
        this.financeNo = financeNo;
    }

    public String getCpay() {
        return this.cpay;
    }

    public void setCpay(String cpay) {
        this.cpay = cpay;
    }

    public Short getIsCash() {
        return this.isCash;
    }

    public void setIsCash(Short isCash) {
        this.isCash = isCash;
    }

    public String getIsCashName() {
        return this.isCashName;
    }

    public void setIsCashName(String isCashName) {
        this.isCashName = isCashName;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public Short getCtype() {
        return this.ctype;
    }

    public void setCtype(Short ctype) {
        this.ctype = ctype;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Short getCstate() {
        return this.cstate;
    }

    public void setCstate(Short cstate) {
        this.cstate = cstate;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getFtype() {
        return this.ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public String getFtypeName() {
        return this.ftypeName;
    }

    public void setFtypeName(String ftypeName) {
        this.ftypeName = ftypeName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheckNo() {
        return this.checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Short getArchivingFlag() {
        return this.archivingFlag;
    }

    public void setArchivingFlag(Short archivingFlag) {
        this.archivingFlag = archivingFlag;
    }

    public String getArchivingName() {
        return this.archivingName;
    }

    public void setArchivingName(String archivingName) {
        this.archivingName = archivingName;
    }

    public String getRemarks2() {
        return this.remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }
}


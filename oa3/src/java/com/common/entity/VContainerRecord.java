/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VContainerRecord
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VContainerRecord
implements Serializable {
    private Integer recordId;
    private Integer infoId;
    private String infoCode;
    private Double infoPrice;
    private Integer modelId;
    private String modelCode;
    private Timestamp cdate;
    private String cusname;
    private Short operType;
    private String operName;
    private Double cmoney;
    private Integer userId;
    private String username;
    private Integer empId;
    private String empName;
    private String conTo;
    private Short cstate;
    private String stateName;

    public Integer getRecordId() {
        return this.recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

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

    public Double getInfoPrice() {
        return this.infoPrice;
    }

    public void setInfoPrice(Double infoPrice) {
        this.infoPrice = infoPrice;
    }

    public Integer getModelId() {
        return this.modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelCode() {
        return this.modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
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

    public Short getOperType() {
        return this.operType;
    }

    public void setOperType(Short operType) {
        this.operType = operType;
    }

    public String getOperName() {
        return this.operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getConTo() {
        return this.conTo;
    }

    public void setConTo(String conTo) {
        this.conTo = conTo;
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
}


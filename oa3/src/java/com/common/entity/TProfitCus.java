/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCustomer
 *  com.common.entity.TProfitCus
 */
package com.common.entity;

import com.common.entity.TCustomer;
import java.io.Serializable;
import java.sql.Timestamp;

public class TProfitCus
implements Serializable {
    private Integer CId;
    private TCustomer TCustomer;
    private String CRemarks;
    private Short CFlag;
    private Timestamp CPayableDate;
    private String CRemarks2;
    private Integer CSaleId;

    public TProfitCus() {
    }

    public TProfitCus(TCustomer TCustomer2, String CRemarks) {
        this.TCustomer = TCustomer2;
        this.CRemarks = CRemarks;
    }

    public TProfitCus(TCustomer TCustomer2, String CRemarks, Short CFlag, Timestamp CPayableDate, String CRemarks2, Integer CSaleId) {
        this.TCustomer = TCustomer2;
        this.CRemarks = CRemarks;
        this.CFlag = CFlag;
        this.CPayableDate = CPayableDate;
        this.CRemarks2 = CRemarks2;
        this.CSaleId = CSaleId;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TCustomer getTCustomer() {
        return this.TCustomer;
    }

    public void setTCustomer(TCustomer TCustomer2) {
        this.TCustomer = TCustomer2;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public Short getCFlag() {
        return this.CFlag;
    }

    public void setCFlag(Short CFlag) {
        this.CFlag = CFlag;
    }

    public Timestamp getCPayableDate() {
        return this.CPayableDate;
    }

    public void setCPayableDate(Timestamp CPayableDate) {
        this.CPayableDate = CPayableDate;
    }

    public String getCRemarks2() {
        return this.CRemarks2;
    }

    public void setCRemarks2(String CRemarks2) {
        this.CRemarks2 = CRemarks2;
    }

    public Integer getCSaleId() {
        return this.CSaleId;
    }

    public void setCSaleId(Integer CSaleId) {
        this.CSaleId = CSaleId;
    }
}


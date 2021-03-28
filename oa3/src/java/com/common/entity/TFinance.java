/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBank
 *  com.common.entity.TFinance
 *  com.common.entity.TFinancetype
 *  com.common.entity.TUser
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TFinance
implements Serializable {
    private Integer CId;
    private TUser TUser;
    private TBank TBank;
    private TFinancetype TFinancetype;
    private Short CType;
    private Short CIsCash;
    private Timestamp CDate;
    private String CBillNo;
    private String CFinanceNo;
    private String CPay;
    private String CCheckNo;
    private Short CState;
    private String CRemarks;
    private String CDescription;
    private Short CArchivingFlag;
    private String CRemarks2;
    private Set TFinanceDetails = new HashSet(0);
    private TCustomer cus;

    public TFinance() {
    }

    public TFinance(Short CType) {
        this.CType = CType;
    }

    public TFinance(TUser TUser2, TBank TBank2, TFinancetype TFinancetype2, Short CType, Short CIsCash, Timestamp CDate, String CBillNo, String CFinanceNo, String CPay, String CCheckNo, Short CState, String CRemarks, String CDescription, Short CArchivingFlag, String CRemarks2, Set TFinanceDetails) {
        this.TUser = TUser2;
        this.TBank = TBank2;
        this.TFinancetype = TFinancetype2;
        this.CType = CType;
        this.CIsCash = CIsCash;
        this.CDate = CDate;
        this.CBillNo = CBillNo;
        this.CFinanceNo = CFinanceNo;
        this.CPay = CPay;
        this.CCheckNo = CCheckNo;
        this.CState = CState;
        this.CRemarks = CRemarks;
        this.CDescription = CDescription;
        this.CArchivingFlag = CArchivingFlag;
        this.CRemarks2 = CRemarks2;
        this.TFinanceDetails = TFinanceDetails;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TUser getTUser() {
        return this.TUser;
    }

    public void setTUser(TUser TUser2) {
        this.TUser = TUser2;
    }

    public TBank getTBank() {
        return this.TBank;
    }

    public void setTBank(TBank TBank2) {
        this.TBank = TBank2;
    }

    public TFinancetype getTFinancetype() {
        return this.TFinancetype;
    }

    public void setTFinancetype(TFinancetype TFinancetype2) {
        this.TFinancetype = TFinancetype2;
    }

    public Short getCType() {
        return this.CType;
    }

    public void setCType(Short CType) {
        this.CType = CType;
    }

    public Short getCIsCash() {
        return this.CIsCash;
    }

    public void setCIsCash(Short CIsCash) {
        this.CIsCash = CIsCash;
    }

    public Timestamp getCDate() {
        return this.CDate;
    }

    public void setCDate(Timestamp CDate) {
        this.CDate = CDate;
    }

    public String getCBillNo() {
        return this.CBillNo;
    }

    public void setCBillNo(String CBillNo) {
        this.CBillNo = CBillNo;
    }

    public String getCFinanceNo() {
        return this.CFinanceNo;
    }

    public void setCFinanceNo(String CFinanceNo) {
        this.CFinanceNo = CFinanceNo;
    }

    public String getCPay() {
        return this.CPay;
    }

    public void setCPay(String CPay) {
        this.CPay = CPay;
    }

    public String getCCheckNo() {
        return this.CCheckNo;
    }

    public void setCCheckNo(String CCheckNo) {
        this.CCheckNo = CCheckNo;
    }

    public Short getCState() {
        return this.CState;
    }

    public void setCState(Short CState) {
        this.CState = CState;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public String getCDescription() {
        return this.CDescription;
    }

    public void setCDescription(String CDescription) {
        this.CDescription = CDescription;
    }

    public Short getCArchivingFlag() {
        return this.CArchivingFlag;
    }

    public void setCArchivingFlag(Short CArchivingFlag) {
        this.CArchivingFlag = CArchivingFlag;
    }

    public String getCRemarks2() {
        return this.CRemarks2;
    }

    public void setCRemarks2(String CRemarks2) {
        this.CRemarks2 = CRemarks2;
    }

    public Set getTFinanceDetails() {
        return this.TFinanceDetails;
    }

    public void setTFinanceDetails(Set TFinanceDetails) {
        this.TFinanceDetails = TFinanceDetails;
    }

	public TCustomer getCus() {
		return cus;
	}

	public void setCus(TCustomer cus) {
		this.cus = cus;
	}
    
}


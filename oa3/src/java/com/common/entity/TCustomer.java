/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCustomer
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TCustomer
implements Serializable {
    private Integer CId;
    private String CName;
    private Short CState;
    private String CRemarks;
    private Integer CCreateUserid;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private String CAddr;
    private String CPhone;
    private String COther1;
    private String COther4;
    private String COther5;
    private String COther6;
    private String COther7;
    private String COther8;
    private Short CFlag;
    private Short CBlack;
    private Set TBusinsForCCusid2 = new HashSet(0);
    private Set TBusinsForCCusid = new HashSet(0);
    private Set TProfitCuses = new HashSet(0);
    private TSaler saler;
    private Integer salerId;
    private String startDate;
    private String endDate;
    public TCustomer() {
    }

    public TCustomer(String CName, Short CState, String CRemarks, Integer CCreateUserid, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, String CAddr, String CPhone, String COther1, String COther4, String COther5, String COther6, String COther7, String COther8, Short CFlag, Short CBlack, Set TBusinsForCCusid2, Set TBusinsForCCusid, Set TProfitCuses) {
        this.CName = CName;
        this.CState = CState;
        this.CRemarks = CRemarks;
        this.CCreateUserid = CCreateUserid;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.CAddr = CAddr;
        this.CPhone = CPhone;
        this.COther1 = COther1;
        this.COther4 = COther4;
        this.COther5 = COther5;
        this.COther6 = COther6;
        this.COther7 = COther7;
        this.COther8 = COther8;
        this.CFlag = CFlag;
        this.CBlack = CBlack;
        this.TBusinsForCCusid2 = TBusinsForCCusid2;
        this.TBusinsForCCusid = TBusinsForCCusid;
        this.TProfitCuses = TProfitCuses;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public String getCName() {
        return this.CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
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

    public Integer getCCreateUserid() {
        return this.CCreateUserid;
    }

    public void setCCreateUserid(Integer CCreateUserid) {
        this.CCreateUserid = CCreateUserid;
    }

    public Timestamp getCCreateDate() {
        return this.CCreateDate;
    }

    public void setCCreateDate(Timestamp CCreateDate) {
        this.CCreateDate = CCreateDate;
    }

    public Integer getCLastUserid() {
        return this.CLastUserid;
    }

    public void setCLastUserid(Integer CLastUserid) {
        this.CLastUserid = CLastUserid;
    }

    public Timestamp getCLastDate() {
        return this.CLastDate;
    }

    public void setCLastDate(Timestamp CLastDate) {
        this.CLastDate = CLastDate;
    }

    public String getCAddr() {
        return this.CAddr;
    }

    public void setCAddr(String CAddr) {
        this.CAddr = CAddr;
    }

    public String getCPhone() {
        return this.CPhone;
    }

    public void setCPhone(String CPhone) {
        this.CPhone = CPhone;
    }

    public String getCOther1() {
        return this.COther1;
    }

    public void setCOther1(String COther1) {
        this.COther1 = COther1;
    }

    public String getCOther4() {
        return this.COther4;
    }

    public void setCOther4(String COther4) {
        this.COther4 = COther4;
    }

    public String getCOther5() {
        return this.COther5;
    }

    public void setCOther5(String COther5) {
        this.COther5 = COther5;
    }

    public String getCOther6() {
        return this.COther6;
    }

    public void setCOther6(String COther6) {
        this.COther6 = COther6;
    }

    public String getCOther7() {
        return this.COther7;
    }

    public void setCOther7(String COther7) {
        this.COther7 = COther7;
    }

    public String getCOther8() {
        return this.COther8;
    }

    public void setCOther8(String COther8) {
        this.COther8 = COther8;
    }

    public Short getCFlag() {
        return this.CFlag;
    }

    public void setCFlag(Short CFlag) {
        this.CFlag = CFlag;
    }

    public Short getCBlack() {
        return this.CBlack;
    }

    public void setCBlack(Short CBlack) {
        this.CBlack = CBlack;
    }

    public Set getTBusinsForCCusid2() {
        return this.TBusinsForCCusid2;
    }

    public void setTBusinsForCCusid2(Set TBusinsForCCusid2) {
        this.TBusinsForCCusid2 = TBusinsForCCusid2;
    }

    public Set getTBusinsForCCusid() {
        return this.TBusinsForCCusid;
    }

    public void setTBusinsForCCusid(Set TBusinsForCCusid) {
        this.TBusinsForCCusid = TBusinsForCCusid;
    }

    public Set getTProfitCuses() {
        return this.TProfitCuses;
    }

    public void setTProfitCuses(Set TProfitCuses) {
        this.TProfitCuses = TProfitCuses;
    }

	public TSaler getSaler() {
		return saler;
	}

	public void setSaler(TSaler saler) {
		this.saler = saler;
	}

	public Integer getSalerId() {
		return salerId;
	}

	public void setSalerId(Integer salerId) {
		this.salerId = salerId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
}


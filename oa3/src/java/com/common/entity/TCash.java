/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusin
 *  com.common.entity.TCash
 *  com.common.entity.TCostItem
 *  com.common.entity.TUser
 */
package com.common.entity;

import com.common.entity.TBusin;
import com.common.entity.TCostItem;
import com.common.entity.TUser;
import java.io.Serializable;
import java.sql.Timestamp;

public class TCash
implements Serializable {
    private Integer CId;
    private TUser TUserByCUserid;
    private TBusin TBusin;
    private TUser TUserByCCreateUserid;
    private TCostItem TCostItem;
    private Double CMoney;
    private String CRemarks;
    private Short CState;
    private Short CSchedule;
    private Double CCount;
    private Short CType;
    private Integer COrder;
    private Timestamp CCreatedate;

    public TCash() {
    }

    public TCash(TUser TUserByCUserid, TBusin TBusin2, TUser TUserByCCreateUserid, TCostItem TCostItem2, Double CMoney, String CRemarks, Short CState, Short CSchedule, Double CCount, Short CType, Integer COrder, Timestamp CCreatedate) {
        this.TUserByCUserid = TUserByCUserid;
        this.TBusin = TBusin2;
        this.TUserByCCreateUserid = TUserByCCreateUserid;
        this.TCostItem = TCostItem2;
        this.CMoney = CMoney;
        this.CRemarks = CRemarks;
        this.CState = CState;
        this.CSchedule = CSchedule;
        this.CCount = CCount;
        this.CType = CType;
        this.COrder = COrder;
        this.CCreatedate = CCreatedate;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TUser getTUserByCUserid() {
        return this.TUserByCUserid;
    }

    public void setTUserByCUserid(TUser TUserByCUserid) {
        this.TUserByCUserid = TUserByCUserid;
    }

    public TBusin getTBusin() {
        return this.TBusin;
    }

    public void setTBusin(TBusin TBusin2) {
        this.TBusin = TBusin2;
    }

    public TUser getTUserByCCreateUserid() {
        return this.TUserByCCreateUserid;
    }

    public void setTUserByCCreateUserid(TUser TUserByCCreateUserid) {
        this.TUserByCCreateUserid = TUserByCCreateUserid;
    }

    public TCostItem getTCostItem() {
        return this.TCostItem;
    }

    public void setTCostItem(TCostItem TCostItem2) {
        this.TCostItem = TCostItem2;
    }

    public Double getCMoney() {
        return this.CMoney;
    }

    public void setCMoney(Double CMoney) {
        this.CMoney = CMoney;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public Short getCState() {
        return this.CState;
    }

    public void setCState(Short CState) {
        this.CState = CState;
    }

    public Short getCSchedule() {
        return this.CSchedule;
    }

    public void setCSchedule(Short CSchedule) {
        this.CSchedule = CSchedule;
    }

    public Double getCCount() {
        return this.CCount;
    }

    public void setCCount(Double CCount) {
        this.CCount = CCount;
    }

    public Short getCType() {
        return this.CType;
    }

    public void setCType(Short CType) {
        this.CType = CType;
    }

    public Integer getCOrder() {
        return this.COrder;
    }

    public void setCOrder(Integer COrder) {
        this.COrder = COrder;
    }

    public Timestamp getCCreatedate() {
        return this.CCreatedate;
    }

    public void setCCreatedate(Timestamp CCreatedate) {
        this.CCreatedate = CCreatedate;
    }
}


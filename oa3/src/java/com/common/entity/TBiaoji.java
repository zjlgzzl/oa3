/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBiaoji
 *  com.common.entity.TBusin
 *  com.common.entity.TCostGroup
 */
package com.common.entity;

import com.common.entity.TBusin;
import com.common.entity.TCostGroup;
import java.io.Serializable;
import java.sql.Timestamp;

public class TBiaoji
implements Serializable {
    private Integer CId;
    private TBusin TBusin;
    private TCostGroup TCostGroup;
    private Double CMoney;
    private Double CMoney2;
    private Short CNotice;
    private Timestamp CDate;

    public TBiaoji() {
    }

    public TBiaoji(TBusin TBusin2, TCostGroup TCostGroup2, Double CMoney, Double CMoney2, Short CNotice, Timestamp CDate) {
        this.TBusin = TBusin2;
        this.TCostGroup = TCostGroup2;
        this.CMoney = CMoney;
        this.CMoney2 = CMoney2;
        this.CNotice = CNotice;
        this.CDate = CDate;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TBusin getTBusin() {
        return this.TBusin;
    }

    public void setTBusin(TBusin TBusin2) {
        this.TBusin = TBusin2;
    }

    public TCostGroup getTCostGroup() {
        return this.TCostGroup;
    }

    public void setTCostGroup(TCostGroup TCostGroup2) {
        this.TCostGroup = TCostGroup2;
    }

    public Double getCMoney() {
        return this.CMoney;
    }

    public void setCMoney(Double CMoney) {
        this.CMoney = CMoney;
    }

    public Double getCMoney2() {
        return this.CMoney2;
    }

    public void setCMoney2(Double CMoney2) {
        this.CMoney2 = CMoney2;
    }

    public Short getCNotice() {
        return this.CNotice;
    }

    public void setCNotice(Short CNotice) {
        this.CNotice = CNotice;
    }

    public Timestamp getCDate() {
        return this.CDate;
    }

    public void setCDate(Timestamp CDate) {
        this.CDate = CDate;
    }
}


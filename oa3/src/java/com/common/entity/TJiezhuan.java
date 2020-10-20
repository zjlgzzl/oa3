/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusin
 *  com.common.entity.TJiezhuan
 */
package com.common.entity;

import com.common.entity.TBusin;
import java.io.Serializable;
import java.sql.Timestamp;

public class TJiezhuan
implements Serializable {
    private Integer CId;
    private TBusin TBusin;
    private Double CMoney;
    private Short CNotice;
    private Timestamp CDate;
    private Double CMoney2;

    public TJiezhuan() {
    }

    public TJiezhuan(TBusin TBusin2, Double CMoney, Short CNotice, Timestamp CDate, Double CMoney2) {
        this.TBusin = TBusin2;
        this.CMoney = CMoney;
        this.CNotice = CNotice;
        this.CDate = CDate;
        this.CMoney2 = CMoney2;
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

    public Double getCMoney() {
        return this.CMoney;
    }

    public void setCMoney(Double CMoney) {
        this.CMoney = CMoney;
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

    public Double getCMoney2() {
        return this.CMoney2;
    }

    public void setCMoney2(Double CMoney2) {
        this.CMoney2 = CMoney2;
    }
}


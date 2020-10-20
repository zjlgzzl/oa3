/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinance
 *  com.common.entity.TFinanceDetail
 */
package com.common.entity;

import com.common.entity.TFinance;
import java.io.Serializable;

public class TFinanceDetail
implements Serializable {
    private Integer CId;
    private TFinance TFinance;
    private String CItem;
    private Double CMoney;
    private String CRemarks;
    private Short CNotice;

    public TFinanceDetail() {
    }

    public TFinanceDetail(TFinance TFinance2) {
        this.TFinance = TFinance2;
    }

    public TFinanceDetail(TFinance TFinance2, String CItem, Double CMoney, String CRemarks, Short CNotice) {
        this.TFinance = TFinance2;
        this.CItem = CItem;
        this.CMoney = CMoney;
        this.CRemarks = CRemarks;
        this.CNotice = CNotice;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TFinance getTFinance() {
        return this.TFinance;
    }

    public void setTFinance(TFinance TFinance2) {
        this.TFinance = TFinance2;
    }

    public String getCItem() {
        return this.CItem;
    }

    public void setCItem(String CItem) {
        this.CItem = CItem;
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

    public Short getCNotice() {
        return this.CNotice;
    }

    public void setCNotice(Short CNotice) {
        this.CNotice = CNotice;
    }
}


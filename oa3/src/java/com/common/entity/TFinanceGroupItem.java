/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostItem
 *  com.common.entity.TFinanceGroupItem
 */
package com.common.entity;

import com.common.entity.TCostItem;
import java.io.Serializable;

public class TFinanceGroupItem
implements Serializable {
    private Integer CId;
    private TCostItem TCostItem;
    private Integer CPid;

    public TFinanceGroupItem() {
    }

    public TFinanceGroupItem(TCostItem TCostItem2, Integer CPid) {
        this.TCostItem = TCostItem2;
        this.CPid = CPid;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TCostItem getTCostItem() {
        return this.TCostItem;
    }

    public void setTCostItem(TCostItem TCostItem2) {
        this.TCostItem = TCostItem2;
    }

    public Integer getCPid() {
        return this.CPid;
    }

    public void setCPid(Integer CPid) {
        this.CPid = CPid;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostGroupItem
 *  com.common.entity.TCostItem
 */
package com.common.entity;

import com.common.entity.TCostItem;
import java.io.Serializable;

public class TCostGroupItem
implements Serializable {
    private Integer CId;
    private TCostItem TCostItem;
    private Integer CPid;
    private Integer COrder;

    public TCostGroupItem() {
    }

    public TCostGroupItem(TCostItem TCostItem2, Integer CPid, Integer COrder) {
        this.TCostItem = TCostItem2;
        this.CPid = CPid;
        this.COrder = COrder;
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

    public Integer getCOrder() {
        return this.COrder;
    }

    public void setCOrder(Integer COrder) {
        this.COrder = COrder;
    }
}


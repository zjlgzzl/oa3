/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusin
 *  com.common.entity.TService
 *  com.common.entity.TServiceItem
 *  com.common.entity.TServiceType
 */
package com.common.entity;

import com.common.entity.TBusin;
import com.common.entity.TServiceItem;
import com.common.entity.TServiceType;
import java.io.Serializable;

public class TService
implements Serializable {
    private Integer CId;
    private TBusin TBusin;
    private TServiceType TServiceType;
    private TServiceItem TServiceItem;
    private String CCount;
    private String CWeight;

    public TService() {
    }

    public TService(TBusin TBusin2, TServiceType TServiceType2, TServiceItem TServiceItem2, String CCount, String CWeight) {
        this.TBusin = TBusin2;
        this.TServiceType = TServiceType2;
        this.TServiceItem = TServiceItem2;
        this.CCount = CCount;
        this.CWeight = CWeight;
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

    public TServiceType getTServiceType() {
        return this.TServiceType;
    }

    public void setTServiceType(TServiceType TServiceType2) {
        this.TServiceType = TServiceType2;
    }

    public TServiceItem getTServiceItem() {
        return this.TServiceItem;
    }

    public void setTServiceItem(TServiceItem TServiceItem2) {
        this.TServiceItem = TServiceItem2;
    }

    public String getCCount() {
        return this.CCount;
    }

    public void setCCount(String CCount) {
        this.CCount = CCount;
    }

    public String getCWeight() {
        return this.CWeight;
    }

    public void setCWeight(String CWeight) {
        this.CWeight = CWeight;
    }
}


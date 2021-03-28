/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusin
 *  com.common.entity.TContainer
 */
package com.common.entity;

import com.common.entity.TBusin;
import java.io.Serializable;

public class TContainer
implements Serializable {
    private Integer CId;
    private TBusin TBusin;
    private String CContainerNum;
    private String CContainerType;
    private String CCount;
    private String CWeight;
    private String CRemarks;
    private String CTrucker;
    private String CRemarks2;

    public TContainer() {
    }

    public TContainer(TBusin TBusin2, String CContainerNum, String CContainerType, String CCount, String CWeight, String CRemarks, String CTrucker, String CRemarks2) {
        this.TBusin = TBusin2;
        this.CContainerNum = CContainerNum;
        this.CContainerType = CContainerType;
        this.CCount = CCount;
        this.CWeight = CWeight;
        this.CRemarks = CRemarks;
        this.CTrucker = CTrucker;
        this.CRemarks2 = CRemarks2;
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

    public String getCContainerNum() {
        return this.CContainerNum;
    }

    public void setCContainerNum(String CContainerNum) {
        this.CContainerNum = CContainerNum;
    }

    public String getCContainerType() {
        return this.CContainerType;
    }

    public void setCContainerType(String CContainerType) {
        this.CContainerType = CContainerType;
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

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public String getCTrucker() {
        return this.CTrucker;
    }

    public void setCTrucker(String CTrucker) {
        this.CTrucker = CTrucker;
    }

    public String getCRemarks2() {
        return this.CRemarks2;
    }

    public void setCRemarks2(String CRemarks2) {
        this.CRemarks2 = CRemarks2;
    }
}


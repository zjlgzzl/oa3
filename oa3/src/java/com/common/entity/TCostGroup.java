/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCostGroup
 */
package com.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TCostGroup
implements Serializable {
    private Integer CId;
    private String CName;
    private String CRemarks;
    private Set TCosts = new HashSet(0);
    private Set TBiaojis = new HashSet(0);
    private Set TCostGroupInfos = new HashSet(0);

    public TCostGroup() {
    }

    public TCostGroup(String CName, String CRemarks, Set TCosts, Set TBiaojis, Set TCostGroupInfos) {
        this.CName = CName;
        this.CRemarks = CRemarks;
        this.TCosts = TCosts;
        this.TBiaojis = TBiaojis;
        this.TCostGroupInfos = TCostGroupInfos;
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

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public Set getTCosts() {
        return this.TCosts;
    }

    public void setTCosts(Set TCosts) {
        this.TCosts = TCosts;
    }

    public Set getTBiaojis() {
        return this.TBiaojis;
    }

    public void setTBiaojis(Set TBiaojis) {
        this.TBiaojis = TBiaojis;
    }

    public Set getTCostGroupInfos() {
        return this.TCostGroupInfos;
    }

    public void setTCostGroupInfos(Set TCostGroupInfos) {
        this.TCostGroupInfos = TCostGroupInfos;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TSaler
 */
package com.common.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TSaler
implements Serializable {
    private Integer CId;
    private String CName;
    private Set TBusinsForCSaleman = new HashSet(0);
    private Set TBusinsForCKf = new HashSet(0);
    private Set TBusinsForCApplyby = new HashSet(0);
    private TUser user;

    public TSaler() {
    }

    public TSaler(String CName, Set TBusinsForCSaleman, Set TBusinsForCKf, Set TBusinsForCApplyby) {
        this.CName = CName;
        this.TBusinsForCSaleman = TBusinsForCSaleman;
        this.TBusinsForCKf = TBusinsForCKf;
        this.TBusinsForCApplyby = TBusinsForCApplyby;
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

    public Set getTBusinsForCSaleman() {
        return this.TBusinsForCSaleman;
    }

    public void setTBusinsForCSaleman(Set TBusinsForCSaleman) {
        this.TBusinsForCSaleman = TBusinsForCSaleman;
    }

    public Set getTBusinsForCKf() {
        return this.TBusinsForCKf;
    }

    public void setTBusinsForCKf(Set TBusinsForCKf) {
        this.TBusinsForCKf = TBusinsForCKf;
    }

    public Set getTBusinsForCApplyby() {
        return this.TBusinsForCApplyby;
    }

    public void setTBusinsForCApplyby(Set TBusinsForCApplyby) {
        this.TBusinsForCApplyby = TBusinsForCApplyby;
    }

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}
    
}


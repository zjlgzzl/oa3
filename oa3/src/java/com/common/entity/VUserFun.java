/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VUserFun
 */
package com.common.entity;

import java.io.Serializable;

public class VUserFun
implements Serializable {
    private Integer userid;
    private String username;
    private Integer funid;
    private Integer uppid;
    private Integer clevel;
    private String funname;
    private String funaction;
    private Integer ordernum;

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFunid() {
        return this.funid;
    }

    public void setFunid(Integer funid) {
        this.funid = funid;
    }

    public Integer getUppid() {
        return this.uppid;
    }

    public void setUppid(Integer uppid) {
        this.uppid = uppid;
    }

    public String getFunname() {
        return this.funname;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public String getFunaction() {
        return this.funaction;
    }

    public void setFunaction(String funaction) {
        this.funaction = funaction;
    }

    public Integer getClevel() {
        return this.clevel;
    }

    public void setClevel(Integer clevel) {
        this.clevel = clevel;
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }
}


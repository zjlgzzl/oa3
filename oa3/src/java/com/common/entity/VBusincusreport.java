/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusincusreport
 */
package com.common.entity;

import java.io.Serializable;

public class VBusincusreport
implements Serializable {
    private Integer cusid;
    private String cusname;
    private Short flag;
    private Short black;

    public Integer getCusid() {
        return this.cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getCusname() {
        return this.cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public Short getFlag() {
        return this.flag;
    }

    public void setFlag(Short flag) {
        this.flag = flag;
    }

    public Short getBlack() {
        return this.black;
    }

    public void setBlack(Short black) {
        this.black = black;
    }
}


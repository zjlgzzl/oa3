/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinSumReport
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VBusinSumReport
implements Serializable {
    private Integer businId;
    private String ccode;
    private Integer cusid;
    private String cusName;
    private Integer eid;
    private String ename;
    private Timestamp createdate;
    private Integer black;

    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public String getCcode() {
        return this.ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public Integer getCusid() {
        return this.cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Integer getEid() {
        return this.eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Timestamp getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Integer getBlack() {
        return this.black;
    }

    public void setBlack(Integer black) {
        this.black = black;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinInvUser
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VBusinInvUser
implements Serializable {
    private Integer businId;
    private String ccode;
    private Integer cusid;
    private String cusName;
    private Integer eid;
    private String ename;
    private Timestamp createdate;
    private Short noticeFlag;
    private String noticeRemarks;
    private String noticeReply;
    private String lastMotifyName;
    private String lastNoticeName;
    private Timestamp lastNoticeDate;

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

    public Short getNoticeFlag() {
        return this.noticeFlag;
    }

    public void setNoticeFlag(Short noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public String getNoticeRemarks() {
        return this.noticeRemarks;
    }

    public void setNoticeRemarks(String noticeRemarks) {
        this.noticeRemarks = noticeRemarks;
    }

    public String getNoticeReply() {
        return this.noticeReply;
    }

    public void setNoticeReply(String noticeReply) {
        this.noticeReply = noticeReply;
    }

    public String getLastMotifyName() {
        return this.lastMotifyName;
    }

    public void setLastMotifyName(String lastMotifyName) {
        this.lastMotifyName = lastMotifyName;
    }

    public String getLastNoticeName() {
        return this.lastNoticeName;
    }

    public void setLastNoticeName(String lastNoticeName) {
        this.lastNoticeName = lastNoticeName;
    }

    public Timestamp getLastNoticeDate() {
        return this.lastNoticeDate;
    }

    public void setLastNoticeDate(Timestamp lastNoticeDate) {
        this.lastNoticeDate = lastNoticeDate;
    }
}


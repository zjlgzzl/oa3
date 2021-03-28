/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusinAskId
 */
package com.common.entity;

import java.io.Serializable;

public class VBusinAskId
implements Serializable {
    private Integer businId;
    private String ccode;
    private Integer cusid;
    private String cusName;
    private Long eid;
    private String ename;
    private String createdate;
    private Long noticeFlag;
    private String askRemarks;
    private String answerRemarks;
    private String askName;
    private String answerName;
    private String createName;

    public VBusinAskId() {
    }

    public VBusinAskId(Integer businId, Integer cusid, Long eid, String ename, String createdate, Long noticeFlag, String askRemarks, String answerRemarks, String askName, String answerName, String createName) {
        this.businId = businId;
        this.cusid = cusid;
        this.eid = eid;
        this.ename = ename;
        this.createdate = createdate;
        this.noticeFlag = noticeFlag;
        this.askRemarks = askRemarks;
        this.answerRemarks = answerRemarks;
        this.askName = askName;
        this.answerName = answerName;
        this.createName = createName;
    }

    public VBusinAskId(Integer businId, String ccode, Integer cusid, String cusName, Long eid, String ename, String createdate, Long noticeFlag, String askRemarks, String answerRemarks, String askName, String answerName, String createName) {
        this.businId = businId;
        this.ccode = ccode;
        this.cusid = cusid;
        this.cusName = cusName;
        this.eid = eid;
        this.ename = ename;
        this.createdate = createdate;
        this.noticeFlag = noticeFlag;
        this.askRemarks = askRemarks;
        this.answerRemarks = answerRemarks;
        this.askName = askName;
        this.answerName = answerName;
        this.createName = createName;
    }

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

    public Long getEid() {
        return this.eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Long getNoticeFlag() {
        return this.noticeFlag;
    }

    public void setNoticeFlag(Long noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public String getAskRemarks() {
        return this.askRemarks;
    }

    public void setAskRemarks(String askRemarks) {
        this.askRemarks = askRemarks;
    }

    public String getAnswerRemarks() {
        return this.answerRemarks;
    }

    public void setAnswerRemarks(String answerRemarks) {
        this.answerRemarks = answerRemarks;
    }

    public String getAskName() {
        return this.askName;
    }

    public void setAskName(String askName) {
        this.askName = askName;
    }

    public String getAnswerName() {
        return this.answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VBusinAskId)) {
            return false;
        }
        VBusinAskId castOther = (VBusinAskId)other;
        return (this.getBusinId() == castOther.getBusinId() || this.getBusinId() != null && castOther.getBusinId() != null && this.getBusinId().equals(castOther.getBusinId())) && (this.getCcode() == castOther.getCcode() || this.getCcode() != null && castOther.getCcode() != null && this.getCcode().equals(castOther.getCcode())) && (this.getCusid() == castOther.getCusid() || this.getCusid() != null && castOther.getCusid() != null && this.getCusid().equals(castOther.getCusid())) && (this.getCusName() == castOther.getCusName() || this.getCusName() != null && castOther.getCusName() != null && this.getCusName().equals(castOther.getCusName())) && (this.getEid() == castOther.getEid() || this.getEid() != null && castOther.getEid() != null && this.getEid().equals(castOther.getEid())) && (this.getEname() == castOther.getEname() || this.getEname() != null && castOther.getEname() != null && this.getEname().equals(castOther.getEname())) && (this.getCreatedate() == castOther.getCreatedate() || this.getCreatedate() != null && castOther.getCreatedate() != null && this.getCreatedate().equals(castOther.getCreatedate())) && (this.getNoticeFlag() == castOther.getNoticeFlag() || this.getNoticeFlag() != null && castOther.getNoticeFlag() != null && this.getNoticeFlag().equals(castOther.getNoticeFlag())) && (this.getAskRemarks() == castOther.getAskRemarks() || this.getAskRemarks() != null && castOther.getAskRemarks() != null && this.getAskRemarks().equals(castOther.getAskRemarks())) && (this.getAnswerRemarks() == castOther.getAnswerRemarks() || this.getAnswerRemarks() != null && castOther.getAnswerRemarks() != null && this.getAnswerRemarks().equals(castOther.getAnswerRemarks())) && (this.getAskName() == castOther.getAskName() || this.getAskName() != null && castOther.getAskName() != null && this.getAskName().equals(castOther.getAskName())) && (this.getAnswerName() == castOther.getAnswerName() || this.getAnswerName() != null && castOther.getAnswerName() != null && this.getAnswerName().equals(castOther.getAnswerName())) && (this.getCreateName() == castOther.getCreateName() || this.getCreateName() != null && castOther.getCreateName() != null && this.getCreateName().equals(castOther.getCreateName()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getBusinId() == null ? 0 : this.getBusinId().hashCode());
        result = 37 * result + (this.getCcode() == null ? 0 : this.getCcode().hashCode());
        result = 37 * result + (this.getCusid() == null ? 0 : this.getCusid().hashCode());
        result = 37 * result + (this.getCusName() == null ? 0 : this.getCusName().hashCode());
        result = 37 * result + (this.getEid() == null ? 0 : this.getEid().hashCode());
        result = 37 * result + (this.getEname() == null ? 0 : this.getEname().hashCode());
        result = 37 * result + (this.getCreatedate() == null ? 0 : this.getCreatedate().hashCode());
        result = 37 * result + (this.getNoticeFlag() == null ? 0 : this.getNoticeFlag().hashCode());
        result = 37 * result + (this.getAskRemarks() == null ? 0 : this.getAskRemarks().hashCode());
        result = 37 * result + (this.getAnswerRemarks() == null ? 0 : this.getAnswerRemarks().hashCode());
        result = 37 * result + (this.getAskName() == null ? 0 : this.getAskName().hashCode());
        result = 37 * result + (this.getAnswerName() == null ? 0 : this.getAnswerName().hashCode());
        result = 37 * result + (this.getCreateName() == null ? 0 : this.getCreateName().hashCode());
        return result;
    }
}


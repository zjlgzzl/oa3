/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VCostGroupSumId
 */
package com.common.entity;

import java.io.Serializable;

public class VCostGroupSumId
implements Serializable {
    private Integer groupId;
    private String groupName;
    private Integer businId;
    private Double sumMoney;
    private Double sumRiel;
    private Double total;
    private Integer infoId;
    private Integer noticeFlag;

    public VCostGroupSumId() {
    }

    public VCostGroupSumId(Integer groupId, Double total, Integer noticeFlag) {
        this.groupId = groupId;
        this.total = total;
        this.noticeFlag = noticeFlag;
    }

    public VCostGroupSumId(Integer groupId, String groupName, Integer businId, Double sumMoney, Double sumRiel, Double total, Integer infoId, Integer noticeFlag) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.businId = businId;
        this.sumMoney = sumMoney;
        this.sumRiel = sumRiel;
        this.total = total;
        this.infoId = infoId;
        this.noticeFlag = noticeFlag;
    }

    public Integer getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public Double getSumMoney() {
        return this.sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Double getSumRiel() {
        return this.sumRiel;
    }

    public void setSumRiel(Double sumRiel) {
        this.sumRiel = sumRiel;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getInfoId() {
        return this.infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getNoticeFlag() {
        return this.noticeFlag;
    }

    public void setNoticeFlag(Integer noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VCostGroupSumId)) {
            return false;
        }
        VCostGroupSumId castOther = (VCostGroupSumId)other;
        return (this.getGroupId() == castOther.getGroupId() || this.getGroupId() != null && castOther.getGroupId() != null && this.getGroupId().equals(castOther.getGroupId())) && (this.getGroupName() == castOther.getGroupName() || this.getGroupName() != null && castOther.getGroupName() != null && this.getGroupName().equals(castOther.getGroupName())) && (this.getBusinId() == castOther.getBusinId() || this.getBusinId() != null && castOther.getBusinId() != null && this.getBusinId().equals(castOther.getBusinId())) && (this.getSumMoney() == castOther.getSumMoney() || this.getSumMoney() != null && castOther.getSumMoney() != null && this.getSumMoney().equals(castOther.getSumMoney())) && (this.getSumRiel() == castOther.getSumRiel() || this.getSumRiel() != null && castOther.getSumRiel() != null && this.getSumRiel().equals(castOther.getSumRiel())) && (this.getTotal() == castOther.getTotal() || this.getTotal() != null && castOther.getTotal() != null && this.getTotal().equals(castOther.getTotal())) && (this.getInfoId() == castOther.getInfoId() || this.getInfoId() != null && castOther.getInfoId() != null && this.getInfoId().equals(castOther.getInfoId())) && (this.getNoticeFlag() == castOther.getNoticeFlag() || this.getNoticeFlag() != null && castOther.getNoticeFlag() != null && this.getNoticeFlag().equals(castOther.getNoticeFlag()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getGroupId() == null ? 0 : this.getGroupId().hashCode());
        result = 37 * result + (this.getGroupName() == null ? 0 : this.getGroupName().hashCode());
        result = 37 * result + (this.getBusinId() == null ? 0 : this.getBusinId().hashCode());
        result = 37 * result + (this.getSumMoney() == null ? 0 : this.getSumMoney().hashCode());
        result = 37 * result + (this.getSumRiel() == null ? 0 : this.getSumRiel().hashCode());
        result = 37 * result + (this.getTotal() == null ? 0 : this.getTotal().hashCode());
        result = 37 * result + (this.getInfoId() == null ? 0 : this.getInfoId().hashCode());
        result = 37 * result + (this.getNoticeFlag() == null ? 0 : this.getNoticeFlag().hashCode());
        return result;
    }
}


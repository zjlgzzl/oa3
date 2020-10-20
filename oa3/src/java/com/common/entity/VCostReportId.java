/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VCostReportId
 */
package com.common.entity;

import java.io.Serializable;

public class VCostReportId
implements Serializable {
    private Integer businId;
    private String cdate;
    private String billNo;
    private Integer itemId;
    private String itemName;
    private Double cmoney;
    private String re;
    private String rem;
    private String nt;
    private String remarks;
    private String cost1;
    private Short cstate;
    private String stateName;
    private Integer hiddenFlag;
    private String cusName;
    private String cnodate8;
    private String dn2;
    private String inv2;
    private String remarks5;
    private Integer newCostFlag;
    private Long empId;
    private String empName;
    private String takeNo;
    private Long appbyId;
    private String appbyName;
    private String refno;
    private Long groupid;
    private String groupName;
    private Integer baoxiao;
    private Integer editCostFlag;
    private Integer bid;

    public VCostReportId() {
    }

    public VCostReportId(Integer businId, String cdate, Integer itemId, Double cmoney, String re, String rem, String nt, String remarks, String cost1, Integer hiddenFlag, String cusName, String cnodate8, String dn2, String inv2, String remarks5, Integer newCostFlag, Long empId, String empName, String takeNo, Long appbyId, String appbyName, String refno, Long groupid, String groupName, Integer baoxiao, Integer editCostFlag, Integer bid) {
        this.businId = businId;
        this.cdate = cdate;
        this.itemId = itemId;
        this.cmoney = cmoney;
        this.re = re;
        this.rem = rem;
        this.nt = nt;
        this.remarks = remarks;
        this.cost1 = cost1;
        this.hiddenFlag = hiddenFlag;
        this.cusName = cusName;
        this.cnodate8 = cnodate8;
        this.dn2 = dn2;
        this.inv2 = inv2;
        this.remarks5 = remarks5;
        this.newCostFlag = newCostFlag;
        this.empId = empId;
        this.empName = empName;
        this.takeNo = takeNo;
        this.appbyId = appbyId;
        this.appbyName = appbyName;
        this.refno = refno;
        this.groupid = groupid;
        this.groupName = groupName;
        this.baoxiao = baoxiao;
        this.editCostFlag = editCostFlag;
        this.bid = bid;
    }

    public VCostReportId(Integer businId, String cdate, String billNo, Integer itemId, String itemName, Double cmoney, String re, String rem, String nt, String remarks, String cost1, Short cstate, String stateName, Integer hiddenFlag, String cusName, String cnodate8, String dn2, String inv2, String remarks5, Integer newCostFlag, Long empId, String empName, String takeNo, Long appbyId, String appbyName, String refno, Long groupid, String groupName, Integer baoxiao, Integer editCostFlag, Integer bid) {
        this.businId = businId;
        this.cdate = cdate;
        this.billNo = billNo;
        this.itemId = itemId;
        this.itemName = itemName;
        this.cmoney = cmoney;
        this.re = re;
        this.rem = rem;
        this.nt = nt;
        this.remarks = remarks;
        this.cost1 = cost1;
        this.cstate = cstate;
        this.stateName = stateName;
        this.hiddenFlag = hiddenFlag;
        this.cusName = cusName;
        this.cnodate8 = cnodate8;
        this.dn2 = dn2;
        this.inv2 = inv2;
        this.remarks5 = remarks5;
        this.newCostFlag = newCostFlag;
        this.empId = empId;
        this.empName = empName;
        this.takeNo = takeNo;
        this.appbyId = appbyId;
        this.appbyName = appbyName;
        this.refno = refno;
        this.groupid = groupid;
        this.groupName = groupName;
        this.baoxiao = baoxiao;
        this.editCostFlag = editCostFlag;
        this.bid = bid;
    }

    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public String getCdate() {
        return this.cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getBillNo() {
        return this.billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getItemId() {
        return this.itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public String getRe() {
        return this.re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public String getRem() {
        return this.rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getNt() {
        return this.nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCost1() {
        return this.cost1;
    }

    public void setCost1(String cost1) {
        this.cost1 = cost1;
    }

    public Short getCstate() {
        return this.cstate;
    }

    public void setCstate(Short cstate) {
        this.cstate = cstate;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getHiddenFlag() {
        return this.hiddenFlag;
    }

    public void setHiddenFlag(Integer hiddenFlag) {
        this.hiddenFlag = hiddenFlag;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCnodate8() {
        return this.cnodate8;
    }

    public void setCnodate8(String cnodate8) {
        this.cnodate8 = cnodate8;
    }

    public String getDn2() {
        return this.dn2;
    }

    public void setDn2(String dn2) {
        this.dn2 = dn2;
    }

    public String getInv2() {
        return this.inv2;
    }

    public void setInv2(String inv2) {
        this.inv2 = inv2;
    }

    public String getRemarks5() {
        return this.remarks5;
    }

    public void setRemarks5(String remarks5) {
        this.remarks5 = remarks5;
    }

    public Integer getNewCostFlag() {
        return this.newCostFlag;
    }

    public void setNewCostFlag(Integer newCostFlag) {
        this.newCostFlag = newCostFlag;
    }

    public Long getEmpId() {
        return this.empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTakeNo() {
        return this.takeNo;
    }

    public void setTakeNo(String takeNo) {
        this.takeNo = takeNo;
    }

    public Long getAppbyId() {
        return this.appbyId;
    }

    public void setAppbyId(Long appbyId) {
        this.appbyId = appbyId;
    }

    public String getAppbyName() {
        return this.appbyName;
    }

    public void setAppbyName(String appbyName) {
        this.appbyName = appbyName;
    }

    public String getRefno() {
        return this.refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public Long getGroupid() {
        return this.groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getBaoxiao() {
        return this.baoxiao;
    }

    public void setBaoxiao(Integer baoxiao) {
        this.baoxiao = baoxiao;
    }

    public Integer getEditCostFlag() {
        return this.editCostFlag;
    }

    public void setEditCostFlag(Integer editCostFlag) {
        this.editCostFlag = editCostFlag;
    }

    public Integer getBid() {
        return this.bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof VCostReportId)) {
            return false;
        }
        VCostReportId castOther = (VCostReportId)other;
        return (this.getBusinId() == castOther.getBusinId() || this.getBusinId() != null && castOther.getBusinId() != null && this.getBusinId().equals(castOther.getBusinId())) && (this.getCdate() == castOther.getCdate() || this.getCdate() != null && castOther.getCdate() != null && this.getCdate().equals(castOther.getCdate())) && (this.getBillNo() == castOther.getBillNo() || this.getBillNo() != null && castOther.getBillNo() != null && this.getBillNo().equals(castOther.getBillNo())) && (this.getItemId() == castOther.getItemId() || this.getItemId() != null && castOther.getItemId() != null && this.getItemId().equals(castOther.getItemId())) && (this.getItemName() == castOther.getItemName() || this.getItemName() != null && castOther.getItemName() != null && this.getItemName().equals(castOther.getItemName())) && (this.getCmoney() == castOther.getCmoney() || this.getCmoney() != null && castOther.getCmoney() != null && this.getCmoney().equals(castOther.getCmoney())) && (this.getRe() == castOther.getRe() || this.getRe() != null && castOther.getRe() != null && this.getRe().equals(castOther.getRe())) && (this.getRem() == castOther.getRem() || this.getRem() != null && castOther.getRem() != null && this.getRem().equals(castOther.getRem())) && (this.getNt() == castOther.getNt() || this.getNt() != null && castOther.getNt() != null && this.getNt().equals(castOther.getNt())) && (this.getRemarks() == castOther.getRemarks() || this.getRemarks() != null && castOther.getRemarks() != null && this.getRemarks().equals(castOther.getRemarks())) && (this.getCost1() == castOther.getCost1() || this.getCost1() != null && castOther.getCost1() != null && this.getCost1().equals(castOther.getCost1())) && (this.getCstate() == castOther.getCstate() || this.getCstate() != null && castOther.getCstate() != null && this.getCstate().equals(castOther.getCstate())) && (this.getStateName() == castOther.getStateName() || this.getStateName() != null && castOther.getStateName() != null && this.getStateName().equals(castOther.getStateName())) && (this.getHiddenFlag() == castOther.getHiddenFlag() || this.getHiddenFlag() != null && castOther.getHiddenFlag() != null && this.getHiddenFlag().equals(castOther.getHiddenFlag())) && (this.getCusName() == castOther.getCusName() || this.getCusName() != null && castOther.getCusName() != null && this.getCusName().equals(castOther.getCusName())) && (this.getCnodate8() == castOther.getCnodate8() || this.getCnodate8() != null && castOther.getCnodate8() != null && this.getCnodate8().equals(castOther.getCnodate8())) && (this.getDn2() == castOther.getDn2() || this.getDn2() != null && castOther.getDn2() != null && this.getDn2().equals(castOther.getDn2())) && (this.getInv2() == castOther.getInv2() || this.getInv2() != null && castOther.getInv2() != null && this.getInv2().equals(castOther.getInv2())) && (this.getRemarks5() == castOther.getRemarks5() || this.getRemarks5() != null && castOther.getRemarks5() != null && this.getRemarks5().equals(castOther.getRemarks5())) && (this.getNewCostFlag() == castOther.getNewCostFlag() || this.getNewCostFlag() != null && castOther.getNewCostFlag() != null && this.getNewCostFlag().equals(castOther.getNewCostFlag())) && (this.getEmpId() == castOther.getEmpId() || this.getEmpId() != null && castOther.getEmpId() != null && this.getEmpId().equals(castOther.getEmpId())) && (this.getEmpName() == castOther.getEmpName() || this.getEmpName() != null && castOther.getEmpName() != null && this.getEmpName().equals(castOther.getEmpName())) && (this.getTakeNo() == castOther.getTakeNo() || this.getTakeNo() != null && castOther.getTakeNo() != null && this.getTakeNo().equals(castOther.getTakeNo())) && (this.getAppbyId() == castOther.getAppbyId() || this.getAppbyId() != null && castOther.getAppbyId() != null && this.getAppbyId().equals(castOther.getAppbyId())) && (this.getAppbyName() == castOther.getAppbyName() || this.getAppbyName() != null && castOther.getAppbyName() != null && this.getAppbyName().equals(castOther.getAppbyName())) && (this.getRefno() == castOther.getRefno() || this.getRefno() != null && castOther.getRefno() != null && this.getRefno().equals(castOther.getRefno())) && (this.getGroupid() == castOther.getGroupid() || this.getGroupid() != null && castOther.getGroupid() != null && this.getGroupid().equals(castOther.getGroupid())) && (this.getGroupName() == castOther.getGroupName() || this.getGroupName() != null && castOther.getGroupName() != null && this.getGroupName().equals(castOther.getGroupName())) && (this.getBaoxiao() == castOther.getBaoxiao() || this.getBaoxiao() != null && castOther.getBaoxiao() != null && this.getBaoxiao().equals(castOther.getBaoxiao())) && (this.getEditCostFlag() == castOther.getEditCostFlag() || this.getEditCostFlag() != null && castOther.getEditCostFlag() != null && this.getEditCostFlag().equals(castOther.getEditCostFlag())) && (this.getBid() == castOther.getBid() || this.getBid() != null && castOther.getBid() != null && this.getBid().equals(castOther.getBid()));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (this.getBusinId() == null ? 0 : this.getBusinId().hashCode());
        result = 37 * result + (this.getCdate() == null ? 0 : this.getCdate().hashCode());
        result = 37 * result + (this.getBillNo() == null ? 0 : this.getBillNo().hashCode());
        result = 37 * result + (this.getItemId() == null ? 0 : this.getItemId().hashCode());
        result = 37 * result + (this.getItemName() == null ? 0 : this.getItemName().hashCode());
        result = 37 * result + (this.getCmoney() == null ? 0 : this.getCmoney().hashCode());
        result = 37 * result + (this.getRe() == null ? 0 : this.getRe().hashCode());
        result = 37 * result + (this.getRem() == null ? 0 : this.getRem().hashCode());
        result = 37 * result + (this.getNt() == null ? 0 : this.getNt().hashCode());
        result = 37 * result + (this.getRemarks() == null ? 0 : this.getRemarks().hashCode());
        result = 37 * result + (this.getCost1() == null ? 0 : this.getCost1().hashCode());
        result = 37 * result + (this.getCstate() == null ? 0 : this.getCstate().hashCode());
        result = 37 * result + (this.getStateName() == null ? 0 : this.getStateName().hashCode());
        result = 37 * result + (this.getHiddenFlag() == null ? 0 : this.getHiddenFlag().hashCode());
        result = 37 * result + (this.getCusName() == null ? 0 : this.getCusName().hashCode());
        result = 37 * result + (this.getCnodate8() == null ? 0 : this.getCnodate8().hashCode());
        result = 37 * result + (this.getDn2() == null ? 0 : this.getDn2().hashCode());
        result = 37 * result + (this.getInv2() == null ? 0 : this.getInv2().hashCode());
        result = 37 * result + (this.getRemarks5() == null ? 0 : this.getRemarks5().hashCode());
        result = 37 * result + (this.getNewCostFlag() == null ? 0 : this.getNewCostFlag().hashCode());
        result = 37 * result + (this.getEmpId() == null ? 0 : this.getEmpId().hashCode());
        result = 37 * result + (this.getEmpName() == null ? 0 : this.getEmpName().hashCode());
        result = 37 * result + (this.getTakeNo() == null ? 0 : this.getTakeNo().hashCode());
        result = 37 * result + (this.getAppbyId() == null ? 0 : this.getAppbyId().hashCode());
        result = 37 * result + (this.getAppbyName() == null ? 0 : this.getAppbyName().hashCode());
        result = 37 * result + (this.getRefno() == null ? 0 : this.getRefno().hashCode());
        result = 37 * result + (this.getGroupid() == null ? 0 : this.getGroupid().hashCode());
        result = 37 * result + (this.getGroupName() == null ? 0 : this.getGroupName().hashCode());
        result = 37 * result + (this.getBaoxiao() == null ? 0 : this.getBaoxiao().hashCode());
        result = 37 * result + (this.getEditCostFlag() == null ? 0 : this.getEditCostFlag().hashCode());
        result = 37 * result + (this.getBid() == null ? 0 : this.getBid().hashCode());
        return result;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.VBusin
 */
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class VBusin
implements Serializable {
    private Integer businId;
    private Timestamp businDate;
    private String billNo;
    private String send;
    private String receive;
    private Timestamp createDate;
    private Short businState;
    private String businStateName;
    private Integer cusId;
    private String cusName;
    private Integer userId;
    private String username;
    private Integer empId;
    private String empName;
    private Integer deptId;
    private Short businAuditFlg;
    private Short financeAuditFlg;
    private Integer costCount;
    private Integer cashCount;
    private Integer costHisCount;
    private Integer cashHisCount;
    private Integer orderNum;
    private String takeNo;
    private Short lockFlag;
    private Short lockFlag2;
    private String archiveRemarks;
    private Integer typeId;
    private String typeName;
    private Short completeFlag;
    private String fileRemarks;
    private Short recieveFlag;
    private String recieveRemarks;
    private Short newCostFlag;
    private Short costDupFlag;
    private String saleman;
    private Short schedule_archive;
    private Timestamp nodate;
    private String agent1;
    private String agent2;
    private Timestamp clearDate;
    private String kaipiaoRemarks;
    private Integer coid;
    private String coname;
    private Short addGroupFlag;
    private Integer lockBy;
    private Timestamp nodate2;
    private Timestamp nodate4;
    private Timestamp nodate8;
    private String cbm;
    private String pkg;
    private String weight;
    private String pickupStatus;
    private Integer salerId;
    public Integer getBusinId() {
        return this.businId;
    }

    public void setBusinId(Integer businId) {
        this.businId = businId;
    }

    public Timestamp getBusinDate() {
        return this.businDate;
    }

    public void setBusinDate(Timestamp businDate) {
        this.businDate = businDate;
    }

    public String getBillNo() {
        return this.billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getSend() {
        return this.send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getReceive() {
        return this.receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Short getBusinState() {
        return this.businState;
    }

    public void setBusinState(Short businState) {
        this.businState = businState;
    }

    public String getBusinStateName() {
        return this.businStateName;
    }

    public void setBusinStateName(String businStateName) {
        this.businStateName = businStateName;
    }

    public Integer getCusId() {
        return this.cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getEmpId() {
        return this.empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Short getBusinAuditFlg() {
        return this.businAuditFlg;
    }

    public void setBusinAuditFlg(Short businAuditFlg) {
        this.businAuditFlg = businAuditFlg;
    }

    public Short getFinanceAuditFlg() {
        return this.financeAuditFlg;
    }

    public void setFinanceAuditFlg(Short financeAuditFlg) {
        this.financeAuditFlg = financeAuditFlg;
    }

    public Integer getCostCount() {
        return this.costCount;
    }

    public void setCostCount(Integer costCount) {
        this.costCount = costCount;
    }

    public Integer getCashCount() {
        return this.cashCount;
    }

    public void setCashCount(Integer cashCount) {
        this.cashCount = cashCount;
    }

    public Integer getCostHisCount() {
        return this.costHisCount;
    }

    public void setCostHisCount(Integer costHisCount) {
        this.costHisCount = costHisCount;
    }

    public Integer getCashHisCount() {
        return this.cashHisCount;
    }

    public void setCashHisCount(Integer cashHisCount) {
        this.cashHisCount = cashHisCount;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getTakeNo() {
        return this.takeNo;
    }

    public void setTakeNo(String takeNo) {
        this.takeNo = takeNo;
    }

    public Short getLockFlag() {
        return this.lockFlag;
    }

    public void setLockFlag(Short lockFlag) {
        this.lockFlag = lockFlag;
    }

    public Short getLockFlag2() {
        return this.lockFlag2;
    }

    public void setLockFlag2(Short lockFlag2) {
        this.lockFlag2 = lockFlag2;
    }

    public String getArchiveRemarks() {
        return this.archiveRemarks;
    }

    public void setArchiveRemarks(String archiveRemarks) {
        this.archiveRemarks = archiveRemarks;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Short getCompleteFlag() {
        return this.completeFlag;
    }

    public void setCompleteFlag(Short completeFlag) {
        this.completeFlag = completeFlag;
    }

    public String getFileRemarks() {
        return this.fileRemarks;
    }

    public void setFileRemarks(String fileRemarks) {
        this.fileRemarks = fileRemarks;
    }

    public Short getRecieveFlag() {
        return this.recieveFlag;
    }

    public void setRecieveFlag(Short recieveFlag) {
        this.recieveFlag = recieveFlag;
    }

    public String getRecieveRemarks() {
        return this.recieveRemarks;
    }

    public void setRecieveRemarks(String recieveRemarks) {
        this.recieveRemarks = recieveRemarks;
    }

    public Short getNewCostFlag() {
        return this.newCostFlag;
    }

    public void setNewCostFlag(Short newCostFlag) {
        this.newCostFlag = newCostFlag;
    }

    public Short getCostDupFlag() {
        return this.costDupFlag;
    }

    public void setCostDupFlag(Short costDupFlag) {
        this.costDupFlag = costDupFlag;
    }

    public String getSaleman() {
        return this.saleman;
    }

    public void setSaleman(String saleman) {
        this.saleman = saleman;
    }

    public Short getSchedule_archive() {
        return this.schedule_archive;
    }

    public void setSchedule_archive(Short schedule_archive) {
        this.schedule_archive = schedule_archive;
    }

    public Timestamp getNodate() {
        return this.nodate;
    }

    public void setNodate(Timestamp nodate) {
        this.nodate = nodate;
    }

    public String getAgent1() {
        return this.agent1;
    }

    public void setAgent1(String agent1) {
        this.agent1 = agent1;
    }

    public String getAgent2() {
        return this.agent2;
    }

    public void setAgent2(String agent2) {
        this.agent2 = agent2;
    }

    public Timestamp getClearDate() {
        return this.clearDate;
    }

    public void setClearDate(Timestamp clearDate) {
        this.clearDate = clearDate;
    }

    public String getKaipiaoRemarks() {
        return this.kaipiaoRemarks;
    }

    public void setKaipiaoRemarks(String kaipiaoRemarks) {
        this.kaipiaoRemarks = kaipiaoRemarks;
    }

    public Integer getCoid() {
        return this.coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public String getConame() {
        return this.coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public Short getAddGroupFlag() {
        return this.addGroupFlag;
    }

    public void setAddGroupFlag(Short addGroupFlag) {
        this.addGroupFlag = addGroupFlag;
    }

    public Integer getLockBy() {
        return this.lockBy;
    }

    public void setLockBy(Integer lockBy) {
        this.lockBy = lockBy;
    }

    public Timestamp getNodate2() {
        return this.nodate2;
    }

    public void setNodate2(Timestamp nodate2) {
        this.nodate2 = nodate2;
    }

    public Timestamp getNodate4() {
        return this.nodate4;
    }

    public void setNodate4(Timestamp nodate4) {
        this.nodate4 = nodate4;
    }

    public Timestamp getNodate8() {
        return this.nodate8;
    }

    public void setNodate8(Timestamp nodate8) {
        this.nodate8 = nodate8;
    }

	public String getCbm() {
		return cbm;
	}

	public void setCbm(String cbm) {
		this.cbm = cbm;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPickupStatus() {
		return pickupStatus;
	}

	public void setPickupStatus(String pickupStatus) {
		this.pickupStatus = pickupStatus;
	}

	public Integer getSalerId() {
		return salerId;
	}

	public void setSalerId(Integer salerId) {
		this.salerId = salerId;
	}

}


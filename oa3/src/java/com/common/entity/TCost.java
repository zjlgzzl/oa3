/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TBusin
 *  com.common.entity.TCost
 *  com.common.entity.TCostGroup
 *  com.common.entity.TCostItem
 *  com.common.entity.TUser
 */
package com.common.entity;

import com.common.entity.TBusin;
import com.common.entity.TCostGroup;
import com.common.entity.TCostItem;
import com.common.entity.TUser;
import java.io.Serializable;
import java.sql.Timestamp;

public class TCost
implements Serializable {
    private Integer CId;
    private TUser TUserByCUserid;
    private TBusin TBusin;
    private TCostGroup TCostGroup;
    private TUser TUserByCCreateUserid;
    private TCostItem TCostItem;
    private Double CMoney;
    private String CRemarks;
    private Short CState;
    private Short CPrint;
    private String CRemarks2;
    private String CRe;
    private String CRemarks3;
    private String CRemarks4;
    private Short CHidden;
    private String CRemarks5;
    private Short CNewCostFlag;
    private Short CCostDupFlag;
    private Integer COrder;
    private Timestamp CCreateDate;
    private Double CRiel;
    private Short CBaoxiao;
    private Short CEditCostFlag;
    private Short CAddGroup;

    public TCost() {
    }

    public TCost(TUser TUserByCUserid, TBusin TBusin2, TCostGroup TCostGroup2, TUser TUserByCCreateUserid, TCostItem TCostItem2, Double CMoney, String CRemarks, Short CState, Short CPrint, String CRemarks2, String CRe, String CRemarks3, String CRemarks4, Short CHidden, String CRemarks5, Short CNewCostFlag, Short CCostDupFlag, Integer COrder, Timestamp CCreateDate, Double CRiel, Short CBaoxiao, Short CEditCostFlag, Short CAddGroup) {
        this.TUserByCUserid = TUserByCUserid;
        this.TBusin = TBusin2;
        this.TCostGroup = TCostGroup2;
        this.TUserByCCreateUserid = TUserByCCreateUserid;
        this.TCostItem = TCostItem2;
        this.CMoney = CMoney;
        this.CRemarks = CRemarks;
        this.CState = CState;
        this.CPrint = CPrint;
        this.CRemarks2 = CRemarks2;
        this.CRe = CRe;
        this.CRemarks3 = CRemarks3;
        this.CRemarks4 = CRemarks4;
        this.CHidden = CHidden;
        this.CRemarks5 = CRemarks5;
        this.CNewCostFlag = CNewCostFlag;
        this.CCostDupFlag = CCostDupFlag;
        this.COrder = COrder;
        this.CCreateDate = CCreateDate;
        this.CRiel = CRiel;
        this.CBaoxiao = CBaoxiao;
        this.CEditCostFlag = CEditCostFlag;
        this.CAddGroup = CAddGroup;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TUser getTUserByCUserid() {
        return this.TUserByCUserid;
    }

    public void setTUserByCUserid(TUser TUserByCUserid) {
        this.TUserByCUserid = TUserByCUserid;
    }

    public TBusin getTBusin() {
        return this.TBusin;
    }

    public void setTBusin(TBusin TBusin2) {
        this.TBusin = TBusin2;
    }

    public TCostGroup getTCostGroup() {
        return this.TCostGroup;
    }

    public void setTCostGroup(TCostGroup TCostGroup2) {
        this.TCostGroup = TCostGroup2;
    }

    public TUser getTUserByCCreateUserid() {
        return this.TUserByCCreateUserid;
    }

    public void setTUserByCCreateUserid(TUser TUserByCCreateUserid) {
        this.TUserByCCreateUserid = TUserByCCreateUserid;
    }

    public TCostItem getTCostItem() {
        return this.TCostItem;
    }

    public void setTCostItem(TCostItem TCostItem2) {
        this.TCostItem = TCostItem2;
    }

    public Double getCMoney() {
        return this.CMoney;
    }

    public void setCMoney(Double CMoney) {
        this.CMoney = CMoney;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public Short getCState() {
        return this.CState;
    }

    public void setCState(Short CState) {
        this.CState = CState;
    }

    public Short getCPrint() {
        return this.CPrint;
    }

    public void setCPrint(Short CPrint) {
        this.CPrint = CPrint;
    }

    public String getCRemarks2() {
        return this.CRemarks2;
    }

    public void setCRemarks2(String CRemarks2) {
        this.CRemarks2 = CRemarks2;
    }

    public String getCRe() {
        return this.CRe;
    }

    public void setCRe(String CRe) {
        this.CRe = CRe;
    }

    public String getCRemarks3() {
        return this.CRemarks3;
    }

    public void setCRemarks3(String CRemarks3) {
        this.CRemarks3 = CRemarks3;
    }

    public String getCRemarks4() {
        return this.CRemarks4;
    }

    public void setCRemarks4(String CRemarks4) {
        this.CRemarks4 = CRemarks4;
    }

    public Short getCHidden() {
        return this.CHidden;
    }

    public void setCHidden(Short CHidden) {
        this.CHidden = CHidden;
    }

    public String getCRemarks5() {
        return this.CRemarks5;
    }

    public void setCRemarks5(String CRemarks5) {
        this.CRemarks5 = CRemarks5;
    }

    public Short getCNewCostFlag() {
        return this.CNewCostFlag;
    }

    public void setCNewCostFlag(Short CNewCostFlag) {
        this.CNewCostFlag = CNewCostFlag;
    }

    public Short getCCostDupFlag() {
        return this.CCostDupFlag;
    }

    public void setCCostDupFlag(Short CCostDupFlag) {
        this.CCostDupFlag = CCostDupFlag;
    }

    public Integer getCOrder() {
        return this.COrder;
    }

    public void setCOrder(Integer COrder) {
        this.COrder = COrder;
    }

    public Timestamp getCCreateDate() {
        return this.CCreateDate;
    }

    public void setCCreateDate(Timestamp CCreateDate) {
        this.CCreateDate = CCreateDate;
    }

    public Double getCRiel() {
        return this.CRiel;
    }

    public void setCRiel(Double CRiel) {
        this.CRiel = CRiel;
    }

    public Short getCBaoxiao() {
        return this.CBaoxiao;
    }

    public void setCBaoxiao(Short CBaoxiao) {
        this.CBaoxiao = CBaoxiao;
    }

    public Short getCEditCostFlag() {
        return this.CEditCostFlag;
    }

    public void setCEditCostFlag(Short CEditCostFlag) {
        this.CEditCostFlag = CEditCostFlag;
    }

    public Short getCAddGroup() {
        return this.CAddGroup;
    }

    public void setCAddGroup(Short CAddGroup) {
        this.CAddGroup = CAddGroup;
    }
}


/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.THscode
 *  com.common.entity.TUser
 */
package com.common.entity;

import com.common.entity.TUser;
import java.io.Serializable;
import java.sql.Timestamp;

public class THscode
implements Serializable {
    private Integer CId;
    private TUser TUser;
    private String CDescription;
    private String CDetails;
    private String CName;
    private String CHscode;
    private String CNew;
    private String CYear;
    private String CRate;
    private String CUnit;
    private String CImp;
    private String CSp;
    private String CVat;
    private String CAdd;
    private String CTotal;
    private String CMinistry;
    private String CForm;
    private String CRemarks;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private Short CType;

    public THscode() {
    }

    public THscode(String CDescription, String CDetails, String CName, String CHscode, String CNew, String CYear, String CRate, String CUnit, String CImp, String CSp, String CVat, String CAdd, String CTotal, String CMinistry, String CForm, String CRemarks) {
        this.CDescription = CDescription;
        this.CDetails = CDetails;
        this.CName = CName;
        this.CHscode = CHscode;
        this.CNew = CNew;
        this.CYear = CYear;
        this.CRate = CRate;
        this.CUnit = CUnit;
        this.CImp = CImp;
        this.CSp = CSp;
        this.CVat = CVat;
        this.CAdd = CAdd;
        this.CTotal = CTotal;
        this.CMinistry = CMinistry;
        this.CForm = CForm;
        this.CRemarks = CRemarks;
    }

    public THscode(TUser TUser2, String CDescription, String CDetails, String CName, String CHscode, String CNew, String CYear, String CRate, String CUnit, String CImp, String CSp, String CVat, String CAdd, String CTotal, String CMinistry, String CForm, String CRemarks, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, Short CType) {
        this.TUser = TUser2;
        this.CDescription = CDescription;
        this.CDetails = CDetails;
        this.CName = CName;
        this.CHscode = CHscode;
        this.CNew = CNew;
        this.CYear = CYear;
        this.CRate = CRate;
        this.CUnit = CUnit;
        this.CImp = CImp;
        this.CSp = CSp;
        this.CVat = CVat;
        this.CAdd = CAdd;
        this.CTotal = CTotal;
        this.CMinistry = CMinistry;
        this.CForm = CForm;
        this.CRemarks = CRemarks;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.CType = CType;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TUser getTUser() {
        return this.TUser;
    }

    public void setTUser(TUser TUser2) {
        this.TUser = TUser2;
    }

    public String getCDescription() {
        return this.CDescription;
    }

    public void setCDescription(String CDescription) {
        this.CDescription = CDescription;
    }

    public String getCDetails() {
        return this.CDetails;
    }

    public void setCDetails(String CDetails) {
        this.CDetails = CDetails;
    }

    public String getCName() {
        return this.CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCHscode() {
        return this.CHscode;
    }

    public void setCHscode(String CHscode) {
        this.CHscode = CHscode;
    }

    public String getCNew() {
        return this.CNew;
    }

    public void setCNew(String CNew) {
        this.CNew = CNew;
    }

    public String getCYear() {
        return this.CYear;
    }

    public void setCYear(String CYear) {
        this.CYear = CYear;
    }

    public String getCRate() {
        return this.CRate;
    }

    public void setCRate(String CRate) {
        this.CRate = CRate;
    }

    public String getCUnit() {
        return this.CUnit;
    }

    public void setCUnit(String CUnit) {
        this.CUnit = CUnit;
    }

    public String getCImp() {
        return this.CImp;
    }

    public void setCImp(String CImp) {
        this.CImp = CImp;
    }

    public String getCSp() {
        return this.CSp;
    }

    public void setCSp(String CSp) {
        this.CSp = CSp;
    }

    public String getCVat() {
        return this.CVat;
    }

    public void setCVat(String CVat) {
        this.CVat = CVat;
    }

    public String getCAdd() {
        return this.CAdd;
    }

    public void setCAdd(String CAdd) {
        this.CAdd = CAdd;
    }

    public String getCTotal() {
        return this.CTotal;
    }

    public void setCTotal(String CTotal) {
        this.CTotal = CTotal;
    }

    public String getCMinistry() {
        return this.CMinistry;
    }

    public void setCMinistry(String CMinistry) {
        this.CMinistry = CMinistry;
    }

    public String getCForm() {
        return this.CForm;
    }

    public void setCForm(String CForm) {
        this.CForm = CForm;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public Timestamp getCCreateDate() {
        return this.CCreateDate;
    }

    public void setCCreateDate(Timestamp CCreateDate) {
        this.CCreateDate = CCreateDate;
    }

    public Integer getCLastUserid() {
        return this.CLastUserid;
    }

    public void setCLastUserid(Integer CLastUserid) {
        this.CLastUserid = CLastUserid;
    }

    public Timestamp getCLastDate() {
        return this.CLastDate;
    }

    public void setCLastDate(Timestamp CLastDate) {
        this.CLastDate = CLastDate;
    }

    public Short getCType() {
        return this.CType;
    }

    public void setCType(Short CType) {
        this.CType = CType;
    }
}


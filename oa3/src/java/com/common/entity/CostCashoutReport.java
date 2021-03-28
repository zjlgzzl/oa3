package com.common.entity;

import java.io.Serializable;

public class CostCashoutReport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String billNo;
	private String name;
	private String items;
	private String agent;
	private String remark;
	private Double cosMoney;
	private Double payMoney;
	private Double diffMoney;
	private String status;
	private String startDate;
	private String endDate;
	private Integer cusId;
	private String cosMoneyTotal;
	private String payMoneyTotal;
	private String diffMoneyTotal;
	
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getCosMoney() {
		return cosMoney;
	}
	public void setCosMoney(Double cosMoney) {
		this.cosMoney = cosMoney;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public Double getDiffMoney() {
		return diffMoney;
	}
	public void setDiffMoney(Double diffMoney) {
		this.diffMoney = diffMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public String getCosMoneyTotal() {
		return cosMoneyTotal;
	}
	public void setCosMoneyTotal(String cosMoneyTotal) {
		this.cosMoneyTotal = cosMoneyTotal;
	}
	public String getPayMoneyTotal() {
		return payMoneyTotal;
	}
	public void setPayMoneyTotal(String payMoneyTotal) {
		this.payMoneyTotal = payMoneyTotal;
	}
	public String getDiffMoneyTotal() {
		return diffMoneyTotal;
	}
	public void setDiffMoneyTotal(String diffMoneyTotal) {
		this.diffMoneyTotal = diffMoneyTotal;
	}

}

package com.common.entity;

import java.io.Serializable;

public class BankAccountReport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String startDate;
	private String endDate;
	private String bankId;
	private String bankName;
	private String inMoney;
	private String outMoney;
	private String beginMoney;
	private String endMoney;
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
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getInMoney() {
		return inMoney;
	}
	public void setInMoney(String inMoney) {
		this.inMoney = inMoney;
	}
	public String getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(String outMoney) {
		this.outMoney = outMoney;
	}
	public String getBeginMoney() {
		return beginMoney;
	}
	public void setBeginMoney(String beginMoney) {
		this.beginMoney = beginMoney;
	}
	public String getEndMoney() {
		return endMoney;
	}
	public void setEndMoney(String endMoney) {
		this.endMoney = endMoney;
	}
	
	
}

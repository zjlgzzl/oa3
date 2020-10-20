package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TAgent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Integer createUserid;
    private Timestamp createDate;
    private Integer lastUserid;
    private Timestamp lastDate;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCreateUserid() {
		return createUserid;
	}
	public void setCreateUserid(Integer createUserid) {
		this.createUserid = createUserid;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Integer getLastUserid() {
		return lastUserid;
	}
	public void setLastUserid(Integer lastUserid) {
		this.lastUserid = lastUserid;
	}
	public Timestamp getLastDate() {
		return lastDate;
	}
	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}
    
}

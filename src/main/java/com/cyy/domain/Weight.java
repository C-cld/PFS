package com.cyy.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Weight {
	private String id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")//js中日期格式化
	private Date date;
	private double amWeight;
	private double pmWeight;
	private Date createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmWeight() {
		return amWeight;
	}
	public void setAmWeight(double amWeight) {
		this.amWeight = amWeight;
	}
	public double getPmWeight() {
		return pmWeight;
	}
	public void setPmWeight(double pmWeight) {
		this.pmWeight = pmWeight;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}

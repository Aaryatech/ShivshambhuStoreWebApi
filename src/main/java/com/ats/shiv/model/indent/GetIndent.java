package com.ats.shiv.model.indent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetIndent {

	@Id
	@Column(name = "ind_m_id")
	private int indMId;

	@Column(name = "ind_m_no")
	private String indMNo;
	@Column(name = "ind_m_date")
	private Date indMDate;

	@Column(name = "ind_m_type")
	private int indMType;
	
	private String indRemark;
	
	private String indIsdev;
	
	private int indIsmonthly;

	private String catDesc;
	
	private int achdId;
	
	private String accHeadDesc;
	
	private int catId;
	
	private String deptCode; 
	private String subDeptCode;
	private String apprvRemark1; 
	private String apprvRemark2;
	
	
	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getAccHeadDesc() {
		return accHeadDesc;
	}

	public void setAccHeadDesc(String accHeadDesc) {
		this.accHeadDesc = accHeadDesc;
	}

	@Column(name = "ind_m_status")
	private int indMStatus;

	
	private int deptId;
	private int subDeptId;
	
	
	
	public int getDeptId() {
		return deptId;
	}

	public int getSubDeptId() {
		return subDeptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public void setSubDeptId(int subDeptId) {
		this.subDeptId = subDeptId;
	}

	public int getIndMId() {
		return indMId;
	}

	public String getIndMNo() {
		return indMNo;
	}
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getIndMDate() {
		return indMDate;
	}

	public int getIndMType() {
		return indMType;
	}

	public String getIndIsdev() {
		return indIsdev;
	}

	public int getIndIsmonthly() {
		return indIsmonthly;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public int getIndMStatus() {
		return indMStatus;
	}

	public void setIndMId(int indMId) {
		this.indMId = indMId;
	}

	public void setIndMNo(String indMNo) {
		this.indMNo = indMNo;
	}

	public void setIndMDate(Date indMDate) {
		this.indMDate = indMDate;
	}

	public void setIndMType(int indMType) {
		this.indMType = indMType;
	}

	public void setIndIsdev(String indIsdev) {
		this.indIsdev = indIsdev;
	}

	public void setIndIsmonthly(int indIsmonthly) {
		this.indIsmonthly = indIsmonthly;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public void setIndMStatus(int indMStatus) {
		this.indMStatus = indMStatus;
	}

	public int getAchdId() {
		return achdId;
	}

	public void setAchdId(int achdId) {
		this.achdId = achdId;
	}

	public String getIndRemark() {
		return indRemark;
	}

	public void setIndRemark(String indRemark) {
		this.indRemark = indRemark;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getSubDeptCode() {
		return subDeptCode;
	}

	public void setSubDeptCode(String subDeptCode) {
		this.subDeptCode = subDeptCode;
	}

	public String getApprvRemark1() {
		return apprvRemark1;
	}

	public void setApprvRemark1(String apprvRemark1) {
		this.apprvRemark1 = apprvRemark1;
	}

	public String getApprvRemark2() {
		return apprvRemark2;
	}

	public void setApprvRemark2(String apprvRemark2) {
		this.apprvRemark2 = apprvRemark2;
	}

	@Override
	public String toString() {
		return "GetIndent [indMId=" + indMId + ", indMNo=" + indMNo + ", indMDate=" + indMDate + ", indMType="
				+ indMType + ", indRemark=" + indRemark + ", indIsdev=" + indIsdev + ", indIsmonthly=" + indIsmonthly
				+ ", catDesc=" + catDesc + ", achdId=" + achdId + ", accHeadDesc=" + accHeadDesc + ", catId=" + catId
				+ ", deptCode=" + deptCode + ", subDeptCode=" + subDeptCode + ", apprvRemark1=" + apprvRemark1
				+ ", apprvRemark2=" + apprvRemark2 + ", indMStatus=" + indMStatus + ", deptId=" + deptId
				+ ", subDeptId=" + subDeptId + "]";
	}

	
}

package com.ats.shivsapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetDamage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "damage_id")
	private int damageId;
	
	@Column(name = "date")
	private Date date;

	@Column(name = "item_id")
	private int itemId;

	@Column(name = "qty")
	private float qty;

	@Column(name = "value")
	private float value;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "del_status")
	private int delStatus;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "item_desc")
	private String itemDesc;
	
	@Column(name = "damage_no")
	private String damageNo;

	public int getDamageId() {
		return damageId;
	}

	public void setDamageId(int damageId) {
		this.damageId = damageId;
	}
	
	@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getDamageNo() {
		return damageNo;
	}

	public void setDamageNo(String damageNo) {
		this.damageNo = damageNo;
	}

	@Override
	public String toString() {
		return "GetDamage [damageId=" + damageId + ", date=" + date + ", itemId=" + itemId + ", qty=" + qty + ", value="
				+ value + ", reason=" + reason + ", delStatus=" + delStatus + ", itemCode=" + itemCode + ", itemDesc="
				+ itemDesc + ", damageNo=" + damageNo + "]";
	}
	
	

}

package com.ats.shivsapi.model.bom;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class GetReqBomHeader {

	/*
	 * SELECT
	 * t_req_bom.bom_req_id,t_req_bom.bom_req_date,t_req_bom.production_id,t_req_bom
	 * .production_date,t_req_bom.plant_id,t_req_bom.sub_plant_id,t_req_bom.
	 * sender_user_id,
	 * t_req_bom.is_manual,t_req_bom.status,m_plant.plant_name,m_subplant.
	 * subplant_name,m_user.usr_name,m_user.usr_mob
	 * 
	 * FROM t_req_bom,m_plant,m_subplant,m_user WHERE t_req_bom.del_status=1 AND
	 * m_plant.del_status=1 AND m_subplant.del_status=1 AND m_user.del_status=1 AND
	 * t_req_bom.plant_id=m_plant.plant_id AND m_plant.plant_id=m_subplant.plant_id
	 * AND t_req_bom.sender_user_id=m_user.user_id AND t_req_bom.status=1 AND
	 * t_req_bom.bom_req_date BETWEEN :fromDate AND :toDate
	 */

	@Id
	private int bomReqId;
	private Date bomReqDate;
	private int productionId;
	private Date productionDate;
	private int plantId;
	private int subPlantId;
	private int senderUserId;
	private int isManual;
	private int status;
	
	private String plantName;
	private String subplantName;
	
	private String usrName;
	private String usrMob;
	public int getBomReqId() {
		return bomReqId;
	}
	public void setBomReqId(int bomReqId) {
		this.bomReqId = bomReqId;
	}
	
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getBomReqDate() {
		return bomReqDate;
	}
	public void setBomReqDate(Date bomReqDate) {
		this.bomReqDate = bomReqDate;
	}
	public int getProductionId() {
		return productionId;
	}
	public void setProductionId(int productionId) {
		this.productionId = productionId;
	}
	
	@JsonFormat(locale = "hi",timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public int getSubPlantId() {
		return subPlantId;
	}
	public void setSubPlantId(int subPlantId) {
		this.subPlantId = subPlantId;
	}
	public int getSenderUserId() {
		return senderUserId;
	}
	public void setSenderUserId(int senderUserId) {
		this.senderUserId = senderUserId;
	}
	public int getIsManual() {
		return isManual;
	}
	public void setIsManual(int isManual) {
		this.isManual = isManual;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getSubplantName() {
		return subplantName;
	}
	public void setSubplantName(String subplantName) {
		this.subplantName = subplantName;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getUsrMob() {
		return usrMob;
	}
	public void setUsrMob(String usrMob) {
		this.usrMob = usrMob;
	}
	
	@Override
	public String toString() {
		return "GetReqBomHeader [bomReqId=" + bomReqId + ", bomReqDate=" + bomReqDate + ", productionId=" + productionId
				+ ", productionDate=" + productionDate + ", plantId=" + plantId + ", subPlantId=" + subPlantId
				+ ", senderUserId=" + senderUserId + ", isManual=" + isManual + ", status=" + status + ", plantName="
				+ plantName + ", subplantName=" + subplantName + ", usrName=" + usrName + ", usrMob=" + usrMob + "]";
	}

}

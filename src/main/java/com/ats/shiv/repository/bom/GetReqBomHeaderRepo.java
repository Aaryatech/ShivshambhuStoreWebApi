package com.ats.shiv.repository.bom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.bom.GetReqBomHeader;

public interface GetReqBomHeaderRepo extends JpaRepository<GetReqBomHeader, Integer>{
	
	@Query(value = " SELECT t_req_bom.bom_req_id,t_req_bom.bom_req_date,t_req_bom.production_id,t_req_bom.production_date,t_req_bom.plant_id,t_req_bom.sub_plant_id,t_req_bom.sender_user_id," + 
			" t_req_bom.is_manual,t_req_bom.status,m_plant.plant_name,m_subplant.subplant_name,m_user.usr_name,m_user.usr_mob " + 
			" FROM t_req_bom,m_plant,m_subplant,m_user " + 
			" WHERE t_req_bom.del_status=1 AND m_plant.del_status=1 AND m_subplant.del_status=1 AND m_user.del_status=1 " + 
			" AND t_req_bom.plant_id=m_plant.plant_id AND m_plant.plant_id=m_subplant.plant_id AND  t_req_bom.sender_user_id=m_user.user_id " + 
			" AND t_req_bom.status=1 AND t_req_bom.bom_req_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
	
	List<GetReqBomHeader> getGetReqBomHeader(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
	@Query(value = " SELECT t_req_bom.bom_req_id,t_req_bom.bom_req_date,t_req_bom.production_id,t_req_bom.production_date,t_req_bom.plant_id,t_req_bom.sub_plant_id,t_req_bom.sender_user_id," + 
			" t_req_bom.is_manual,t_req_bom.status,m_plant.plant_name,m_subplant.subplant_name,m_user.usr_name,m_user.usr_mob " + 
			" FROM t_req_bom,m_plant,m_subplant,m_user " + 
			" WHERE t_req_bom.del_status=1 AND m_plant.del_status=1 AND m_subplant.del_status=1 AND m_user.del_status=1 " + 
			" AND t_req_bom.plant_id=m_plant.plant_id AND m_plant.plant_id=m_subplant.plant_id AND  t_req_bom.sender_user_id=m_user.user_id " + 
			" AND t_req_bom.status=1 AND t_req_bom.bom_req_id=:bomReqId ", nativeQuery = true)
	GetReqBomHeader getOneGetReqBomHeader(@Param("bomReqId") int bomReqId);

	
}

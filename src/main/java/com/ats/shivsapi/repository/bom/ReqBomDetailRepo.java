package com.ats.shivsapi.repository.bom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shivsapi.model.bom.ReqBomDetail;


public interface ReqBomDetailRepo extends JpaRepository<ReqBomDetail, Integer> {
	
	
	List<ReqBomDetail> findByBomReqDetailIdIn(List<Integer> bomDIdList);
	
	

}

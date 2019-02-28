package com.ats.shiv.repository.bom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.bom.GetReqBomDetail;

public interface GetReqBomDetailRepo extends JpaRepository<GetReqBomDetail, Integer> {

	@Query(value = " SELECT t_req_bom_detail.bom_req_detail_id,t_req_bom_detail.bom_req_id,t_req_bom_detail.rm_id,t_req_bom_detail.auto_rm_req_qty,"
			+ " t_req_bom_detail.rm_req_qty,t_req_bom_detail.rm_issue_qty,t_req_bom_detail.status, "
			+ " m_item.item_code,m_item.item_desc,m_item.item_uom,m_item.cat_id,m_item.grp_id,m_item.sub_grp_id,m_uom_store.uom as rm_uom_name "
			+ " FROM t_req_bom_detail,m_item,m_uom_store "
			+ " WHERE t_req_bom_detail.bom_req_id=:bomReqId AND t_req_bom_detail.rm_id=m_item.item_id AND m_uom_store.uom_id=m_item.item_uom2 "
			+ " AND m_item.is_used=1 AND t_req_bom_detail.del_status=1 AND m_uom_store.is_used=1 ", nativeQuery = true)

	List<GetReqBomDetail> getGetReqBomDetail(@Param("bomReqId") int bomReqId);

}

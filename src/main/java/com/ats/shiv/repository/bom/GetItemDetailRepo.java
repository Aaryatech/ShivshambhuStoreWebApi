package com.ats.shiv.repository.bom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.bom.GetItemDetail;


public interface GetItemDetailRepo extends JpaRepository<GetItemDetail, Integer> {
	
	/*@Query(value = " SELECT m_item_detail.rm_name,m_item_detail.rm_qty,m_item_detail.no_pieces_per_item,"
			+ " m_item_detail.item_detail_id,"
			+ " m_item_detail.item_id,m_item_detail.rm_weight," + 
			" COALESCE((SELECT m_uom.uom_name FROM m_uom WHERE m_uom.uom_id=m_item_detail.rm_uom_id "
			+ " AND m_uom.del_status=1),'NA') AS rm_uom_name," + 
			" (t_production_plan_detail.plan_qty *m_item_detail.rm_qty) AS rm_quantity "
			+ "FROM t_production_plan_detail,m_item_detail " + 
			" WHERE m_item_detail.item_id=t_production_plan_detail.item_id "
			+ "AND t_production_plan_detail.production_header_id=:prodHeaderId "
			+ " AND m_item_detail.del_status=1 AND t_production_plan_detail.del_status=1 ", nativeQuery = true)
	
	List<GetItemDetail> getGetItemDetail2(@Param("prodHeaderId") int prodHeaderId);
	*/
	@Query(value = " SELECT m_item_detail.item_detail_id,m_item_detail.item_id as fg_item_id,"
			+ " m_item_detail.rm_id," + 
			" m_item_detail.rm_uom_id,m_item_detail.rm_qty," + 
			" m_item.item_code as rm_item_code,m_item.item_desc as rm_name,m_uom.uom_name as rm_uom_name,"
			+ " m_item.cat_id," + 
			" m_category.cat_desc, " + 
			" (t_production_plan_detail.plan_qty *m_item_detail.rm_qty) AS item_rm_qty "+
			" FROM  m_item_detail,m_item,m_category,m_uom,t_production_plan_header,t_production_plan_detail " + 
			" WHERE m_item_detail.rm_id=m_item.item_id " + 
			" AND m_item.cat_id=m_category.cat_id AND m_item.item_uom2=m_uom.uom_id " + 
			" AND m_item_detail.del_status=1 AND  m_uom.del_status=1  "
			+ " AND t_production_plan_header.production_header_id=:prodHeaderId " + 
			" AND t_production_plan_detail.production_header_id=t_production_plan_header.production_header_id "
			+ " "
			+ " AND t_production_plan_detail.item_id=m_item_detail.item_id AND "
			+ " t_production_plan_detail.del_status=1 " + 
			" ORDER by m_item_detail.item_detail_id ", nativeQuery = true)
	
	List<GetItemDetail> getGetItemDetail(@Param("prodHeaderId") int prodHeaderId);


}

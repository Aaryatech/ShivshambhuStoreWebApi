package com.ats.shivsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.shivsapi.model.GetPoHeader;

@Repository
public interface GetPoHeaderRepository extends JpaRepository<GetPoHeader, Integer>{

	
	@Query(value="select po_id,po_type,po_no,po_date,vend_id,vend_quation,i.ind_m_date as ind_date,vend_quation_date,po_basic_value,disc_value,po_tax_id,po_tax_per,po_tax_value,po_pack_per , po_pack_val,po_pack_remark,po_insu_per,po_insu_val,po_insu_remark,po_frt_per,po_frt_val,po_frt_remark,other_charge_before,\n" + 
			"other_charge_before_remark , other_charge_after, other_charge_after_remark, total_value, delivery_id, dispatch_id, payment_term_id,po_remark,\n" + 
			"  po_status,  prn_status,prn_copies,  po_header.ind_id,  ind_no, user_id,approv_status, po_header.del_status from po_header,indent i where i.ind_m_id=po_header.ind_id and po_header.del_status=1 and po_header.po_type=:poType and po_header.po_status=:status",nativeQuery=true)
	List<GetPoHeader> findByPoTypeAndPoStatusAndDelStatus(@Param("poType")int poType,@Param("status") int status);

}

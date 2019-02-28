package com.ats.shiv.model.rejection.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.mrn.GetMrnDetailRej;

public interface GetMrnDetailRejRepo extends JpaRepository<GetMrnDetailRej, Integer> {

	@Query(value = "SELECT m.*,i.item_desc as item_name ,i.item_code FROM t_mrn_detail m,m_item i WHERE  m.item_id=i.item_id AND m.mrn_id =:status  AND m.del_status=1 and m.reject_qty>0", nativeQuery = true)
	List<GetMrnDetailRej> getMrnDetailByList(@Param("status") int status);
	
	
	//Sachin Query for Auto MRN Issue Based on T_req BOM new Inteerface
	@Query(value = "SELECT m.*,i.item_desc as item_name ,i.item_code FROM t_mrn_detail m,m_item i "
			+ "WHERE  m.item_id=i.item_id AND m.item_id =:rmId  AND m.del_status=1 and m.remaining_qty >0", nativeQuery = true)
	List<GetMrnDetailRej> getMrnDetailForBatchIsuue(@Param("rmId") int rmId);
}

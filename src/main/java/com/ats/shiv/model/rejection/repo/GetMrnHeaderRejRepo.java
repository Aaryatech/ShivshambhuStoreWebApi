package com.ats.shiv.model.rejection.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.mrn.GetMrnHeaderRej;

public interface GetMrnHeaderRejRepo extends JpaRepository<GetMrnHeaderRej, Integer> {

	@Query(value = "SELECT m.*, v.vendor_name FROM t_mrn_header m,m_vendor v WHERE m.vendor_id=v.vendor_id AND  m.mrn_id IN(:status)  AND m.del_status=1", nativeQuery = true)
	List<GetMrnHeaderRej> getMrnHeaderByList(@Param("status") List<Integer> status);

}

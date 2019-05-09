package com.ats.shivsapi.repository.indent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shivsapi.model.report.IndentReportDetail;

public interface IndentReportDetailRepository extends JpaRepository<IndentReportDetail, Integer> {

	@Query(value = "SELECT d.*,i.item_code,i.item_max_level FROM indtrans d,m_item i WHERE d.ind_m_id IN(:indentIdList) AND d.del_status=1 AND i.item_id=d.item_id", nativeQuery = true)
	List<IndentReportDetail> getIndentReportDetailList(@Param("indentIdList") List<Integer> indentIdList);

}

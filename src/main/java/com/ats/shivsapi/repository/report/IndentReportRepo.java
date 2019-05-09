package com.ats.shivsapi.repository.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shivsapi.model.report.IndentReport;

public interface IndentReportRepo extends JpaRepository<IndentReport, Integer> {

	@Query(value = "SELECT h.*,c.cat_desc,a.acc_head_desc,p.dept_desc,s.sub_dept_desc,t.type_name  FROM indent h,m_category c,m_account_head a,m_dept p ,m_sub_dept s,type t WHERE h.ind_m_id IN(:indentIdList) AND h.del_status=1 AND h.dept_id=p.dept_id AND h.sub_dept_id=s.sub_dept_id AND h.cat_id=c.cat_id AND a.acc_head_id=h.achd_id \r\n"
			+ " and t.type_id=h.ind_m_type", nativeQuery = true)
	List<IndentReport> getIndentHeaderReportList(@Param("indentIdList") List<Integer> indentIdList);

	//
}

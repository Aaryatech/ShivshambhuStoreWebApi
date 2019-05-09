package com.ats.shivsapi.repository.indent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.shivsapi.model.indent.GetIndent;
import com.ats.shivsapi.model.indent.IndentReport;

@Repository
public interface GetIndentRepo extends JpaRepository<GetIndent, Integer> {

	@Query(value = " SELECT indent.cat_id, indent.ind_m_id,indent.ind_m_no,indent.ind_m_date,indent.ind_isdev,indent.ind_ismonthly,indent.ind_m_type,indent.achd_id ,"
			+ "indent.dept_id,indent.sub_dept_id, m_account_head.acc_head_desc, "
			+ " m_category.cat_desc,indent.ind_m_status,indent.ind_remark,concat(m_dept.dept_code,' - ',m_dept.dept_desc) as dept_code, "
			+ "concat(m_sub_dept.sub_dept_code,' - ',m_sub_dept.sub_dept_desc) as sub_dept_code,indent.apprv_remark1,indent.apprv_remark2 FROM indent,m_category,m_account_head,m_dept,"
			+ "m_sub_dept  WHERE indent.ind_m_date BETWEEN :fromDate AND :toDate "
			+ " AND indent.cat_id=m_category.cat_id AND indent.ind_m_status IN (:status) AND m_account_head.acc_head_id=indent.achd_id "
			+ "and indent.del_status=1 and m_dept.dept_id=indent.dept_id "
			+ "and m_sub_dept.sub_dept_id=indent.sub_dept_id order by indent.ind_m_date,indent.ind_m_no", nativeQuery = true)

	List<GetIndent> getIndent(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("status") List<String> status);
	
	@Query(value = "SELECT\n" + 
			"        indent.cat_id,\n" + 
			"        indent.ind_m_id,\n" + 
			"        indent.ind_m_no,\n" + 
			"        indent.ind_m_date,\n" + 
			"        indent.ind_isdev,\n" + 
			"        indent.ind_ismonthly,\n" + 
			"        indent.ind_m_type,\n" + 
			"        indent.achd_id ,\n" + 
			"        indent.dept_id,\n" + 
			"        indent.sub_dept_id,\n" + 
			"        m_account_head.acc_head_desc,\n" + 
			"        m_category.cat_desc,\n" + 
			"        indent.ind_m_status,\n" + 
			"        indent.ind_remark,\n" + 
			"        concat(m_dept.dept_code,' - ',m_dept.dept_desc) as dept_code,\n" + 
			"        concat(m_sub_dept.sub_dept_code,' - ',m_sub_dept.sub_dept_desc) as sub_dept_code "
			+ " ,indent.apprv_remark1,indent.apprv_remark2 FROM\n" + 
			"        indent,\n" + 
			"        m_category,\n" + 
			"        m_account_head,\n" + 
			"        m_dept,\n" + 
			"        m_sub_dept\n" + 
			"    WHERE\n" + 
			"        indent.ind_m_date <= :toDate  \n" + 
			"        AND indent.cat_id=m_category.cat_id \n" + 
			"        AND indent.ind_m_status IN (:status) \n" + 
			"        AND m_account_head.acc_head_id=indent.achd_id \n" + 
			"        and indent.del_status=1\n" + 
			"        and m_dept.dept_id=indent.dept_id\n" + 
			"        and m_sub_dept.sub_dept_id=indent.sub_dept_id\n" + 
			"    order by\n" + 
			"        indent.ind_m_date,\n" + 
			"        indent.ind_m_no", nativeQuery = true) 
	List<GetIndent> getIndentsForApproval(@Param("toDate") String toDate,
			@Param("status") List<String> status);//same query for apr 1 and apr 2 list change status list while sending status param

}

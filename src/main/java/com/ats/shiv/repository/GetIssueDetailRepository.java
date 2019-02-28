package com.ats.shiv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.GetIssueDetail;

public interface GetIssueDetailRepository extends JpaRepository<GetIssueDetail, Integer>{

	
	@Query(value=("select\r\n" + 
			"        id.*,\r\n" + 
			"        CONCAT(i.item_code,\r\n" + 
			"        '-',\r\n" + 
			"        i.item_desc) as item_code,\r\n" + 
			"        CONCAT(g.grp_code,\r\n" + 
			"        ' ',\r\n" + 
			"        g.grp_desc) as grp_code,\r\n" + 
			"        d.dept_code,\r\n" + 
			"        sd.sub_dept_code,\r\n" + 
			"        a.acc_head_desc,\r\n" + 
			"        pd.landing_cost/pd.item_qty as item_rate \r\n" + 
			"    from\r\n" + 
			"        item_issue_detail id,\r\n" + 
			"        m_item_group g,\r\n" + 
			"        m_dept d,\r\n" + 
			"        m_sub_dept sd,\r\n" + 
			"        m_account_head a,\r\n" + 
			"        m_item i,\r\n" + 
			"        t_mrn_detail md,\r\n" + 
			"        po_detail pd\r\n" + 
			"    where\r\n" + 
			"        id.issue_id=:issueId \r\n" + 
			"        and id.del_status = 1 \r\n" + 
			"        and g.grp_id = id.item_group_id \r\n" + 
			"        and d.dept_id = id.dept_id \r\n" + 
			"        and sd.sub_dept_id = id.sub_dept_id \r\n" + 
			"        and a.acc_head_id=id.acc_head \r\n" + 
			"        and id.item_id = i.item_id\r\n" + 
			"        and md.mrn_detail_id = id.mrn_detail_id\r\n" + 
			"        and pd.po_detail_id = md.po_detail_id"),nativeQuery=true)
	List<GetIssueDetail> findByIssueId(@Param("issueId") int issueId);
	 
}

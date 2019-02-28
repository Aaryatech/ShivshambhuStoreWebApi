package com.ats.shiv.repository.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.report.IndentReport;
import com.ats.shiv.model.report.POReport;

public interface POReportRepository extends JpaRepository<POReport, Integer> {

	@Query(value = "SELECT h.*,v.vendor_name,v.vendor_code,v.vendor_add1,v.vendor_gst_no,x.tax_desc ,t.delivery_desc ,dp.disp_mode_desc ,pt.pymt_desc,id.ind_m_date FROM po_header h ,m_vendor v,m_tax_form x,m_delivery_terms t,m_dispatch_mode dp ,m_payment_terms pt,indent id WHERE h.po_id IN(:poIdList) AND h.del_status=1 AND v.vendor_id=h.vend_id AND x.tax_id=h.po_tax_id AND t.delivery_term_id=h.delivery_id AND h.dispatch_id=dp.disp_mode_id AND h.payment_term_id=pt.pymt_term_id and id.ind_m_id = h.ind_id "
			+ "", nativeQuery = true)
	List<POReport> getPOReportHeaderList(@Param("poIdList") List<Integer> poIdList);

}

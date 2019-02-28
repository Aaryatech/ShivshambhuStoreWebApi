package com.ats.shiv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shiv.model.IndentValueLimit;

public interface IndentValueLimitRepository extends JpaRepository<IndentValueLimit, Integer>{

	
	/*@Query(value="select\r\n" + 
			"        indtrans.item_id,\r\n" + 
			"        sum(indtrans.ind_qty) as qty,\r\n" + 
			"        po_detail.item_rate as rate  \r\n" + 
			"    from\r\n" + 
			"        indtrans ,\r\n" + 
			"        indent,\r\n" + 
			"        po_detail   \r\n" + 
			"    inner join\r\n" + 
			"        (\r\n" + 
			"            select\r\n" + 
			"                po_detail.item_id,\r\n" + 
			"                max(po_detail.po_detail_id) as latest     \r\n" + 
			"            from\r\n" + 
			"                po_detail,\r\n" + 
			"                po_header \r\n" + 
			"            WHERE\r\n" + 
			"                po_header.po_id =po_detail.po_id \r\n" + 
			"                AND po_header.del_status=1       \r\n" + 
			"            group by\r\n" + 
			"                po_detail.item_id  \r\n" + 
			"        ) g    \r\n" + 
			"            on (\r\n" + 
			"                po_detail.po_detail_id = g.latest \r\n" + 
			"                and po_detail.item_id = g.item_id\r\n" + 
			"            )    \r\n" + 
			"    WHERE\r\n" + 
			"        indtrans.item_id=po_detail.item_id \r\n" + 
			"        AND indent.ind_m_id=indtrans.ind_m_id \r\n" + 
			"        and indent.cat_id=:catId \r\n" + 
			"        AND indent.ind_m_type=:typeId \r\n" + 
			"        AND indtrans.ind_d_status IN (:status) and indent.ind_m_status in (:status)\r\n" + 
			"        AND indtrans.ind_m_id = indent.ind_m_id \r\n" + 
			"        AND indent.ind_m_date BETWEEN :fromDate AND :toDate \r\n" + 
			"        and indent.del_status=1 and indtrans.del_status=1\r\n" + 
			"    GROUP BY\r\n" + 
			"        indtrans.item_id",nativeQuery=true)
	List<IndentValueLimit> getIndentValueLimit(@Param("fromDate") String fromDate,@Param("toDate") String toDate,
			@Param("typeId")String typeId,@Param("catId") String catId,@Param("status") List<Integer> status);*/
	
	@Query(value="select id.item_id,sum(id.ind_qty) as qty,i.item_cl_rate as rate from indtrans id,indent ih,m_item i where id.del_status=1 and id.ind_d_status in (:detailStatus) "
			+ "and id.ind_m_id=ih.ind_m_id and ih.ind_m_status in (:status) and ih.del_status=1 and ih.ind_m_type=:typeId and ih.cat_id=:catId "
			+ "and i.item_id=id.item_id and ih.ind_m_date between :fromDate and :toDate group by id.item_id",nativeQuery=true)
	List<IndentValueLimit> getIndentValueLimit(@Param("fromDate") String fromDate,@Param("toDate") String toDate,
			@Param("typeId")String typeId,@Param("catId") String catId,@Param("status") List<Integer> status,@Param("detailStatus") List<Integer> detailStatus);

}

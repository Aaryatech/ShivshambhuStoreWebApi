package com.ats.shivsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.shivsapi.model.GetDamage;

public interface GetDamageRepository extends JpaRepository<GetDamage, Integer>{

	@Query(value = "select d.*,i.item_code,i.item_desc from t_damage d,m_item i where d.item_id = i.item_id "
			+ "and d.date between :fromDate and :toDate and d.del_status=1", nativeQuery = true)
	List<GetDamage> getDamageList(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "select d.*,i.item_code,i.item_desc from t_damage d,m_item i where d.item_id = i.item_id "
			+ "and d.damage_id =:damageId ", nativeQuery = true)
	GetDamage getDamageById(@Param("damageId") int damageId);

}

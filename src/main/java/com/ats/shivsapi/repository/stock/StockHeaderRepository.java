package com.ats.shivsapi.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shivsapi.model.StockHeader;

public interface StockHeaderRepository extends JpaRepository<StockHeader, Integer>{

	StockHeader findByStatusAndDelStatus(int status, int delStatus);

}

package com.ats.shiv.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shiv.model.StockHeader;

public interface StockHeaderRepository extends JpaRepository<StockHeader, Integer>{

	StockHeader findByStatusAndDelStatus(int status, int delStatus);

}

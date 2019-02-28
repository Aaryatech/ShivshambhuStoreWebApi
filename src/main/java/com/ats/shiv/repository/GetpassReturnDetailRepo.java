package com.ats.shiv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shiv.model.GetpassReturnDetail;

public interface GetpassReturnDetailRepo extends JpaRepository<GetpassReturnDetail, Integer> {

	List<GetpassReturnDetail> findByReturnId(int returnId);

}

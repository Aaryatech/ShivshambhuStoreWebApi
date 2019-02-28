package com.ats.shiv.model.rejection.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shiv.model.rejection.RejectionMemoDetail;

public interface RejectionMemoDetailRepo extends JpaRepository<RejectionMemoDetail, Integer> {

	List<RejectionMemoDetail> findByRejectionId(int rejectionId);

}

package com.ats.shivsapi.model.rejection.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shivsapi.model.rejection.RejectionMemoDetail;

public interface RejectionMemoDetailRepo extends JpaRepository<RejectionMemoDetail, Integer> {

	List<RejectionMemoDetail> findByRejectionId(int rejectionId);

}

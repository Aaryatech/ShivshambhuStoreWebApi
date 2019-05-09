package com.ats.shivsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ats.shivsapi.model.IssueHeader;

public interface IssueHeaderRepository extends JpaRepository<IssueHeader, Integer>{
	
	@Transactional
	@Modifying
	@Query("UPDATE IssueHeader SET delete_status=0 WHERE issue_id=:issueId")
	int delete(@Param("issueId") int issueId);

	List<IssueHeader> findByDeleteStatus(int i);

	@Transactional
	@Modifying
	@Query("UPDATE IssueHeader SET status=:status WHERE issue_id=:issueId")
	int updateStatusWhileApprov(@Param("issueId") int issueId, @Param("status") int status);

}

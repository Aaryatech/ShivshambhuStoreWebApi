package com.ats.shivsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shivsapi.model.DocType;

public interface DocTypeRepository extends JpaRepository<DocType, Integer>{

	DocType findByDocId(int docTypeId);

	DocType findByDocName(String docName);

}

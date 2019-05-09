package com.ats.shivsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shivsapi.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	Company findByComId(int i);

}

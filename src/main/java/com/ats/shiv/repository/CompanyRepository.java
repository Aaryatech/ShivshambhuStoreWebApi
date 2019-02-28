package com.ats.shiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shiv.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	Company findByComId(int i);

}

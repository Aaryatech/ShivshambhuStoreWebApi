package com.ats.shiv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shiv.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer>{

	List<Type> findAllByDelStatus(int i);

}

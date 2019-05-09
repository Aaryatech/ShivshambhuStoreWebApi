package com.ats.shivsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shivsapi.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer>{

	List<Type> findAllByDelStatus(int i);

}

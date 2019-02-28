package com.ats.shiv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shiv.model.SettingValue;

public interface SettingValueRepository extends JpaRepository<SettingValue, Integer>{

	SettingValue findAllByName(String name);

}

package com.ats.shivsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.shivsapi.model.SettingValue;

public interface SettingValueRepository extends JpaRepository<SettingValue, Integer>{

	SettingValue findAllByName(String name);

}

package com.ats.shivsapi.service;

import java.util.List;

import com.ats.shivsapi.model.AccessRightModule;
import com.ats.shivsapi.model.AssignRoleDetailList;
import com.ats.shivsapi.model.Info;
import com.ats.shivsapi.model.User;

public interface AccessRightService {

	List<AccessRightModule> getAllModulAndSubModule();

	Info saveAssignRole(AssignRoleDetailList assignRoleDetailList);

	List<AssignRoleDetailList> getAllAccessRole();
	
	Info updateRoleIdByEmpId(int id, int roleId);

	List<User> getAllUser();

	String getRoleJson(int usrId);

	
	
}

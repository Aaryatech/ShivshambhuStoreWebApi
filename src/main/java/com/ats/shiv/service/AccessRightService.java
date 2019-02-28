package com.ats.shiv.service;

import java.util.List;

import com.ats.shiv.model.AccessRightModule;
import com.ats.shiv.model.AssignRoleDetailList;
import com.ats.shiv.model.Info;
import com.ats.shiv.model.User;

public interface AccessRightService {

	List<AccessRightModule> getAllModulAndSubModule();

	Info saveAssignRole(AssignRoleDetailList assignRoleDetailList);

	List<AssignRoleDetailList> getAllAccessRole();
	
	Info updateRoleIdByEmpId(int id, int roleId);

	List<User> getAllUser();

	String getRoleJson(int usrId);

	
	
}

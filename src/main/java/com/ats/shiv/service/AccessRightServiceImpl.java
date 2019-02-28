package com.ats.shiv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.shiv.model.AccessRightModule;
import com.ats.shiv.model.AccessRightSubModule;
import com.ats.shiv.model.AssignRoleDetailList;
import com.ats.shiv.model.Info;
import com.ats.shiv.model.User;
import com.ats.shiv.repository.EmployeeRepository;
import com.ats.shiv.repository.GetEmployeeRepository;
import com.ats.shiv.repository.UserRepository;
import com.ats.shiv.repository.AccessRight.AccessRightModuleRepository;
import com.ats.shiv.repository.AccessRight.AccessRightSubModuleRepository;
import com.ats.shiv.repository.AccessRight.AssignRoleDetailListRepository;
@Service
public class AccessRightServiceImpl implements AccessRightService{

	
	@Autowired
	AccessRightModuleRepository accessRightModuleRepository;
	
	@Autowired
	AccessRightSubModuleRepository accessRightSubModuleRepository;
	
	@Autowired
	AssignRoleDetailListRepository assignRoleDetailListRepository;
	
	/*@Autowired
	GetEmployeeRepository getEmployeeRepository;*/
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public List<AccessRightModule> getAllModulAndSubModule() {
		 
		List<AccessRightModule> accessRightModuleList=accessRightModuleRepository.getModule();
		
		
		for(int i=0;i<accessRightModuleList.size();i++)
		{
			
			List<AccessRightSubModule> accessRightSubModuleList=accessRightSubModuleRepository.getSubModule(accessRightModuleList.get(i).getModuleId());
			accessRightModuleList.get(i).setAccessRightSubModuleList(accessRightSubModuleList);
			
		}
		
		return accessRightModuleList;
	}
	@Override
	public Info saveAssignRole(AssignRoleDetailList assignRoleDetailList) {
		 
		Info info=new Info();
		
		assignRoleDetailList=assignRoleDetailListRepository.save(assignRoleDetailList);
		
		if(assignRoleDetailList!=null)
		{
			info.setError(false);
			info.setMessage("success");
		}
		else {
			 
				info.setError(true);
				info.setMessage("failed");
			 
		}
		
		return info;
	}
	@Override
	public List<AssignRoleDetailList> getAllAccessRole() {
		  
		
		return assignRoleDetailListRepository.findByDelStatus(0);
	}

	@Override
	public Info updateRoleIdByEmpId(int id, int roleId) {
		 
		
		Info info=new Info();
		try
		{
			int update = userRepository.updateRoleIdByEmpId(id,roleId);
			if(update==0)
			{
				info.setError(true);
				info.setMessage("Employee role assign Failed");
			}
			else
			{
				info.setError(false);
				info.setMessage("Employee   role assign Successfully");
			}
			
		}catch(Exception e)
		{
			info.setError(true);
			info.setMessage("Employee  role assign Failed EXC");
		}
		return info;
	}
	@Override
	public List<User> getAllUser() {
		
		return userRepository.findByDelStatus(0);
	}
	@Override
	public String getRoleJson(int usrId) {
		AssignRoleDetailList assignRoleDetailList=new AssignRoleDetailList();
		try {
			System.out.println("user Id : "+usrId);
		assignRoleDetailList=assignRoleDetailListRepository.getRoleJson(usrId);
		
		System.out.println("assignRoleDetailList  : "+assignRoleDetailList.toString());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String json=null;
		if(assignRoleDetailList!=null) {
		 json=new String(assignRoleDetailList.getRoleJson());
		}
		
		return json;
	}
	
	
}

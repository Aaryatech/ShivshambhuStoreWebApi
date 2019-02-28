package com.ats.shiv.service;

import java.util.List;

import com.ats.shiv.model.Department;
import com.ats.shiv.model.GetUserType;
import com.ats.shiv.model.Info;
import com.ats.shiv.model.LoginResponse;
import com.ats.shiv.model.User;



public interface UserService {

	public LoginResponse findUserByUsername(String username,String password);

	public String save(User user);

	public Info insertUser(User user);

	public List<Department> getAllDept();

	public List<GetUserType> getAllUserType();
}

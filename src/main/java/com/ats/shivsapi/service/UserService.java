package com.ats.shivsapi.service;

import java.util.List;

import com.ats.shivsapi.model.Department;
import com.ats.shivsapi.model.GetUserType;
import com.ats.shivsapi.model.Info;
import com.ats.shivsapi.model.LoginResponse;
import com.ats.shivsapi.model.User;



public interface UserService {

	public LoginResponse findUserByUsername(String username,String password);

	public String save(User user);

	public Info insertUser(User user);

	public List<Department> getAllDept();

	public List<GetUserType> getAllUserType();
}

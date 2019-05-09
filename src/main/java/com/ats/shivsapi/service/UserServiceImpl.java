package com.ats.shivsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.shivsapi.model.Department;
import com.ats.shivsapi.model.ErrorMessage;
import com.ats.shivsapi.model.GetUserType;
import com.ats.shivsapi.model.Info;
import com.ats.shivsapi.model.LoginResponse;
import com.ats.shivsapi.model.User;
import com.ats.shivsapi.repository.DepartmentRepository;
import com.ats.shivsapi.repository.GetUserTypeRepository;
import com.ats.shivsapi.repository.UserRepository;
import com.ats.shivsapi.util.JsonUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	GetUserTypeRepository getUserTypeRepository;
	
	String jsonUser = "{}";
	User user = null;
	ErrorMessage errorMessage;

	@Override
	public LoginResponse findUserByUsername(String username, String password) {

		String dbUsername = null;
		String dbPassword = null;
        LoginResponse loginResponse=null;
		try {
			user = userRepository.findByUsernameAndDelStatus(username,0);
			dbUsername = user.getUsername();
			dbPassword = user.getPassword();
		} catch (Exception e) {
			loginResponse=new LoginResponse();
            errorMessage=new ErrorMessage();
			errorMessage.setError(true);
			errorMessage.setMessage("User is not registered");
			
			loginResponse.setErrorMessage(errorMessage);
			loginResponse.setUser(user);
			
		
		}
		try {
			if (user == null || username == null||username.equalsIgnoreCase("")) {
				
				loginResponse=new LoginResponse();

				errorMessage=new ErrorMessage();
				errorMessage.setError(true);
				errorMessage.setMessage("User is not registered");
				
				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);
				
			} else if (password == null||password.equalsIgnoreCase("")) {
				
				loginResponse=new LoginResponse();
	            errorMessage=new ErrorMessage();

				errorMessage.setError(true);
				errorMessage.setMessage("Please enter Password");
				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);

			} else if (dbUsername.equals(username) && dbPassword.equals(password)) {
				
				loginResponse=new LoginResponse();
	            errorMessage=new ErrorMessage();

				errorMessage.setError(false);
				errorMessage.setMessage("User Logged in Successfully");
				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);

			} else if (dbUsername.equals(username) && dbPassword != password) {
				
				loginResponse=new LoginResponse();
	            errorMessage=new ErrorMessage();

				errorMessage.setError(true);
				errorMessage.setMessage("Invalid Password");
				loginResponse.setErrorMessage(errorMessage);
				loginResponse.setUser(user);

			}
		} catch (Exception e) {

			loginResponse=new LoginResponse();
            errorMessage=new ErrorMessage();

			errorMessage.setError(true);
			errorMessage.setMessage("User is not registered");
			loginResponse.setErrorMessage(errorMessage);
			loginResponse.setUser(user);

		}
		return loginResponse;

	}

	@Override
	public String save(User user) {
		try {
			User tempUser =null;
			try {	if (user != null||user.getUsername()==""||user.getPassword()=="") {
				tempUser = userRepository.findByUsername(user.getUsername());
			}
			else
			{
				errorMessage.setError(false);
				errorMessage.setMessage("UserForm fields are empty");
			}
		
			} catch (Exception e) {
				
				errorMessage.setError(true);
				errorMessage.setMessage("Exception occured while inserting NewUser");
				jsonUser = JsonUtil.javaToJson(errorMessage);

			}

			if (tempUser == null) {
				user = userRepository.save(user);
				errorMessage.setError(false);
				errorMessage.setMessage("Record Inserted Successfully");

				jsonUser = JsonUtil.javaToJson(errorMessage);
			} else {

				errorMessage.setError(true);
				errorMessage.setMessage("User with this username is already registered");

				jsonUser = JsonUtil.javaToJson(errorMessage);
			}
		} catch (Exception e) {
			errorMessage.setError(true);
			errorMessage.setMessage("Exception occured while inserting NewUser");
			jsonUser = JsonUtil.javaToJson(errorMessage);
			
		}
		return jsonUser;
	}

	@Override
	public Info insertUser(User user) {
		 
		Info info=new Info();
		user=userRepository.save(user);
		if(user!=null)
		{
			info.setError(false);
			info.setMessage("success");
		}
		{
			info.setError(true);
			info.setMessage("failed");
		}
		return info;
	}

	@Override
	public List<Department> getAllDept() {
		 
	return departmentRepository.getAllDept();
	}

	@Override
	public List<GetUserType> getAllUserType() {
	 
		return getUserTypeRepository.getType();
	}
	
	
}

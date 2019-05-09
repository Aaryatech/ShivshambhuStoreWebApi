package com.ats.shivsapi.model.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.shivsapi.common.DateConvertor;
import com.ats.shivsapi.model.Info;


@RequestMapping("/UserMstController")
@RestController
public class UserMstController {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	GetUserRepo getUserRepo;

/*	// ----------------------------------------User----------------------------------------------------

		@RequestMapping(value = { "/saveUser" }, method = RequestMethod.POST)
		public @ResponseBody User saveUser(@RequestBody User user) {

			User res = new User();

			try {

				res = userRepo.saveAndFlush(user);

			} catch (Exception e) {

				e.printStackTrace();

			}
			return res;

		}

		@RequestMapping(value = { "/saveUniqueUser" }, method = RequestMethod.POST)
		public @ResponseBody Info saveUniqueUser(@RequestParam("usrMob") String usrMob) {

			User res = new User();
			Info info = new Info();

			try {
				res = userRepo.findByUsrMobAndDelStatus(usrMob, 1);
				if (res == null) {

					info.setError(false);
					info.setMessage("New User");
					System.out.println("In If");
				} else {
					info.setError(true);
					info.setMessage("User Mobile No Already Exist");
					System.out.println("In else");
				}

			} catch (Exception e) {

				e.printStackTrace();
				info.setError(true);
				info.setMessage(" In Exception");

			}
			return info;

		}

		@RequestMapping(value = { "/getUserByUserId" }, method = RequestMethod.POST)
		public @ResponseBody User getUserByUserId(@RequestParam("userId") int userId) {

			User user = new User();

			try {
				user = userRepo.findByUserIdAndDelStatus(userId, 1);
				user.setUsrDob(DateConvertor.convertToDMY(user.getUsrDob()));

			} catch (Exception e) {

				e.printStackTrace();

			}
			return user;

		}

		@RequestMapping(value = { "/getAllUserList" }, method = RequestMethod.GET)
		public @ResponseBody List<User> getAllUserList() {

			List<User> userList = new ArrayList<User>();

			try {

				userList = userRepo.findByDelStatusOrderByUserIdDesc(1);

			} catch (Exception e) {

				e.printStackTrace();

			}
			return userList;

		}

		@RequestMapping(value = { "/getUserList" }, method = RequestMethod.GET)
		public @ResponseBody List<GetUser> getUserList1() {

			List<GetUser> userList = new ArrayList<GetUser>();

			try {

				userList = getUserRepo.getUserList();

			} catch (Exception e) {

				e.printStackTrace();

			}
			return userList;

		}

		@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteUser(@RequestParam("userId") int userId) {

			Info info = new Info();

			try {
				int delete = userRepo.deleteUser(userId);

				if (delete == 1) {
					info.setError(false);
					info.setMessage("successfully Deleted");
				} else {
					info.setError(true);
					info.setMessage(" Deleted to Delete");
				}

			} catch (Exception e) {

				e.printStackTrace();
				info.setError(true);
				info.setMessage(" Deleted to Delete");

			}
			return info;

		}

		@RequestMapping(value = { "/updateUserToken" }, method = RequestMethod.POST)
		public @ResponseBody Info updateUserToken(@RequestParam("userId") int userId, @RequestParam("token") String token) {

			Info info = new Info();

			try {
				int delete = userRepo.updatetoken(userId, token);

				if (delete == 1) {
					info.setError(false);
					info.setMessage("successfully Updated");
				} else {
					info.setError(true);
					info.setMessage(" Deleted to Delete");
				}

			} catch (Exception e) {

				e.printStackTrace();
				info.setError(true);
				info.setMessage(" Deleted to Delete");

			}
			return info;

		}

		@RequestMapping(value = { "/deleteMultiUser" }, method = RequestMethod.POST)
		public @ResponseBody Info deleteMultiUser(@RequestParam("userIds") List<Integer> userIds) {

			Info info = new Info();

			try {
				int delete = userRepo.deleteMultiUser(userIds);

				if (delete >= 1) {
					info.setError(false);
					info.setMessage("successfully Multiple Deleted");
				} else {
					info.setError(true);
					info.setMessage(" Deleted to Delete");
				}

			} catch (Exception e) {

				e.printStackTrace();
				info.setError(true);
				info.setMessage(" Deleted to Delete");

			}
			return info;

		}
		@RequestMapping(value = { "/getUserList" }, method = RequestMethod.GET)
		public @ResponseBody List<GetUser> getUserList() {

			List<GetUser> userList = new ArrayList<GetUser>();

			try {

				userList = getUserRepo.getUserList();

			} catch (Exception e) {

				e.printStackTrace();

			}
			return userList;

		}*/

		
		// ---------- User Login--------------------

		@RequestMapping(value = { "/loginUser" }, method = RequestMethod.POST)
		public @ResponseBody LoginResUser loginUser(@RequestParam("usrMob") String usrMob,
				@RequestParam("userPass") String userPass) {

			LoginResUser loginResponse = new LoginResUser();
			try {

				User mu = userRepo.findByUsrMobAndUserPassAndDelStatus(usrMob, userPass, 1);
				if (mu == null) {
					loginResponse.setError(true);
					loginResponse.setMsg("login Failed");
				} else {
					loginResponse.setError(false);
					loginResponse.setMsg("login successfully");
					loginResponse.setUser(mu);
				}

			} catch (Exception e) {

				e.printStackTrace();
				loginResponse.setError(true);
				loginResponse.setMsg("login Failed");
			}

			return loginResponse;
		}

		
		

}

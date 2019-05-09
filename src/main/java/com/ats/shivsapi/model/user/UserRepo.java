package com.ats.shivsapi.model.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE User SET del_status=0  WHERE user_id=:userId")
	int deleteUser(@Param("userId") int userId);

	User findByUserIdAndDelStatus(int userId, int i);

	List<User> findByDelStatusOrderByUserIdDesc(int i);

	User findByUsrMobAndUserPassAndDelStatus(String usrMob, String userPass, int i);

	@Transactional
	@Modifying
	@Query("UPDATE User SET delStatus=0  WHERE user_id IN(:userIds)")
	int deleteMultiUser(@Param("userIds") List<Integer> userIds);

	@Transactional
	@Modifying
	@Query("UPDATE User SET deviceToken=:token  WHERE user_id=:userId")
	int updatetoken(@Param("userId") int userId, @Param("token") String token);

	User findByUsrMobAndDelStatus(String usrMob, int i);

	User findByUsrMobAndUserPassAndDelStatusAndDeptId(String usrMob, String userPass, int i, int deptId);

}

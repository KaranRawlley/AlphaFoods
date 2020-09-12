package com.mind.recipereview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mind.recipereview.entity.User;

@Repository
public interface iUserDao extends JpaRepository<User, Integer> {
	
	// Method For Finding User By Email
	public User findByEmail(String email);
	// Method For Finding User By UserName
	public User findByUserName(String username);
	
	
	
	

}

package com.mind.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mind.entity.User;

@Repository
public interface iUserDao extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	

}

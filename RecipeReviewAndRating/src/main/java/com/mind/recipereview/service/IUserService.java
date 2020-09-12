package com.mind.recipereview.service;

import java.util.List;

import com.mind.recipereview.entity.User;

public interface IUserService {
	
	public User save(User user);
	public List<User> findAll();
	public User findById(Integer userId);
	public  void deleteById(Integer userId);
	public User findByEmail(String email);
	public User findByUserName(String username);

}

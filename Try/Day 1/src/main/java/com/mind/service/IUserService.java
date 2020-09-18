package com.mind.service;

import java.util.List;

import com.mind.entity.User;

public interface IUserService {
	
	public User save(User user);
	public List<User> findAll();
	public User findById(Integer userId);
	public  void deleteById(Integer userId);
	public User findByEmail(String email);

}

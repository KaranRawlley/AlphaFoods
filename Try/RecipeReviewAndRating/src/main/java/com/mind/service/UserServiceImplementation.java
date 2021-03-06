
package com.mind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mind.dao.iUserDao;
import com.mind.entity.User;
@Service
public class UserServiceImplementation implements IUserService {
	
	
	@Autowired
	private iUserDao userDao;
 
	@Override
	public User save(User user) {
		
		User theUser= userDao.save(user);
		return theUser;
	}

	@Override
	public List<User> findAll() {
		
		List<User>allUsers= userDao.findAll();
		return allUsers;
	}

	@Override
	public User findById(Integer userId) {
		
		Optional<User>theUser =userDao.findById(userId);
		User user=null;
		if(theUser.isPresent()) 
			user=theUser.get();
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find User id - " + userId);
		}
		return user;
	}

	@Override
	public void deleteById(Integer userId) {
		
		Optional<User>theUser =userDao.findById(userId);
		if(theUser.isPresent()) 
			userDao.deleteById(userId);
		
		else 
			throw new RuntimeException("Did not find student id - " + userId);
		
	}

	@Override
	public User findByEmail(String email) {
		User user=userDao.findByEmail(email);
		return user;
	}

}

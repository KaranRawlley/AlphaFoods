package com.mind.recipereview.config;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mind.recipereview.entity.User;
import com.mind.recipereview.service.IUserService;
@Service
public class MyUserDetailService implements UserDetailsService
{
	
	@Autowired
	IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) 
			                              throws UsernameNotFoundException {
		
		System.out.println("In My User Detail Service");
		
		User user=userService.findByUserName(username);
       if(user == null)
			throw new UsernameNotFoundException("  Not Found"+username);
		MyUserDetail myuserdetail = new MyUserDetail(user);
		return myuserdetail;
	}
	

}
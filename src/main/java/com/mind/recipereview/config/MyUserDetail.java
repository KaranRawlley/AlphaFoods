package com.mind.recipereview.config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.mind.recipereview.entity.Role;
import com.mind.recipereview.entity.User;
public class MyUserDetail implements UserDetails 
{
	// serial id is hown by default
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	
	private List<GrantedAuthority> authorities=new ArrayList<>();
	
	public MyUserDetail(User user)
	{
		System.out.println("In My User Detail ");
		List<Role> roles=user.getRoles();
		this.userName=user.getUserName();
		this.password=user.getPassword();
		
		for(Role role:roles) {
			/** .name() Will Convert Enum To String because GrantedAuthority Will return String 
			 * That Why We Convert Enum To String
			 */
			this.authorities.add(new SimpleGrantedAuthority(role.getName().name()));
		}
		

	}
	public MyUserDetail()
	{
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	 return true;
	}
	@Override
	public boolean isEnabled() {
	return true;
	}

	
}
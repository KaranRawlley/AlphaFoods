package com.mind.recipereview.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mind.recipereview.jwtt.AuthEntryPointJwt;
import com.mind.recipereview.jwtt.AuthTokenFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService userdetailservice;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("In Security Configure");
		auth.userDetailsService(userdetailservice).passwordEncoder(getpasswordencoder());
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public BCryptPasswordEncoder getpasswordencoder() {
		System.out.println("In Security Configure Bycrypt");
		return new BCryptPasswordEncoder();
	}
   @Bean
	public DaoAuthenticationProvider authenticationProvider() {
	   System.out.println("In Security Configure Authentication Provider");
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		// set the custom user details service
		auth.setUserDetailsService(userdetailservice);
		// set the password encoder - bcrypt
		auth.setPasswordEncoder(new BCryptPasswordEncoder()); 
		return auth;
	}

   @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/CookingRecipe/signin").permitAll()
		.antMatchers("/CookingRecipe/signup").permitAll()
		.antMatchers("/upload/AddDress").permitAll()
		.antMatchers("/upload/uploadRecipe").permitAll()
		.antMatchers("/download/signin").permitAll()
		.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}

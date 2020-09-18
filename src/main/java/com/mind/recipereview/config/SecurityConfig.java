package com.mind.recipereview.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService userdetailservice;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailservice).passwordEncoder(getpasswordencoder());
	}
	@Bean
	public BCryptPasswordEncoder getpasswordencoder()
	{
		return new BCryptPasswordEncoder();
	}
   @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userdetailservice); // set the custom user details service
		auth.setPasswordEncoder(new BCryptPasswordEncoder()); // set the password encoder - bcrypt
		return auth;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
        http.authorizeRequests()
	   .and()
	   .formLogin()
	   .loginPage("/CookingRecipe/showMyLoginPage").permitAll()
	   .loginProcessingUrl("/authenticateTheUser")
	   .successHandler(customAuthenticationSuccessHandler)
	   .defaultSuccessUrl("/recipe/findall", true)
	   .and().formLogin()
	   .and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
		//http.apply(new SpringSocialConfigurer()).signupUrl("/signup");

	}

}

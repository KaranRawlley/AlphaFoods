package com.mind.recipereview.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidatorServer implements ConstraintValidator
                                                <ValidPasswordServer, String> {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String PASSWORD_PATTERN = "^(?=.*?\\p{Lu})(?=.*?\\p{Ll})(?=.*?\\d)"
	                                               +"(?=.*?[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]}"
	                                               + ";:'\",<.>/?]).*$";

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		if (email == null) {
			return false;
		}
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}



package com.mind.recipereview.validations;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidatorServer implements ConstraintValidator<ValidPhoneServer, String> {

	private Pattern pattern;
	private Matcher matcher;
	

	@Override
	public boolean isValid(final String phone, final ConstraintValidatorContext context) {
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
		if (phone == null) {
			return false;
		}
		 Matcher m = p.matcher(phone); 
	        return (m.find() && m.group().equals(phone)); 
	}

}

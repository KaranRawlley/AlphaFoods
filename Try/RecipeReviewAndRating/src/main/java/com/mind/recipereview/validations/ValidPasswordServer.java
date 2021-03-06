package com.mind.recipereview.validations;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
@Constraint(validatedBy = EmailValidatorServer.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPasswordServer {
	String message() default "Please Enter a Password In a Combination of SmallLatter and"
			+ "UpperLatter And Special symbol and Digit";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}


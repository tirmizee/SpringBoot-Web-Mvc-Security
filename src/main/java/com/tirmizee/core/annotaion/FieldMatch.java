package com.tirmizee.core.annotaion;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.beanutils.BeanUtils;

import com.tirmizee.core.annotaion.FieldMatch.FieldMatchValidator;

@Documented
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

	String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String firstField();

    String secondField();

    public final class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{

    	private String firstField;
        private String secondField;
    	
		@Override
		public void initialize(FieldMatch constraintAnnotation) {
			firstField = constraintAnnotation.firstField();
			secondField = constraintAnnotation.secondField();
		}

		@Override
		public boolean isValid(Object value, ConstraintValidatorContext context) {
			try {
				
				final Object firstVal = BeanUtils.getProperty(value, firstField);
				final Object secondVal = BeanUtils.getProperty(value, secondField);
				
				return firstVal == null ? secondVal == null : firstVal.equals(secondVal);
			
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				return false;
			}
		}
    	
    }
	
}

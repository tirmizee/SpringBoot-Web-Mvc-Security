package com.tirmizee.core.hibernate.validator;

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

import com.tirmizee.core.hibernate.validator.FieldMatch.FieldMatchValidator;

/**
 * @author Pratya Yeekhaday
 *
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

	String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    String fieldMatch();

    public final class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{

    	private String field;
        private String fieldMatch;
    	
		@Override
		public void initialize(FieldMatch constraintAnnotation) {
			field = constraintAnnotation.field();
			fieldMatch = constraintAnnotation.fieldMatch();
		}

		@Override
		public boolean isValid(Object value, ConstraintValidatorContext context) {
			try {

				final Object fieldVal = BeanUtils.getProperty(value, field);
				final Object fieldMatchVal = BeanUtils.getProperty(value, fieldMatch);
				
				return fieldVal == null ? fieldMatchVal == null : fieldVal.equals(fieldMatchVal);
			
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				return false;
			}
		}
    	
    }
	
}

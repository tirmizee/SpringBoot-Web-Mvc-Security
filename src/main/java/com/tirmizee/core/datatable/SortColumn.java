
package com.tirmizee.core.datatable;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * Annotation for sort datatable
 * 
 * @author pratya yeekhaday
 *
 */
@Retention(RUNTIME)
@Target(FIELD)  
@Inherited
@Documented
public @interface SortColumn { 
	
	@AliasFor("name")
	String[] value() default {}; 
	
}

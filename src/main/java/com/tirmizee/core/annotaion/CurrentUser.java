package com.tirmizee.core.annotaion;


import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Target({PARAMETER, TYPE})
@Retention(RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {}

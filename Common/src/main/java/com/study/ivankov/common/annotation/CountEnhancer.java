package com.study.ivankov.common.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Ivankov_A
 *
 */
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface CountEnhancer {

	// int countToEnhance();

	int value();

}

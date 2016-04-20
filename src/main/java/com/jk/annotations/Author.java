/*
 *
 */
package com.jk.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Author.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Author(name = "Jalal H. Kiswani", date = "1/9/2014", version = "1.0")
public @interface Author {

	/**
	 * Name.
	 *
	 * @return the string
	 */
	String name();

	/**
	 * Date.
	 *
	 * @return the string
	 */
	String date();

	/**
	 * Version.
	 *
	 * @return the string
	 */
	String version();

	/**
	 * Modif user.
	 *
	 * @return the string
	 */
	String modifUser() default "";

	/**
	 * Modify date.
	 *
	 * @return the string
	 */
	String modifyDate() default "";

	/**
	 * Description.
	 *
	 * @return the string
	 */
	String description() default "";
}

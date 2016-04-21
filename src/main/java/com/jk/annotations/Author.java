/*
 * Copyright 2002-2016 Jalal Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jk.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface Author.
 *
 * @author Jalal Kiswani
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Author(name = "Jalal H. Kiswani", date = "1/9/2014", version = "1.0")
public @interface Author {

	/**
	 * Date.
	 *
	 * @return the string
	 */
	String date();

	/**
	 * Description.
	 *
	 * @return the string
	 */
	String description() default "";

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
	 * Name.
	 *
	 * @return the string
	 */
	String name();

	/**
	 * Version.
	 *
	 * @return the string
	 */
	String version();
}

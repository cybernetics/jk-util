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
package com.fs.commons.desktop.validation;

/**
 * Validator that can validate some aspect of a component's model, and indicate
 * problems to the user.
 * <p>
 * Note that the class <code><a href="builtin/Validators.html">Validators</a>
 * </code> provides many built-in validators to perform common tasks.
 *
 * @author Tim Boudreau
 * @param <T>
 *            the generic type
 */
public interface Validator<T> {

	/**
	 * Validate the passed model. If the component is invalid, this method
	 * should add problems to the passed list.
	 *
	 * @param problems
	 *            A list of problems.
	 * @param compName
	 *            The name of the component in question (may be null in some
	 *            cases)
	 * @param model
	 *            The model in question
	 * @return true if no problems were found during validation
	 */
	public boolean validate(Problems problems, String compName, T model);

}

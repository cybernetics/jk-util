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
package com.jk.validation.builtin;

import com.jk.validation.Problems;
import com.jk.validation.Validator;

/**
 *
 * @author Tim Boudreau
 */
class NonNegativeNumberValidator implements Validator<String> {

	/* (non-Javadoc)
	 * @see com.fs.commons.desktop.validation.Validator#validate(com.fs.commons.desktop.validation.Problems, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean validate(final Problems problems, final String compName, final String text) {
		try {
			final double d = Double.parseDouble(text);
			if (d < 0D) {
				final String problem = ValidationBundle.getMessage(NonNegativeNumberValidator.class, "ERR_NEGATIVE_NUMBER", compName); // NOI18N
				problems.add(problem);
				return false;
			}
		} catch (final NumberFormatException e) {
			// do nothing - if someone wants not-a-number validation, they
			// should
			// chain an IntegerDocumentValidator or similar
		}
		return true;
	}
}

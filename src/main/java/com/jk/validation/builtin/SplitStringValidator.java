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
 * A validator of strings that first splits the string in question using the
 * passed regular expression, and then runs another validator over each
 * component string.
 *
 * @author Tim Boudreau
 */
final class SplitStringValidator implements Validator<String> {
	private final String regexp;
	private final Validator<String> other;

	/**
	 * Instantiates a new split string validator.
	 *
	 * @param regexp
	 *            the regexp
	 * @param other
	 *            the other
	 */
	public SplitStringValidator(final String regexp, final Validator<String> other) {
		this.regexp = regexp;
		this.other = other;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SplitStringValidator for " + this.other; // NOI18N
	}

	/* (non-Javadoc)
	 * @see com.fs.commons.desktop.validation.Validator#validate(com.fs.commons.desktop.validation.Problems, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean validate(final Problems problems, final String compName, final String model) {
		final String[] components = model.split(this.regexp);
		boolean result = true;
		for (final String component : components) {
			result &= this.other.validate(problems, compName, component);
		}
		return result;
	}
}

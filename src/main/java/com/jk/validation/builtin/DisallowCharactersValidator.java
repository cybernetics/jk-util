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
final class DisallowCharactersValidator implements Validator<String> {
	private final char[] chars;

	/**
	 * Instantiates a new disallow characters validator.
	 *
	 * @param chars
	 *            the chars
	 */
	public DisallowCharactersValidator(final char[] chars) {
		this.chars = chars;
	}

	private String asString() {
		return "'" + new String(this.chars) + "' "; // NOI18N
	}

	/* (non-Javadoc)
	 * @see com.fs.commons.desktop.validation.Validator#validate(com.fs.commons.desktop.validation.Problems, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean validate(final Problems problems, final String compName, final String model) {
		for (final char c : this.chars) {
			for (final char cc : model.toCharArray()) {
				if (cc == c) {
					problems.add(ValidationBundle.getMessage(DisallowCharactersValidator.class, "DISALLOWED_CHARS", asString(), compName)); // NOI18N
					return false;
				}
			}
		}
		return true;
	}
}

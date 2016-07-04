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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.jk.validation.Problems;
import com.jk.validation.Validator;

/**
 *
 * @author Tim Boudreau
 */
final class OrValidator<T> implements Validator<T> {
	private final List<Validator<T>> validators = new LinkedList<Validator<T>>();

	/**
	 * Instantiates a new or validator.
	 *
	 * @param initial
	 *            the initial
	 */
	public OrValidator(final Validator<T>... initial) {
		this.validators.addAll(Arrays.asList(initial));
	}

	/**
	 * Or.
	 *
	 * @param v
	 *            the v
	 */
	public void or(final Validator<T> v) {
		orImpl(v);
	}

	Validator<T> orImpl(final Validator<T> v) {
		this.validators.add(0, v);
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrValidator for " + this.validators;
	}

	/* (non-Javadoc)
	 * @see com.fs.commons.desktop.validation.Validator#validate(com.fs.commons.desktop.validation.Problems, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean validate(final Problems problems, final String compName, final T model) {
		boolean result = true;
		for (final Validator v : this.validators) {
			final boolean nextResult = v.validate(problems, compName, model);
			result &= nextResult;
		}
		return result;
	}
}

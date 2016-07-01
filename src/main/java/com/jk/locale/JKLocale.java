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
package com.jk.locale;

import java.io.Serializable;

/**
 * The Class JKLocale.
 *
 * @author Jalal Kiswani
 */
public class JKLocale implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -4392783699434888201L;
	
	/** The Constant ARABIC. */
	public static final JKLocale ARABIC = new JKLocale(1, "ar");
	
	/** The Constant ENGLISH. */
	public static final JKLocale ENGLISH = new JKLocale(2, "en");

	/**
	 * Value of.
	 *
	 * @param languageId
	 *            the language id
	 * @return the JK locale
	 */
	public static JKLocale valueOf(final int languageId) {
		return languageId == 1 ? ARABIC : ENGLISH;
	}

	/**
	 * Value of.
	 *
	 * @param localeString
	 *            the locale string
	 * @return the JK locale
	 */
	public static JKLocale valueOf(final String localeString) {
		return localeString.equals("ar") ? ARABIC : ENGLISH;
	}

	int languageId;
	String languageName;

	/**
	 * Instantiates a new JK locale.
	 *
	 * @param langId
	 *            the lang id
	 * @param languageName
	 *            the language name
	 */
	public JKLocale(final int langId, final String languageName) {
		this.languageId = langId;
		this.languageName = languageName;
	}

	/**
	 * Gets the language id.
	 *
	 * @return the languageId
	 */
	public int getLanguageId() {
		return this.languageId;
	}

	/**
	 * Gets the language name.
	 *
	 * @return the languageName
	 */
	public String getLanguageName() {
		return this.languageName;
	}

	/**
	 * Sets the language id.
	 *
	 * @param languageId
	 *            the languageId to set
	 */
	public void setLanguageId(final int languageId) {
		this.languageId = languageId;
	}

	/**
	 * Sets the language name.
	 *
	 * @param languageName
	 *            the languageName to set
	 */
	public void setLanguageName(final String languageName) {
		this.languageName = languageName;
	}

}

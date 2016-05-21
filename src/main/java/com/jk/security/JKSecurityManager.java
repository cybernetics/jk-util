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
package com.jk.security;

public class JKSecurityManager {
	static JKAuthenticaor authenticaor;

	static JKAuthorizer authorizer;

	private static JKUser currentUser;

	/**
	 *
	 * @param priviligeId
	 * @param String
	 * @throws SecurityException
	 * @throws JKNotAllowedOperationException
	 */
	public static void checkAllowedPrivilige(final JKPrivilige privilige)  {
		final JKAuthorizer auth = getAuthorizer();
		auth.checkAllowed(privilige);
	}

	/**
	 * @return the authenticaor
	 */
	public static JKAuthenticaor getAuthenticaor() {
		if (authenticaor == null) {
			throw new IllegalStateException("Please set Auth implmentation");
		}
		return authenticaor;
	}

	/**
	 * @return the authorizer
	 */
	public static JKAuthorizer getAuthorizer() {
		if (authorizer == null) {
			throw new IllegalStateException("Please set Auth implmentation");
		}
		return authorizer;
	}

	/**
	 * @return the currentUser
	 */
	public static JKUser getCurrentUser() {
		if (currentUser == null) {
			throw new IllegalStateException("Current user cannot be null");
		}
		return currentUser;
	}

	public static boolean isUserLoggedIn() {
		return currentUser != null;
	}

	/**
	 * @param authenticaor
	 *            the authenticaor to set
	 */
	public static void setAuthenticaor(final JKAuthenticaor authenticaor) {
		JKSecurityManager.authenticaor = authenticaor;
	}

	/**
	 * @param authorizer
	 *            the authorizer to set
	 */
	public static void setAuthorizer(final JKAuthorizer authorizer) {
		JKSecurityManager.authorizer = authorizer;
	}

	/**
	 *
	 * @param user
	 */
	public static void setCurrentUser(final JKUser currentUser) {
		JKSecurityManager.currentUser = currentUser;
	}

	public static boolean matchPassword(String plain, JKUser user) {
		return JKEncDec.encode(plain).equals(user.getPassword());
	}

	public static String encryptPassword(String text) {
		return JKEncDec.encode(text);
	}
}

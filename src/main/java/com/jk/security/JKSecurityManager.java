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

import com.jk.util.JKObjectUtil;

/**
 * The Class JKSecurityManager.
 *
 * @author Jalal Kiswani
 */
public class JKSecurityManager {
	static JKAuthenticaor authenticaor;

	static JKAuthorizer authorizer;

	private static JKUser currentUser;

	/**
	 * Check allowed privilige.
	 *
	 * @param privilige
	 *            the privilige
	 * @throws SecurityException
	 *             the security exception
	 */
	public static void checkAllowedPrivilige(final JKPrivilige privilige)  {
		final JKAuthorizer auth = getAuthorizer();
		auth.checkAllowed(privilige);
	}

	/**
	 * Gets the authenticaor.
	 *
	 * @return the authenticaor
	 */
	public static JKAuthenticaor getAuthenticaor() {
		if (authenticaor == null) {
			throw new IllegalStateException("Please set Auth implmentation");
		}
		return authenticaor;
	}

	/**
	 * Gets the authorizer.
	 *
	 * @return the authorizer
	 */
	public static JKAuthorizer getAuthorizer() {
		if (authorizer == null) {
			throw new IllegalStateException("Please set Auth implmentation");
		}
		return authorizer;
	}

	/**
	 * Gets the current user.
	 *
	 * @return the currentUser
	 */
	public static JKUser getCurrentUser() {
		if (currentUser == null) {
			throw new IllegalStateException("Current user cannot be null");
		}
		return currentUser;
	}

	/**
	 * Checks if is user logged in.
	 *
	 * @return true, if is user logged in
	 */
	public static boolean isUserLoggedIn() {
		return currentUser != null;
	}

	/**
	 * Sets the authenticaor.
	 *
	 * @param authenticaor
	 *            the authenticaor to set
	 */
	public static void setAuthenticaor(final JKAuthenticaor authenticaor) {
		JKSecurityManager.authenticaor = authenticaor;
	}

	/**
	 * Sets the authorizer.
	 *
	 * @param authorizer
	 *            the authorizer to set
	 */
	public static void setAuthorizer(final JKAuthorizer authorizer) {
		JKSecurityManager.authorizer = authorizer;
	}

	/**
	 * Sets the current user.
	 *
	 * @param currentUser
	 *            the new current user
	 */
	public static void setCurrentUser(final JKUser currentUser) {
		JKSecurityManager.currentUser = currentUser;
	}

	/**
	 * Match password.
	 *
	 * @param plain
	 *            the plain
	 * @param user
	 *            the user
	 * @return true, if successful
	 */
	public static boolean matchPassword(String plain, JKUser user) {
		return JKEncDec.encode(plain).equals(user.getPassword());
	}

	/**
	 * Encrypt password.
	 *
	 * @param text
	 *            the text
	 * @return the string
	 */
	public static String encryptPassword(String text) {
		return JKEncDec.encode(text);
	}

//	public static JKPrivilige createPrivilige(int id, String name, JKPrivilige parent) {
//		return new JKPrivilige(id, name,parent);
//	}

	/**
 * Creates the privilige.
 *
 * @param name
 *            the name
 * @param parent
 *            the parent
 * @return the JK privilige
 */
public static JKPrivilige createPrivilige(String name, JKPrivilige parent) {
		return createPrivilige(name, parent,0);
	}
	
	/**
	 * Creates the privilige.
	 *
	 * @param name
	 *            the name
	 * @param parent
	 *            the parent
	 * @param number
	 *            the number
	 * @return the JK privilige
	 */
	public static JKPrivilige createPrivilige(String name, JKPrivilige parent,int number) {
		int id=JKObjectUtil.hash(parent==null?name:name.concat(parent.getPriviligeName()));
		JKPrivilige p = new JKPrivilige(id, name,parent);
		p.setNumber(number);
		p.setDesc(p.getFullName());
		return p;
	}

}

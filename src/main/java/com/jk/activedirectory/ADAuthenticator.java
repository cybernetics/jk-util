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
package com.jk.activedirectory;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * The Class ADAuthenticator.
 *
 * @author Jalal Kiswani
 */
public class ADAuthenticator {
	static Logger log = Logger.getLogger(ADAuthenticator.class.getSimpleName());

	/**
	 * Checks if is authenticed.
	 *
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return true, if is authenticed
	 * @throws NamingException
	 *             the naming exception
	 */
	public static boolean isAuthenticed(String host, int port, String userName, String password) throws NamingException {
		log.info("isAuthenticed");
		// Set up the environment for creating the initial context
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, userName + "@" + host);
		log.info(env.toString());
		env.put(Context.SECURITY_CREDENTIALS, password);
		// Create the initial context
		DirContext ctx = new InitialDirContext(env);
		log.info("DirContext Init Succ");
		boolean result = ctx != null;
		if (ctx != null) {
			log.info("Closing DirContext");
			ctx.close();
		}
		return result;
	}
}

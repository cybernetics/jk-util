package com.jk.activedirectory;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class ADAuthenticator {
	static Logger log = Logger.getLogger(ADAuthenticator.class.getSimpleName());

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

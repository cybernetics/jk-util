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
package com.jk.context;

import java.util.HashMap;
import java.util.Map;

import com.jk.resources.JKResourceLoader;
import com.jk.resources.JKResourceLoaderFactory;
import com.jk.thread.JKThreadLocal;

/**
 * The Class JKAbstractContext.
 *
 * @author Jalal Kiswani
 */
public abstract class JKAbstractContext implements JKContext {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getApplicationMap()
	 */
	@Override
	public HashMap<String, Object> getApplicationMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(JKContextConstants.APPLICATION_MAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(final String attribute) {
		return JKThreadLocal.getValue(attribute);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getMachineName()
	 */
	@Override
	public String getMachineName() {
		return (String) JKThreadLocal.getValue(JKContextConstants.MACHINE_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getRemoteIP()
	 */
	@Override
	public String getRemoteIP() {
		return (String) JKThreadLocal.getValue(JKContextConstants.IP_ADDRESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getRemotPort()
	 */
	@Override
	public int getRemotPort() {
		return (int) JKThreadLocal.getValue(JKContextConstants.PORT_NUMBER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getRequestMap()
	 */
	@Override
	public HashMap<String, Object> getRequestMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(JKContextConstants.HTTP_REQUEST_MAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getSessionID()
	 */
	@Override
	public String getSessionID() {
		return (String) JKThreadLocal.getValue(JKContextConstants.HTTP_SESSION_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getSessionMap()
	 */
	@Override
	public HashMap<String, Object> getSessionMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(JKContextConstants.HTTP_SESSION_MAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getUser()
	 */
	@Override
	public Object getUser() {
		return JKThreadLocal.getValue(JKContextConstants.USER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#setAttribute(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public void setAttribute(final String key, final Object value) {
		JKThreadLocal.setValue(key, value);
	}

	/**
	 * Sets the application map.
	 *
	 * @param applicationMap
	 *            the application map
	 */
	public void setApplicationMap(final Map<String, Object> applicationMap) {
		JKThreadLocal.setValue(JKContextConstants.APPLICATION_MAP, applicationMap);
	}

	/**
	 * Sets the machine name.
	 *
	 * @param machineName
	 *            the new machine name
	 */
	public void setMachineName(final String machineName) {
		JKThreadLocal.setValue(JKContextConstants.MACHINE_NAME, machineName);
	}

	/**
	 * Sets the remote ip.
	 *
	 * @param remoteIP
	 *            the new remote ip
	 */
	public void setRemoteIP(final String remoteIP) {
		JKThreadLocal.setValue(JKContextConstants.IP_ADDRESS, remoteIP);
	}

	/**
	 * Sets the remot port.
	 *
	 * @param remotPort
	 *            the new remot port
	 */
	public void setRemotPort(final int remotPort) {
		JKThreadLocal.setValue(JKContextConstants.PORT_NUMBER, remotPort);
	}

	/**
	 * Sets the request map.
	 *
	 * @param requestMap
	 *            the request map
	 */
	public void setRequestMap(final Map<String, Object> requestMap) {
		JKThreadLocal.setValue(JKContextConstants.HTTP_REQUEST_MAP, requestMap);
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionID
	 *            the new session id
	 */
	public void setSessionID(final String sessionID) {
		JKThreadLocal.setValue(JKContextConstants.HTTP_SESSION_ID, sessionID);
	}

	/**
	 * Sets the session map.
	 *
	 * @param sessionMap
	 *            the session map
	 */
	public void setSessionMap(final Map<String, Object> sessionMap) {
		JKThreadLocal.setValue(JKContextConstants.HTTP_SESSION_MAP, sessionMap);
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(final Object user) {
		JKThreadLocal.setValue(JKContextConstants.USER, user);
	}

	@Override
	public JKResourceLoader getResourceLoader() {
		return JKResourceLoaderFactory.getResourceLoader();
	}

	@Override
	public String getConfigPath() {
		return JKThreadLocal.getValue(JKContextConstants.JK_CONFIG_PATH, JKContextConstants.JK_CONFIG_PATH_DEFAULT)
				.toString();
	}

	public void setConfigPath(String configPath) {
		JKThreadLocal.setValue(JKContextConstants.JK_CONFIG_PATH, configPath);
	}
}

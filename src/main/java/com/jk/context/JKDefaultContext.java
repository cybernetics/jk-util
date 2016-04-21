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

import java.util.Map;

import com.jk.thread.JKThreadLocal;

/**
 * The Class JKDefaultContext.
 *
 * @author Tareq.Saad
 * @version 1.0
 * @since 30/12/2014
 */
public class JKDefaultContext extends JKAbstractContext {

	/**
	 * Sets the application map.
	 *
	 * @param applicationMap
	 *            the application map
	 */
	public void setApplicationMap(final Map<String, Object> applicationMap) {
		JKThreadLocal.setValue(ContextParamKeys.APPLICATION_MAP, applicationMap);
	}

	/**
	 * Sets the machine name.
	 *
	 * @param machineName
	 *            the new machine name
	 */
	public void setMachineName(final String machineName) {
		JKThreadLocal.setValue(ContextParamKeys.MACHINE_NAME, machineName);
	}

	/**
	 * Sets the remote ip.
	 *
	 * @param remoteIP
	 *            the new remote ip
	 */
	public void setRemoteIP(final String remoteIP) {
		JKThreadLocal.setValue(ContextParamKeys.IP_ADDRESS, remoteIP);
	}

	/**
	 * Sets the remot port.
	 *
	 * @param remotPort
	 *            the new remot port
	 */
	public void setRemotPort(final int remotPort) {
		JKThreadLocal.setValue(ContextParamKeys.PORT_NUMBER, remotPort);
	}

	/**
	 * Sets the request map.
	 *
	 * @param requestMap
	 *            the request map
	 */
	public void setRequestMap(final Map<String, Object> requestMap) {
		JKThreadLocal.setValue(ContextParamKeys.HTTP_REQUEST_MAP, requestMap);
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionID
	 *            the new session id
	 */
	public void setSessionID(final String sessionID) {
		JKThreadLocal.setValue(ContextParamKeys.HTTP_SESSION_ID, sessionID);
	}

	/**
	 * Sets the session map.
	 *
	 * @param sessionMap
	 *            the session map
	 */
	public void setSessionMap(final Map<String, Object> sessionMap) {
		JKThreadLocal.setValue(ContextParamKeys.HTTP_SESSION_MAP, sessionMap);
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(final Object user) {
		JKThreadLocal.setValue(ContextParamKeys.USER, user);
	}

}

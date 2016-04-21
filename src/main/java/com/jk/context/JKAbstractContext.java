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
		return (HashMap<String, Object>) JKThreadLocal.getValue(ContextParamKeys.APPLICATION_MAP);
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
		return (String) JKThreadLocal.getValue(ContextParamKeys.MACHINE_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getRemoteIP()
	 */
	@Override
	public String getRemoteIP() {
		return (String) JKThreadLocal.getValue(ContextParamKeys.IP_ADDRESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getRemotPort()
	 */
	@Override
	public int getRemotPort() {
		return (int) JKThreadLocal.getValue(ContextParamKeys.PORT_NUMBER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getRequestMap()
	 */
	@Override
	public HashMap<String, Object> getRequestMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(ContextParamKeys.HTTP_REQUEST_MAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getSessionID()
	 */
	@Override
	public String getSessionID() {
		return (String) JKThreadLocal.getValue(ContextParamKeys.HTTP_SESSION_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getSessionMap()
	 */
	@Override
	public HashMap<String, Object> getSessionMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(ContextParamKeys.HTTP_SESSION_MAP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jk.context.JKContext#getUser()
	 */
	@Override
	public Object getUser() {
		return JKThreadLocal.getValue(ContextParamKeys.USER);
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

}

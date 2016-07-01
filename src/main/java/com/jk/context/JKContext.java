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

import com.jk.resources.JKResourceLoader;
import com.jk.thread.JKThreadLocal;

/**
 * The Interface JKContext.
 *
 * @author Jalal Kiswani
 */
public interface JKContext {

	/**
	 * Gets the application map.
	 *
	 * @return the application map
	 */
	public HashMap<String, Object> getApplicationMap();

	/**
	 * Gets the attribute.
	 *
	 * @param attribute
	 *            the attribute
	 * @return the attribute
	 */
	public Object getAttribute(String attribute);

	/**
	 * Gets the machine name.
	 *
	 * @return the machine name
	 */
	public String getMachineName();

	/**
	 * Gets the remote ip.
	 *
	 * @return the remote ip
	 */
	public String getRemoteIP();

	/**
	 * Gets the remot port.
	 *
	 * @return the remot port
	 */
	public int getRemotPort();

	/**
	 * Gets the request map.
	 *
	 * @return the request map
	 */
	public HashMap<String, Object> getRequestMap();

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionID();

	/**
	 * Gets the session map.
	 *
	 * @return the session map
	 */
	public HashMap<String, Object> getSessionMap();

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Object getUser();

	/**
	 * Sets the attribute.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setAttribute(String key, Object value);

	/**
	 * Gets the resource loader.
	 *
	 * @return the resource loader
	 */
	public JKResourceLoader getResourceLoader();
	
	/**
	 * Gets the config path.
	 *
	 * @return the config path
	 */
	public String getConfigPath();
}

/**
 * 
 */
package com.jk.context;

import java.util.HashMap;


public interface JKContext {
	/**
	 * 
	 * @return
	 */
	public String getRemoteIP();

	/**
	 * 
	 * @return
	 */
	public int getRemotPort();

	/**
	 * 
	 * @return
	 */
	public Object getUser();

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getRequestMap();

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getSessionMap();

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getApplicationMap();

	/**
	 * 
	 * @param attribute
	 * @return
	 */
	public Object getAttribute(String attribute);

	/**
	 * 
	 * @return
	 */
	public String getMachineName();

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setAttribute(String key, Object value);

	/**
	 * 
	 * @return
	 */
	public String getSessionID();

}

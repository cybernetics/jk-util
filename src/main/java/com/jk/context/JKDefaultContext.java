/**
 * 
 */
package com.jk.context;

import java.util.Map;

import com.jk.thread.JKThreadLocal;
/**
 * @version 1.0
 * @author Tareq.Saad
 * @since 30/12/2014
 */
public class JKDefaultContext extends JKAbstractContext {
	
	/**
	 * 
	 * @param remoteIP
	 */
	public void setRemoteIP(String remoteIP) {
		JKThreadLocal.setValue(ContextParamKeys.IP_ADDRESS, remoteIP);
	}
	/**
	 * 
	 * @param remotPort
	 */
	public void setRemotPort(int remotPort) {
		JKThreadLocal.setValue(ContextParamKeys.PORT_NUMBER, remotPort);
	}
	
	/**
	 * 
	 * @param requestMap
	 */
	public void setRequestMap(Map<String, Object> requestMap) {
		JKThreadLocal.setValue(ContextParamKeys.HTTP_REQUEST_MAP, requestMap);
	}
	/**
	 * 
	 * @param sessionMap
	 */
	public void setSessionMap(Map<String, Object> sessionMap){
		JKThreadLocal.setValue(ContextParamKeys.HTTP_SESSION_MAP, sessionMap);
	}
	/**
	 * 
	 * @param applicationMap
	 */
	public void setApplicationMap(Map<String, Object> applicationMap){
		JKThreadLocal.setValue(ContextParamKeys.APPLICATION_MAP, applicationMap);
	}
	/**
	 * 
	 * @param user
	 */
	public void setUser(Object user){
		JKThreadLocal.setValue(ContextParamKeys.USER, user);
	}
	
	/**
	 * 
	 * @param machineName
	 */
	public void setMachineName(String machineName){
		JKThreadLocal.setValue(ContextParamKeys.MACHINE_NAME, machineName);
	}
	/**
	 * 
	 * @param sessionID
	 */
	public void setSessionID(String sessionID){
		JKThreadLocal.setValue(ContextParamKeys.HTTP_SESSION_ID, sessionID);
	}
	
}

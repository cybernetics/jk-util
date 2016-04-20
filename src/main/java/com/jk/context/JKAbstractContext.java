package com.jk.context;

import java.util.HashMap;

import com.jk.thread.JKThreadLocal;

public abstract class JKAbstractContext implements JKContext {

	@Override
	public String getRemoteIP() {
		return (String) JKThreadLocal.getValue(ContextParamKeys.IP_ADDRESS);
	}

	@Override
	public int getRemotPort() {
		return (int) JKThreadLocal.getValue(ContextParamKeys.PORT_NUMBER);
	}

	@Override
	public Object getUser() {
		return JKThreadLocal.getValue(ContextParamKeys.USER);
	}

	@Override
	public HashMap<String, Object> getRequestMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(ContextParamKeys.HTTP_REQUEST_MAP);
	}

	@Override
	public HashMap<String, Object> getSessionMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(ContextParamKeys.HTTP_SESSION_MAP);
	}

	@Override
	public HashMap<String, Object> getApplicationMap() {
		return (HashMap<String, Object>) JKThreadLocal.getValue(ContextParamKeys.APPLICATION_MAP);
	}

	@Override
	public Object getAttribute(String attribute) {
		return JKThreadLocal.getValue(attribute);
	}


	@Override
	public String getMachineName() {
		return (String) JKThreadLocal.getValue(ContextParamKeys.MACHINE_NAME);
	}

	@Override
	public void setAttribute(String key, Object value) {
		JKThreadLocal.setValue(key, value);
	}

	@Override
	public String getSessionID() {
		return (String) JKThreadLocal.getValue(ContextParamKeys.HTTP_SESSION_ID);
	}

}

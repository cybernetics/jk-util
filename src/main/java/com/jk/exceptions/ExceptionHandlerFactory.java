package com.jk.exceptions;

import java.util.LinkedHashMap;

import com.jk.annotations.Author;

@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class ExceptionHandlerFactory {
	private static ExceptionHandlerFactory instance;
	private ExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();
	private LinkedHashMap<Class<? extends Throwable>, ExceptionHandler> handlers = new LinkedHashMap<>();

	/**
	 * 
	 * @return
	 */
	public static ExceptionHandlerFactory getInstance() {
		if (instance == null) {
			instance = new ExceptionHandlerFactory();
		}
		return instance;
	}

	public <T extends Throwable> ExceptionHandlerInfo getHandler(T t) {
		ExceptionHandlerInfo info = getHandler(t.getClass());
		if (info != null) {
			info.setException(t);
		}
		if (info == null && t.getCause() != null) {
			info = getHandler(t.getCause());
		}
		return info;
	}

	/**
	 * @return
	 */
	public ExceptionHandlerInfo getHandler(Class<? extends Throwable> clas) {
		ExceptionHandler handler = handlers.get(clas);
		if (handler != null) {
			ExceptionHandlerInfo info = new ExceptionHandlerInfo();
			info.setExceptionClass(clas).setHandler(handler);
			return info;
		}
		return null;
	}

	public void setHandler(Class<? extends Throwable> clas, ExceptionHandler handler) {
		handlers.put(clas, handler);
	}

	/**
	 * 
	 * @return
	 */
	public ExceptionHandler getDefaultHandler() {
		return defaultExceptionHandler;
	}

	public void setDefaultExceptionHandler(ExceptionHandler defaultExceptionHandler) {
		this.defaultExceptionHandler = defaultExceptionHandler;
	}
}

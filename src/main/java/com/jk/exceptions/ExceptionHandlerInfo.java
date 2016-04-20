package com.jk.exceptions;

public class ExceptionHandlerInfo {
	ExceptionHandler handler;
	Class<? extends Throwable> exceptionClass;
	Throwable exception;

	public ExceptionHandlerInfo() {
	}

	public ExceptionHandler getHandler() {
		return handler;
	}

	public ExceptionHandlerInfo setHandler(ExceptionHandler<?> handler) {
		this.handler = handler;
		return this;
	}

	public Class<? extends Throwable> getExceptionClass() {
		return exceptionClass;
	}

	public ExceptionHandlerInfo setExceptionClass(Class<? extends Throwable> exceptionClass) {
		this.exceptionClass = exceptionClass;
		return this;	
	}

	public Throwable getException() {
		return exception;
	}

	public ExceptionHandlerInfo setException(Throwable exception) {
		this.exception = exception;
		return this;
	}

}

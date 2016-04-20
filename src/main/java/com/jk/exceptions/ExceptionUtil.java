package com.jk.exceptions;

import com.jk.annotations.Author;

@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class ExceptionUtil {

	public static void handle(Throwable t) {
		handle(t, true);
	}

	public static void handle(Throwable t, boolean throwRuntimeException) {
		ExceptionHandlerFactory factory = ExceptionHandlerFactory.getInstance();
		ExceptionHandlerInfo info= factory.getHandler(t);
		if (info != null) {
			info.getHandler().handle(info.getException(), throwRuntimeException);
		}else{
			factory.getDefaultHandler().handle(t, throwRuntimeException);
		}

	}

}

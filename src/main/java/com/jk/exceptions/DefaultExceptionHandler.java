package com.jk.exceptions;

import java.util.logging.Logger;

import com.jk.annotations.Author;

@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public class DefaultExceptionHandler implements ExceptionHandler {
	Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void handle(Throwable throwable, boolean throwRuntimeException) {
		logger.severe(throwable.getMessage());
		if (throwRuntimeException) {
			if (throwable instanceof RuntimeException) {
				throw (RuntimeException) throwable;
			}
			throw new RuntimeException(throwable);
		}
	}

}

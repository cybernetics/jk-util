package com.jk.exceptions;

import com.jk.annotations.Author;

@Author(name = "Jalal H. Kiswani", date = "1/11/2014", version = "1.0")
public interface ExceptionHandler<T extends Throwable> {
	public void handle(T throwable, boolean throwRuntimeException);
}

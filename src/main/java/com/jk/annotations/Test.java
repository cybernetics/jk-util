package com.jk.annotations;

import java.io.IOException;

import com.jk.exceptions.handler.ExceptionHandler;
import com.jk.exceptions.handler.JKExceptionHandler;

@ExceptionHandler
public class Test implements JKExceptionHandler<IOException> {

	@Override
	public void handle(IOException throwable, boolean throwRuntimeException) {
		System.out.println("Hayne");
	}

}

package com.jk.exceptions;

import com.fs.commons.desktop.validation.Problems;

public class JKValidationException extends JKException implements JKNonPrintableException {
	Problems problems;

	public JKValidationException() {
		super();
	}

	public JKValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JKValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public JKValidationException(String message) {
		super(message);
	}

	public JKValidationException(Throwable cause) {
		super(cause);
	}

	public JKValidationException(Problems problems) {
		this.problems = problems;
	}
	 public Problems getProblems() {
		return problems;
	}

}

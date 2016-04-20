/*
 * 
 */
package com.jk.annotations;

/**
 * The Interface AnnotationHandler.
 */
@Author(name = "Jalal H. Kiswani", date = "1/10/2014", version = "1.0")
public interface AnnotationHandler {

	/**
	 * Call back handler that will be called in the AnnotationDetector class.
	 *
	 * @param className
	 *            the class name
	 */
	public void handleAnnotationFound(String className);
}

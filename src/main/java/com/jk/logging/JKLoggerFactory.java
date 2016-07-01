/*
 * Copyright 2002-2016 Jalal Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jk.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A factory for creating JKLogger objects.
 */
public class JKLoggerFactory {

	private JKLoggerFactory(){
		
	}
	
	/**
	 * Gets the logger.
	 *
	 * @param class1
	 *            the class 1
	 * @return the logger
	 */
	public static JKLogger getLogger(Class class1 ){
		return new JKLogger(class1.getName());
	} 
	
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		JKLogger logger = JKLoggerFactory.getLogger(Integer.class);
		logger.info("asdfads");
	}

}

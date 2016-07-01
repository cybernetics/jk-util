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
package com.jk.context;

import com.jk.context.impl.JKDesktopContext;
import com.jk.context.impl.JKJsfContext;
import com.jk.context.impl.JKMobileContext;
import com.jk.context.impl.JKServletContext;
import com.jk.thread.JKThreadLocal;

/**
 * A factory for creating JKContext objects.
 *
 */
public class JKContextFactory {
	private static JKContextFactory factoryImpl;

	/**
	 * 
	 * @return
	 */
	protected static JKContextFactory getInstance() {
		if (factoryImpl == null) {
			factoryImpl = new JKContextFactory();
		}
		return factoryImpl;
	}

	/**
	 * Sets the instance.
	 *
	 * @param instance
	 *            the new instance
	 */
	public static void setInstance(JKContextFactory instance) {
		JKContextFactory.factoryImpl = instance;
	}

	/**
	 * Creates a new JKContext object.
	 *
	 * @return the JK context
	 */
	public JKContext createDesktopContext() {
		return new JKDesktopContext();
	}

	/**
	 * Creates a new JKContext object.
	 *
	 * @return the JK mobile context
	 */
	public JKMobileContext createMobileContext() {
		return new JKMobileContext();
	}

	/**
	 * Creates a new JKContext object.
	 *
	 * @return the JK jsf context
	 */
	public JKJsfContext createJsfContext() {
		return new JKJsfContext();
	}

	/**
	 * Creates a new JKContext object.
	 *
	 * @return the JK servlet context
	 */
	public JKServletContext createJkServletContext() {
		return new JKServletContext();
	}

	/**
	 * Gets the current context.
	 *
	 * @return the current context
	 */
	public static JKContext getCurrentContext() {
		JKContext context = (JKContext) JKThreadLocal.getValue(JKContextConstants.JK_CONTEXT);
		if (context == null) {
			context = getInstance().createDesktopContext();
			JKThreadLocal.setValue(JKContextConstants.JK_CONTEXT, context);
		}
		return context;
	}

	/**
	 * Sets the current context.
	 *
	 * @param context
	 *            the new current context
	 */
	public static void setCurrentContext(JKContext context) {
		JKThreadLocal.setValue(JKContextConstants.JK_CONTEXT, context);
	}

}

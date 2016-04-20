/**
 * 
 */
package com.jk.context;

import com.jk.thread.JKThreadLocal;

/**
 * @author Tareq.Saad
 *
 */
public class JKContextFactory {
	
	public static JKContext getCurrentContext(){
		return (JKContext) JKThreadLocal.getValue(ContextParamKeys.JK_CONTEXT);
	}

}

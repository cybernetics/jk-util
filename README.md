# JK-Util
This is utility classes used by my other projects

#Maven dependency:

	<dependency>
	    <groupId>com.jalalkiswani</groupId>
	    <artifactId>jk-util</artifactId>
	    <version>0.0.9</version>
	</dependency>

##Resource Loading
###API
![alt tag](https://github.com/kiswanij/jk-util/blob/master/design/resource-loader.PNG)
###Usage	
	InputStream in = JKResourceLoaderFactory.getResourceLoader().getResourceAsStream(fileName);

## Exception Handling
###API Class Diagram
![alt tag](https://github.com/kiswanij/jk-util/blob/master/design/exception-handling1.PNG)
###API Sequence Diagram
![alt tag](https://github.com/kiswanij/jk-util/blob/master/design/exception-handling2.PNG)
###Usage:
just handle the exception this way in your catch clause:  

	JKExceptionUtil.handle(e);

To create exception handler :  

	package com.jalalkiswani.example;
	
	import java.io.FileNotFoundException;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	
	import javax.swing.JOptionPane;
	
	import com.jk.exceptions.handler.JKExceptionHandler;
	
	public class FileNotFoundExceptionHandler implements JKExceptionHandler<FileNotFoundException> {
		Logger logger = Logger.getLogger(getClass().getName());
	
		public void handle(FileNotFoundException throwable, boolean throwRuntimeException) {
			JOptionPane.showMessageDialog(null, "The file your requested is not found");
			logger.log(Level.WARNING, "File not found : ", throwable);
			if (throwRuntimeException) {
				throw new RuntimeException(throwable);
			}
		}
	}
	
- To register the exception handler :  
	
	JKExceptionHandlerFactory.getInstance().setHandler(FileNotFoundException.class, new FileNotFoundExceptionHandler()); 

##Context-API
![alt tag](https://github.com/kiswanij/jk-util/blob/master/design/context.PNG)


Enjoy!  
Jalal   
http://www.jalalkiswani.com


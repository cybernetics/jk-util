# JK-Util
Utility API's and wrappers useful for all types of Java application development. 

#Maven dependency:

	<dependency>
	    <groupId>com.jalalkiswani</groupId>
	    <artifactId>jk-util</artifactId>
	     <version>0.0.9-2</version>
	</dependency>

## Exception Handling
For detailed description about this API, check my article titled "Exception Handling in Real-Life Java Applications" on DZone at :  
<http://dzone.com/articles/exception-handling-in-real-life-applications>   
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
	
	@ExceptionHandler
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
	
	    JKExceptionHandlerFactory.getInstance().registerHanders(yourPackageNameString);

## Caching API
###Class-Diagram
![alt tag](https://github.com/kiswanij/jk-util/blob/master/design/cache.PNG)  
### Usage
In this scenario, the application should call `JKCacheManager cacheManager = JKCacheFactory.getCacheManager();` to get instance of cache manager, then it can add value to the cache by calling `cacheManager.cache(key, value);`, for retrieval , it should use `cacheManager.get(key,String.class)` to retrieve the value from cache , it will return `null` if the value is not available in cache.    

	package com.jalalkiswani.examples;
	
	import com.jk.cache.JKCacheFactory;
	import com.jk.cache.JKCacheManager;
	
	public class CachTest {
		public static void main(String[] args) {
			JKCacheManager cacheManager = JKCacheFactory.getCacheManager();
			String key = "Cache-Key";
			String value = "Cache-Value";
			cacheManager.cache(key, value);
			
			System.out.println(cacheManager.get(key,String.class));			
		}
	}


##Context-API
![alt tag](https://github.com/kiswanij/jk-util/blob/master/design/context.PNG)


##Resource Loading
###API
![alt tag](https://github.com/kiswanij/jk-util/blob/master/design/resource-loader.PNG)
###Usage	
	InputStream in = JKResourceLoaderFactory.getResourceLoader().getResourceAsStream(fileName);


Enjoy!  
Jalal   
http://www.jalalkiswani.com


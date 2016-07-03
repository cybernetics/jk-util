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
package com.jk.util;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;

import com.jk.annotations.Author;
import com.jk.exceptions.JKException;
import com.jk.exceptions.handler.JKExceptionUtil;

/**
 * The Class ObjectUtil.
 *
 * @author Jalal Kiswani
 */
@Author(name = "Jalal Kiswani", date = "23/9/2014", version = "1.0")
public class JKObjectUtil {

	/**
	 * Clone bean.
	 *
	 * @param <T>
	 *            the generic type
	 * @param bean
	 *            the bean
	 * @return the t
	 */
	public static <T> T cloneBean(final Object bean) {
		try {
			return (T) BeanUtils.cloneBean(bean);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the class.
	 *
	 * @param beanClassName
	 *            the bean class name
	 * @return the class
	 */
	public static Class<?> getClass(final String beanClassName) {
		try {
			return Class.forName(beanClassName);
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the property value.
	 *
	 * @param <T>
	 *            the generic type
	 * @param instance
	 *            the instance
	 * @param fieldName
	 *            the field name
	 * @return the property value
	 */
	public static <T> T getPropertyValue(final Object instance, final String fieldName) {
		try {
			return (T) PropertyUtils.getProperty(instance, fieldName);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Checks if is boolean.
	 *
	 * @param type
	 *            the type
	 * @return true, if is boolean
	 */
	public static boolean isBoolean(final Class type) {
		if (Boolean.class.isAssignableFrom(type)) {
			return true;
		}
		return type.getName().equals("boolean");
	}

	/**
	 * Checks if is date.
	 *
	 * @param type
	 *            the type
	 * @return true, if is date
	 */
	public static boolean isDate(final Class type) {
		return Date.class.isAssignableFrom(type);
	}

	/**
	 * Checks if is final.
	 *
	 * @param field
	 *            the field
	 * @return true, if is final
	 */
	public static boolean isFinal(final Field field) {
		return Modifier.isFinal(field.getModifiers());
	}

	/**
	 * Checks if is static.
	 *
	 * @param field
	 *            the field
	 * @return true, if is static
	 */
	public static boolean isStatic(final Field field) {
		return Modifier.isStatic(field.getModifiers());
	}

	/**
	 * Checks if is time.
	 *
	 * @param type
	 *            the type
	 * @return true, if is time
	 */
	public static boolean isTime(final Class type) {
		return Time.class.isAssignableFrom(type);
	}

	/**
	 * Checks if is time stamp.
	 *
	 * @param type
	 *            the type
	 * @return true, if is time stamp
	 */
	public static boolean isTimeStamp(final Class type) {
		return Timestamp.class.isAssignableFrom(type);
	}

	/**
	 * Checks if is transient.
	 *
	 * @param field
	 *            the field
	 * @return true, if is transient
	 */
	public static boolean isTransient(final Field field) {
		return Modifier.isTransient(field.getModifiers());
	}

	/**
	 * New instance.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clas
	 *            the clas
	 * @return the t
	 */
	public static <T> T newInstance(final Class<T> clas) {
		try {
			return clas.newInstance();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * New instance.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clas
	 *            the clas
	 * @param params
	 *            the params
	 * @return the t
	 */
	public static <T> T newInstance(final Class<T> clas, final Object... params) {
		try {
			final Class<?>[] paramClasses = JKObjectUtil.toClassesFromObjects(params);
			if (paramClasses.length == 0) {
				return clas.newInstance();
			}
			return ConstructorUtils.invokeConstructor(clas, params);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * New instance.
	 *
	 * @param <T>
	 *            the generic type
	 * @param className
	 *            the class name
	 * @return the t
	 */
	public static <T> T newInstance(final String className) {
		try {
			return (T) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Sets the peoperty value.
	 *
	 * @param source
	 *            the source
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the value
	 */
	public static void setPeopertyValue(final Object source, final String fieldName, final Object value) {
		try {
			PropertyUtils.setProperty(source, fieldName, value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * To class.
	 *
	 * @param name
	 *            the name
	 * @return the class
	 */
	public static Class<?> toClass(final String name) {
		try {
			return Class.forName(name);
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * To classes from objects.
	 *
	 * @param params
	 *            the params
	 * @return the class[]
	 */
	public static Class<?>[] toClassesFromObjects(final Object[] params) {
		final Class<?>[] classes = new Class<?>[params.length];
		int i = 0;
		for (final Object object : params) {
			if (object != null) {
				classes[i++] = object.getClass();
			} else {
				classes[i++] = Object.class;
			}
		}
		return classes;
	}

	public static String toString(final Object object, boolean useCurrentTostringIfAvailable) {
		if (object == null) {
			return "[NULL]";
		}
		if (useCurrentTostringIfAvailable && isMethodDirectlyExists(object, "toString")) {
			return object.toString();
		}
		return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * To string.
	 *
	 * @param object
	 *            the object
	 * @return the string
	 */
	public static String toString(final Object object) {
		return toString(object, false);
	}

	public static boolean isMethodDirectlyExists(Object object, String methodName, Class<?>... params) {
		try {
			Method method = object.getClass().getMethod(methodName, params);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Equals.
	 *
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @return true, if successful
	 */
	public static boolean equals(final Object source, final Object target) {
		if (source == null && target != null) {
			return false;
		}
		if (source == null && target == null) {
			return true;
		}
		return JKObjectUtil.toString(source).equals(JKObjectUtil.toString(target));
	}

	/**
	 * Gets the field value.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clas
	 *            the clas
	 * @param instance
	 *            the instance
	 * @param fieldName
	 *            the field name
	 * @return the field value
	 */
	public static <T> T getFieldValue(Class<?> clas, Object instance, String fieldName) {
		try {
			Field declaredField = clas.getDeclaredField(fieldName);
			declaredField.setAccessible(true);
			return (T) declaredField.get(instance);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			throw new JKException(e);
		}
	}

	/**
	 * Call static method.
	 *
	 * @param clas
	 *            the clas
	 * @param methodName
	 *            the method name
	 * @param params
	 *            the params
	 * @return the object
	 */
	///////////////////////////////////////////////////////////////////
	public static Object callStaticMethod(Class clas, String methodName, Object... params) {
		try {
			Class[] paramsTypes = new Class[params.length];
			for (int i = 0; i < params.length; i++) {
				paramsTypes[i] = Object.class;
			}
			Method method = clas.getMethod(methodName, paramsTypes);
			Object o = method.invoke(null, params);
			return o;
		} catch (Exception e) {
			throw new JKException(e);
		}
	}

	/**
	 * Copy.
	 *
	 * @param source
	 *            the source
	 * @return the object
	 */
	// /////////////////////////////////////////////////////////////////
	public static Object copy(Object source) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(source);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object deepCopy = ois.readObject();
			return deepCopy;
		} catch (Exception e) {
			throw new JKException(e);
		}
	}

	/**
	 * To xml.
	 *
	 * @param obj
	 *            the obj
	 * @return the string
	 */
	// ////////////////////////////////////////////////////////////////////
	public static String toXml(final Object obj) {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();

		// XStream x = createXStream();
		// String xml = x.toXML(obj);
		// return xml;
		final XMLEncoder e = new XMLEncoder(out);
		e.setExceptionListener(new XmlEncoderExceptionListener());
		// e.setPersistenceDelegate(Object.class, new MyPersistenceDelegate());
		e.writeObject(obj);
		e.close();
		return out.toString();
		// return null;
	}

	/**
	 * To object.
	 *
	 * @param xml
	 *            the xml
	 * @return the object
	 */
	public static Object toObject(final String xml) {
		// XStream x = createXStream();
		// return x.fromXML(xml);
		// try {
		final ByteArrayInputStream out = new ByteArrayInputStream(xml.getBytes());
		final XMLDecoder encoder = new XMLDecoder(out);
		final Object object = encoder.readObject();
		//
		encoder.close();
		return object;
		// } catch (Exception e) {
		// System.err.println("Failed to decode object : \n" + xml);
		// return null;
		// }
		// return null;
	}

	/**
	 * Hash.
	 *
	 * @param name
	 *            the name
	 * @return the int
	 */
	public static int hash(String name) {
		return name.hashCode();
	}

	/**
	 * Gets the generic paramter.
	 *
	 * @param <T>
	 *            the generic type
	 * @param handler
	 *            the handler
	 * @return the generic paramter
	 */
	public static <T> Class<? extends T> getGenericParamter(String handler) {
		ParameterizedType parameterizedType = (ParameterizedType) JKObjectUtil.getClass(handler).getGenericInterfaces()[0];
		Class<? extends T> clas = (Class<? extends T>) (parameterizedType.getActualTypeArguments()[0]);
		return clas;
	}

	/**
	 * Fix property name.
	 *
	 * @param name
	 *            the name
	 * @return the string
	 */
	////////////////////////////////////////////////////////////////////////////////////////////
	public static String fixPropertyName(String name) {
		// captialize every char after the underscore , and remove underscores
		final char[] charArray = name.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == '_') {
				charArray[i + 1] = Character.toUpperCase(charArray[i + 1]);
			}
		}
		name = new String(charArray).replaceAll("_", "");
		return name;
	}

	/**
	 * Populate.
	 *
	 * @param instance
	 *            the instance
	 * @param values
	 *            the values
	 */
	public static void populate(Object instance, Map<String, Object> values) {
		try {
			BeanUtilsBean beanUtilBean = new BeanUtilsBean();
			for (String key : values.keySet()) {
				Object value = values.get(key);
				beanUtilBean.setProperty(instance, key, value);
			}
		} catch (Exception e) {
			JKExceptionUtil.handle(e);
		}
	}

	/**
	 * Checks if is class avilable in class path.
	 *
	 * @param string
	 *            the string
	 * @return true, if is class avilable in class path
	 */
	public static boolean isClassAvilableInClassPath(String string) {
		try {
			Class.forName(string);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	/**
	 * Call method.
	 *
	 * @param obj
	 *            the obj
	 * @param methodName
	 *            the method name
	 * @param includePrivateMehtods
	 *            the include private mehtods
	 * @param args
	 *            the args
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void callMethod(final Object obj, final String methodName, final boolean includePrivateMehtods, final Object... args)
			throws InvocationTargetException {
		final Class[] intArgsClass = initParamsClasses(args);
		try {
			Class<?> current = obj.getClass();
			Method method = null;
			while (current != Object.class) {
				try {
					method = current.getDeclaredMethod(methodName, intArgsClass);
					break;
				} catch (final NoSuchMethodException ex) {
					current = current.getSuperclass();
				}
			}
			if (method == null) {
				throw new NoSuchMethodException("Mehtod is not found in " + current);
			}
			method.setAccessible(true);
			method.invoke(obj, args);
		} catch (final InvocationTargetException e) {
			throw new InvocationTargetException(e.getCause());
		} catch (final Exception e) {
			throw new InvocationTargetException(e);
		}
	}

	/**
	 * Call method.
	 *
	 * @param obj
	 *            the obj
	 * @param methodName
	 *            the method name
	 * @param args
	 *            the args
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void callMethod(final Object obj, final String methodName, final Object... args) throws InvocationTargetException {
		callMethod(obj, methodName, false, args);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static Class[] initParamsClasses(final Object... args) {
		int count = 0;
		Class[] intArgsClass = null;
		if (args != null) {
			intArgsClass = new Class[args.length];
			for (final Object arg : args) {
				intArgsClass[count++] = arg.getClass();
			}
		}
		return intArgsClass;
	}

	public static <T> String getInstanceVariables(Class<T> clas) {
		StringBuffer fieldsString = new StringBuffer();
		Field[] fields = clas.getDeclaredFields();
		int i = 0;
		for (Field field : fields) {
			if (i++ > 0) {
				fieldsString.append(JK.CSV_SEPARATOR);
			}
			fieldsString.append(field.getName());
		}
		return fieldsString.toString();
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// private static Object[] initParamsValues(final Object... args) {
	// if (args == null) {
	// return null;
	// }
	// final Object[] intArgs = new Object[args.length];
	// int count = 0;
	// for (final Object arg : args) {
	// intArgs[count++] = arg;
	// }
	// return intArgs;
	// }

}

//////////////////////////////////////////////////////////////////////
class XmlEncoderExceptionListener implements ExceptionListener {

	PrintStream out;

	public XmlEncoderExceptionListener() {
		try {
			this.out = new PrintStream(new FileOutputStream(JKIOUtil.getUserFolderPath(true) + "gui.log", true));
		} catch (final Exception e) {
			System.err.println("unable to creat gui.log file for encoder exceptions , default logging will be used");
		}
	}

	@Override
	public void exceptionThrown(final Exception e) {
		e.printStackTrace(this.out == null ? System.err : this.out);
	}

}
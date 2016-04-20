/*
 *
 */
package com.jk.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;

import com.jk.annotations.Author;

/**
 * The Class ObjectUtil.
 */
@Author(name = "Jalal Kiswani", date = "23/9/2014", version = "1.0")
public class ObjectUtil {

	/**
	 * To string.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 */
	public static String toString(Object object) {
		return ToStringBuilder.reflectionToString(object, ToStringStyle.NO_CLASS_NAME_STYLE);
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
	public boolean equals(Object source, Object target) {
		Assert.assertNotNull(source);
		return toString(source).equals(toString(target));
	}

	/**
	 * Checks if is transient.
	 * 
	 * @param field
	 *            the field
	 * @return true, if is transient
	 */
	public static boolean isTransient(Field field) {
		return Modifier.isTransient(field.getModifiers());
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
	public static <T> T getPropertyValue(Object instance, String fieldName) {
		try {
			return (T) PropertyUtils.getProperty(instance, fieldName);
		} catch (Exception e) {
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
	public static void setPeopertyValue(Object source, String fieldName, Object value) {
		try {
			PropertyUtils.setProperty(source, fieldName, value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Clone bean.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param bean
	 *            the bean
	 * @return the t
	 */
	public static <T> T cloneBean(Object bean) {
		try {
			return (T) BeanUtils.cloneBean(bean);
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException
				| NoSuchMethodException e) {
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
	public static <T> T newInstance(String className) {
		try {
			return (T) Thread.currentThread().getContextClassLoader().loadClass(className).newInstance();
		} catch (Exception e) {
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
	 * @return the t
	 */
	public static <T> T newInstance(Class<T> clas) {
		try {
			return clas.newInstance();
		} catch (Exception e) {
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
	public static Class getClass(String beanClassName) {
		try {
			return Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Checks if is static.
	 * 
	 * @param field
	 *            the field
	 * @return true, if is static
	 */
	public static boolean isStatic(Field field) {
		return Modifier.isStatic(field.getModifiers());
	}

	/**
	 * Checks if is final.
	 * 
	 * @param field
	 *            the field
	 * @return true, if is final
	 */
	public static boolean isFinal(Field field) {
		return Modifier.isFinal(field.getModifiers());
	}

	/**
	 * Checks if is time.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is time
	 */
	public static boolean isTime(Class type) {
		return Time.class.isAssignableFrom(type);
	}

	/**
	 * Checks if is time stamp.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is time stamp
	 */
	public static boolean isTimeStamp(Class type) {
		return Timestamp.class.isAssignableFrom(type);
	}

	/**
	 * Checks if is date.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is date
	 */
	public static boolean isDate(Class type) {
		return Date.class.isAssignableFrom(type);
	}

	/**
	 * Checks if is boolean.
	 * 
	 * @param type
	 *            the type
	 * @return true, if is boolean
	 */
	public static boolean isBoolean(Class type) {
		if (Boolean.class.isAssignableFrom(type)) {
			return true;
		}
		return type.getName().equals("boolean");
	}

	/**
	 * 
	 * @param clas
	 * @param params
	 * @return
	 */
	public static <T> T newInstance(final Class<T> clas, Object... params) {
		try {
			Class<?>[] paramClasses = toClassesFromObjects(params);
			if (paramClasses.length == 0) {
				return (T) clas.newInstance();
			}
			return (T) ConstructorUtils.invokeConstructor(clas, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	public static Class<?>[] toClassesFromObjects(Object[] params) {
		Class<?>[] classes = new Class<?>[params.length];
		int i = 0;
		for (Object object : params) {
			if (object != null) {
				classes[i++] = object.getClass();
			} else {
				classes[i++] = Object.class;
			}
		}
		return classes;
	}

	public static Class<?> toClass(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}

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
 *
 * @author Jalal Kiswani
 */
@Author(name = "Jalal Kiswani", date = "23/9/2014", version = "1.0")
public class ObjectUtil {

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
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException
				| NoSuchMethodException e) {
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
	public static Class getClass(final String beanClassName) {
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
			final Class<?>[] paramClasses = ObjectUtil.toClassesFromObjects(params);
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

	/**
	 * To string.
	 *
	 * @param object
	 *            the object
	 * @return the string
	 */
	public static String toString(final Object object) {
		return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
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
	public boolean equals(final Object source, final Object target) {
		Assert.assertNotNull(source);
		return ObjectUtil.toString(source).equals(ObjectUtil.toString(target));
	}
}

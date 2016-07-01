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

import java.math.BigDecimal;

/**
 * The Class JKNumbersUtil.
 *
 * @author Jalal Kiswani
 */
public class JKNumbersUtil {
	
	/**
	 * Adds the amounts.
	 *
	 * @param num1
	 *            the num 1
	 * @param num2
	 *            the num 2
	 * @return the double
	 */
	public static double addAmounts(final double num1, final double num2) {
		final BigDecimal b1 = new BigDecimal(num1);
		final BigDecimal b2 = new BigDecimal(num2);
		BigDecimal b3 = b1.add(b2);
		b3 = b3.setScale(3, BigDecimal.ROUND_HALF_UP);
		final double result = b3.doubleValue();
		return result;
	}

	/**
	 * Divide.
	 *
	 * @param n1
	 *            the n 1
	 * @param n2
	 *            the n 2
	 * @return the double
	 */
	public static double divide(final double n1, final double n2) {
		final BigDecimal b1 = new BigDecimal(n1);
		final BigDecimal b2 = new BigDecimal(n2 + "");
		BigDecimal b3 = b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP);
		b3 = b3.setScale(3, BigDecimal.ROUND_HALF_UP);
		final double result = b3.doubleValue();
		return result;
	}

	/**
	 * Fix amount.
	 *
	 * @param value
	 *            the value
	 * @return the double
	 */
	public static double fixAmount(final double value) {
		final BigDecimal b1 = new BigDecimal(value);
		final BigDecimal b2 = b1.setScale(3, BigDecimal.ROUND_HALF_UP);
		return b2.doubleValue();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		System.out.println(divide(10.1, 30));
	}

	/**
	 * Mutiply.
	 *
	 * @param n1
	 *            the n 1
	 * @param n2
	 *            the n 2
	 * @return the double
	 */
	public static double mutiply(final double n1, final double n2) {
		final BigDecimal b1 = new BigDecimal(n1);
		final BigDecimal b2 = new BigDecimal(n2);
		BigDecimal b3 = b1.multiply(b2);
		b3 = b3.setScale(3, BigDecimal.ROUND_HALF_UP);
		final double result = b3.doubleValue();
		return result;
	}

	/**
	 * Sub amounts.
	 *
	 * @param n1
	 *            the n 1
	 * @param n2
	 *            the n 2
	 * @return the double
	 */
	public static double subAmounts(final double n1, final double n2) {
		final BigDecimal b1 = new BigDecimal(n1);
		final BigDecimal b2 = new BigDecimal(n2);
		BigDecimal b3 = b1.subtract(b2);
		b3 = b3.setScale(3, BigDecimal.ROUND_HALF_UP);
		final double result = b3.doubleValue();
		return result;
	}
}

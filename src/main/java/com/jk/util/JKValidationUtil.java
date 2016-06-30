package com.jk.util;

public class JKValidationUtil {
	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isBoolean(final String param) {
		if (isEmpty(param)) {
			return false;
		}
		return param.equalsIgnoreCase("true") || param.equalsIgnoreCase("false");
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isDouble(final String txt) {
		try {
			Double.parseDouble(txt);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isEmpty(final String str) {
		return str == null || str.trim().equals("");
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isFloat(final String txt) {
		try {
			Float.parseFloat(txt);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isInt(final String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isInteger(final String txt) {
		try {
			Integer.parseInt(txt);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean isUpperCase(final String txt) {
		boolean upper = true;
		for (final char c : txt.toCharArray()) {
			if (Character.isLowerCase(c)) {
				upper = false;
				break;
			}
		}
		return upper;
	}

}

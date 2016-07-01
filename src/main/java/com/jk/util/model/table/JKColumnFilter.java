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
package com.jk.util.model.table;

import java.util.Vector;

/**
 * The Class JKColumnFilter.
 *
 * @author Jalal Kiswani
 */
public class JKColumnFilter {
	enum FilterType {
		EQUALS, CONTAINS, MORE_THAN, LESS_THAN, BETWEEN, DOESNOT_CONTAINS, STARTS_WIDTH, ENDS_WITH;
		public int requiredFieldsCount() {
			if (this == BETWEEN) {
				return 2;
			}
			return 1;
		}
	}

	FilterType type = FilterType.CONTAINS;
	Vector values = new Vector();
	JKTableColumn column;
	boolean required;

	/**
	 * Instantiates a new JK column filter.
	 *
	 * @param column
	 *            the column
	 */
	public JKColumnFilter(final JKTableColumn column) {
		this.column = column;
	}

	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public JKTableColumn getColumn() {
		return this.column;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public FilterType getType() {
		return this.type;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public Vector getValues() {
		return this.values;
	}

	/**
	 * Checks if is required.
	 *
	 * @return true, if is required
	 */
	public boolean isRequired() {
		return this.required;
	}

	/**
	 * Sets the column.
	 *
	 * @param column
	 *            the new column
	 */
	public void setColumn(final JKTableColumn column) {
		this.column = column;
	}

	/**
	 * Sets the required.
	 *
	 * @param required
	 *            the new required
	 */
	public void setRequired(final boolean required) {
		this.required = required;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(final FilterType type) {
		this.type = type;
	}

	/**
	 * Sets the values.
	 *
	 * @param values
	 *            the new values
	 */
	public void setValues(final Vector values) {
		this.values = values;
	}

	/**
	 * To query string.
	 *
	 * @return the string
	 */
	public String toQueryString() {
		final StringBuffer buf = new StringBuffer();
		final Object value1 = this.values.get(0);
		buf.append(this.column.getName());
		switch (this.type) {
		case STARTS_WIDTH:
			buf.append(" like '" + value1 + "%'");
			break;
		case CONTAINS:
			buf.append(" like '%" + value1 + "%'");
			break;
		case ENDS_WITH:
			buf.append(" like '%" + value1 + "'");
			break;
		case DOESNOT_CONTAINS:
			buf.append(" not like '%" + value1 + "%'");
			break;
		case MORE_THAN:
			buf.append(" > '" + value1 + "'");
			break;
		case LESS_THAN:
			buf.append(" < '" + value1 + "'");
			break;
		}
		return buf.toString();
	}
}

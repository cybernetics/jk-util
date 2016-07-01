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

/**
 * The Class JKTableColumnValue.
 *
 * @author Jalal Kiswani
 * @param <T>
 *            the generic type
 */
public class JKTableColumnValue<T> {
	JKTableColumn tableColumn;
	T value;
	private boolean enabled = true;

	/**
	 * Instantiates a new JK table column value.
	 */
	public JKTableColumnValue() {
	}

	/**
	 * Instantiates a new JK table column value.
	 *
	 * @param tableColumn
	 *            the table column
	 */
	public JKTableColumnValue(final JKTableColumn tableColumn) {
		this.tableColumn = tableColumn;
	}

	/**
	 * Gets the table column.
	 *
	 * @return the table column
	 */
	public JKTableColumn getTableColumn() {
		return this.tableColumn;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public T getValue() {
		return this.value;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Sets the table column.
	 *
	 * @param tableColumn
	 *            the new table column
	 */
	public void setTableColumn(final JKTableColumn tableColumn) {
		this.tableColumn = tableColumn;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(final T value) {
		this.value = value;
	}

}

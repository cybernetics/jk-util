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

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import com.jk.util.JKConversionUtil;

/**
 * The Class JKTableRecord.
 *
 * @author Jalal Kiswani
 */
public class JKTableRecord {
	
	/**
	 * The Enum RecordStatus.
	 *
	 * @author Jalal Kiswani
	 */
	public enum RecordStatus {
		NEW, LATEST, MODIFIED, DELETED;
	}

	Vector<JKTableColumnValue> columnsValues = new Vector<JKTableColumnValue>();
	RecordStatus status;

	/**
	 * Adds the empty value.
	 *
	 * @param col
	 *            the col
	 */
	public void addEmptyValue(final JKTableColumn col) {
		final JKTableColumnValue value = new JKTableColumnValue(col);
		this.columnsValues.add(value);
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Adds the empty values.
	 *
	 * @param tableColumns
	 *            the table columns
	 */
	// ////////////////////////////////////////////////////////////////////
	public void addEmptyValues(final Vector<JKTableColumn> tableColumns) {
		for (final JKTableColumn col : tableColumns) {
			final JKTableColumnValue value = new JKTableColumnValue(col);
			this.columnsValues.add(value);
		}
	}

	/**
	 * Gets the column.
	 *
	 * @param index
	 *            the index
	 * @return the column
	 */
	// ////////////////////////////////////////////////////////////////////
	public JKTableColumn getColumn(final int index) {
		return this.columnsValues.get(index).getTableColumn();
	}

	/**
	 * Gets the column index.
	 *
	 * @param name
	 *            the name
	 * @return the column index
	 */
	// ////////////////////////////////////////////////////////////////////
	public int getColumnIndex(final String name) {
		for (int i = 0; i < this.columnsValues.size(); i++) {
			final JKTableColumnValue value = this.columnsValues.get(i);
			if (value.getTableColumn().getName().toLowerCase().equals(name.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the columns values.
	 *
	 * @return the columns values
	 */
	public Vector<JKTableColumnValue> getColumnsValues() {
		return this.columnsValues;
	}

	// ////////////////////////////////////////////////////////////////////

	/**
	 * Gets the column value.
	 *
	 * @param col
	 *            the col
	 * @return the column value
	 */
	// ////////////////////////////////////////////////////////////////////
	public Object getColumnValue(final int col) {
		return this.columnsValues.get(col).getValue();
	}

	/**
	 * Gets the column value.
	 *
	 * @param name
	 *            the name
	 * @return the column value
	 */
	// ////////////////////////////////////////////////////////////////////
	public Object getColumnValue(final String name) {
		return getColumnValue(getColumnIndex(name));
	}

	/**
	 * Gets the column value as date.
	 *
	 * @param colIndex
	 *            the col index
	 * @return the column value as date
	 */
	public Date getColumnValueAsDate(final int colIndex) {
		return JKConversionUtil.toDate(getColumnValue(colIndex));
	}

	/**
	 * Gets the column value as date.
	 *
	 * @param colName
	 *            the col name
	 * @return the column value as date
	 */
	public Date getColumnValueAsDate(final String colName) {
		return getColumnValueAsDate(getColumnIndex(colName));
	}

	/**
	 * Gets the column value as double.
	 *
	 * @param col
	 *            the col
	 * @return the column value as double
	 */
	// ////////////////////////////////////////////////////////////////////
	public double getColumnValueAsDouble(final int col) {
		final Object value = getColumnValue(col);
		if (value == null) {
			return 0;
		}
		return new Double(value.toString());
	}

	/**
	 * Gets the column value as double.
	 *
	 * @param col
	 *            the col
	 * @param defaultValue
	 *            the default value
	 * @return the column value as double
	 */
	public Double getColumnValueAsDouble(final int col, final double defaultValue) {
		Double value = getColumnValueAsDouble(col);
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Gets the column value as double.
	 *
	 * @param colName
	 *            the col name
	 * @return the column value as double
	 */
	public double getColumnValueAsDouble(final String colName) {
		return getColumnValueAsDouble(getColumnIndex(colName));
	}

	/**
	 * Gets the column value as float.
	 *
	 * @param col
	 *            the col
	 * @return the column value as float
	 */
	// ////////////////////////////////////////////////////////////////////
	public Float getColumnValueAsFloat(final int col) {
		final Object value = getColumnValue(col);
		if (value == null) {
			return null;
		}
		return new Float(value.toString());
	}

	/**
	 * Gets the column value as integer.
	 *
	 * @param col
	 *            the col
	 * @return the column value as integer
	 */
	// ////////////////////////////////////////////////////////////////////
	public int getColumnValueAsInteger(final int col) {
		// Object value = getColumnValue(col);
		// if (value == null) {
		// return 0;
		// }
		// if (value instanceof Boolean) {
		// return (Boolean) value ? 1 : 0;
		// }
		// return new Integer(value.toString());
		return (int) getColumnValueAsDouble(col);
	}

	/**
	 * Gets the column value as integer.
	 *
	 * @param colName
	 *            the col name
	 * @return the column value as integer
	 */
	public int getColumnValueAsInteger(final String colName) {
		return getColumnValueAsInteger(getColumnIndex(colName));
	}

	/**
	 * Gets the field value.
	 *
	 * @param name
	 *            the name
	 * @return the field value
	 */
	public Object getFieldValue(final String name) {
		return getColumnValue(name);
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	// ////////////////////////////////////////////////////////////////////
	public RecordStatus getStatus() {
		return this.status;
	}

	/**
	 * Checks if is column enabled.
	 *
	 * @param col
	 *            the col
	 * @return true, if is column enabled
	 */
	public boolean isColumnEnabled(final int col) {
		return this.columnsValues.get(col).isEnabled();
	}

	/**
	 * Checks if is modified.
	 *
	 * @return true, if is modified
	 */
	public boolean isModified() {
		return getStatus() == RecordStatus.MODIFIED;
	}

	/**
	 * Sets the column enabled.
	 *
	 * @param col
	 *            the col
	 * @param enable
	 *            the enable
	 */
	public void setColumnEnabled(final int col, final boolean enable) {
		this.columnsValues.get(col).setEnabled(enable);
	}

	/**
	 * Sets the columns values.
	 *
	 * @param values
	 *            the new columns values
	 */
	// ////////////////////////////////////////////////////////////////////
	public void setColumnsValues(final Vector<JKTableColumnValue> values) {
		this.columnsValues = values;
	}

	/**
	 * Sets the column value.
	 *
	 * @param columnIndex
	 *            the column index
	 * @param value
	 *            the value
	 */
	public void setColumnValue(final int columnIndex, final Object value) {
		this.columnsValues.get(columnIndex).setValue(value);
	}

	/**
	 * Sets the column value.
	 *
	 * @param colName
	 *            the col name
	 * @param value
	 *            the value
	 */
	public void setColumnValue(final String colName, final Object value) {
		setColumnValue(getColumnIndex(colName), value);
	}

	/**
	 * Sets the field value.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public void setFieldValue(final String name, final Object value) {
		setColumnValue(name, value);
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	// ////////////////////////////////////////////////////////////////////
	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

	/**
	 * To hash.
	 *
	 * @return the hashtable
	 */
	// ////////////////////////////////////////////////////////////////////
	public Hashtable<String, Object> toHash() {
		final Hashtable<String, Object> values = new Hashtable();
		for (int i = 0; i < this.columnsValues.size(); i++) {
			final JKTableColumnValue value = this.columnsValues.get(i);
			values.put(getColumn(i).getName(), value);
		}
		return values;

	}

	/**
	 * To values vector.
	 *
	 * @return the vector
	 */
	// ////////////////////////////////////////////////////////////////////
	public Vector<Object> toValuesVector() {
		final Vector<Object> values = new Vector<Object>();
		for (final JKTableColumnValue value : this.columnsValues) {
			values.add(value);
		}
		return values;
	}

}

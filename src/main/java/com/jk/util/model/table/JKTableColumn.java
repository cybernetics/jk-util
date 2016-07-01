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

import java.math.BigDecimal;
import java.text.Format;
import java.util.Vector;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.jk.locale.JKMessage;

/**
 * The Class JKTableColumn.
 *
 * @author Jalal Kiswani
 */
public class JKTableColumn {
	// actual index
	int index;
	// visible index since mst likely there will be hidden fields
	int visibleIndex;
	String name;
	boolean required;
	boolean editable;
	private String humanName;
	private Format formatter;
	private int preferredWidth;
	private TableCellRenderer cellRenderer;
	private TableCellEditor cellEditor;
	private boolean visible = true;
	private Class columnClass;
	private String columnClassName;
	private int columnType;
	private String columnTypeName;
	Vector<JKColumnFilter> filters;
	private int maxLength;
	private Object defaultValue;

	/**
	 * Gets the column class.
	 *
	 * @return the column class
	 */
	public Class getColumnClass() {
		return this.columnClass == null ? Object.class : this.columnClass;
	}

	/**
	 * Gets the column class name.
	 *
	 * @return the column class name
	 */
	public String getColumnClassName() {
		return this.columnClassName;
	}

	/**
	 * Gets the column type.
	 *
	 * @return the column type
	 */
	public int getColumnType() {
		// Handle this on appropriate way
		return this.columnType;
	}

	/**
	 * Gets the column type name.
	 *
	 * @return the column type name
	 */
	public String getColumnTypeName() {
		return this.columnTypeName;
	}

	/**
	 * Gets the editor.
	 *
	 * @return the editor
	 */
	public TableCellEditor getEditor() {
		return this.cellEditor;
	}

	/**
	 * Gets the formatter.
	 *
	 * @return the formatter
	 */
	public Format getFormatter() {
		return this.formatter;
	}

	/**
	 * Gets the human name.
	 *
	 * @return the human name
	 */
	public String getHumanName() {
		return this.humanName;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Gets the max length.
	 *
	 * @return the max length
	 */
	public int getMaxLength() {
		return this.maxLength;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the preferred width.
	 *
	 * @return the preferred width
	 */
	public int getPreferredWidth() {
		return this.preferredWidth;
	}

	/**
	 * Gets the renderer.
	 *
	 * @return the renderer
	 */
	public TableCellRenderer getRenderer() {
		return this.cellRenderer;
	}

	/**
	 * Gets the visible index.
	 *
	 * @return the visible index
	 */
	public int getVisibleIndex() {
		return isVisible() ? this.visibleIndex : -1;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return true, if is editable
	 */
	public boolean isEditable() {
		return this.editable && isVisible();
	}

	/**
	 * Checks if is numeric.
	 *
	 * @return true, if is numeric
	 */
	public boolean isNumeric() {
		final Class c = getColumnClass();
		return c.equals(Integer.class) || c.equals(Float.class) || c.equals(Long.class) || c.equals(BigDecimal.class);
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
	 * Checks if is visible.
	 *
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * Sets the column class.
	 *
	 * @param columnClass
	 *            the new column class
	 */
	public void setColumnClass(final Class columnClass) {
		this.columnClass = columnClass;
	}

	/**
	 * Sets the column class name.
	 *
	 * @param columnClassName
	 *            the new column class name
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public void setColumnClassName(final String columnClassName) throws ClassNotFoundException {
		if (columnClassName.equals("byte[]")) {
			setColumnClass(Object.class);
		} else {
			setColumnClass(Class.forName(columnClassName));
		}
		this.columnClassName = columnClassName;
	}

	/**
	 * Sets the column type.
	 *
	 * @param columnType
	 *            the new column type
	 */
	public void setColumnType(final int columnType) {
		this.columnType = columnType;
	}

	/**
	 * Sets the column type name.
	 *
	 * @param columnTypeName
	 *            the new column type name
	 */
	public void setColumnTypeName(final String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}

	/**
	 * Sets the default value.
	 *
	 * @param defaultValue
	 *            the new default value
	 */
	public void setDefaultValue(final Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable
	 *            the new editable
	 */
	public void setEditable(final boolean editable) {
		this.editable = editable;
	}

	/**
	 * Sets the editor.
	 *
	 * @param cellEditor
	 *            the new editor
	 */
	public void setEditor(final TableCellEditor cellEditor) {
		this.cellEditor = cellEditor;
	}

	/**
	 * Sets the formatter.
	 *
	 * @param formatter
	 *            the new formatter
	 */
	public void setFormatter(final Format formatter) {
		this.formatter = formatter;
	}

	/**
	 * Sets the human name.
	 *
	 * @param humanName
	 *            the new human name
	 */
	public void setHumanName(final String humanName) {
		this.humanName = humanName;
	}

	/**
	 * Sets the index.
	 *
	 * @param index
	 *            the new index
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * Sets the max length.
	 *
	 * @param maxLength
	 *            the new max length
	 */
	public void setMaxLength(final int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(final String name) {
		this.name = name;
		setHumanName(JKMessage.get(name, true));
	}

	/**
	 * Sets the preferred width.
	 *
	 * @param preferredWidth
	 *            the new preferred width
	 */
	public void setPreferredWidth(final int preferredWidth) {
		this.preferredWidth = preferredWidth;
	}

	/**
	 * Sets the renderer.
	 *
	 * @param cellRenderer
	 *            the new renderer
	 */
	public void setRenderer(final TableCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
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
	 * Sets the visible.
	 *
	 * @param visible
	 *            the new visible
	 */
	public void setVisible(final boolean visible) {
		this.visible = visible;
	}

	/**
	 * Sets the visible index.
	 *
	 * @param visibleIndex
	 *            the new visible index
	 */
	public void setVisibleIndex(final int visibleIndex) {
		this.visibleIndex = visibleIndex;
	}

}
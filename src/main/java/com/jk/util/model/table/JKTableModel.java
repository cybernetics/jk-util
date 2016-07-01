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

import java.text.Format;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.jk.util.JKNumbersUtil;
import com.jk.util.model.table.JKTableRecord.RecordStatus;

/**
 * The Class JKTableModel.
 *
 * @author Jalal Kiswani
 */
public class JKTableModel extends AbstractTableModel {
	/**
	 *
	 * @author jalal
	 *
	 */
	class ColumnVisiblityManagar {
		final Vector<JKTableColumn> columns;

		// ///////////////////////////////////////////////////////////
		public ColumnVisiblityManagar(final Vector<JKTableColumn> columns) {
			this.columns = columns;
			refreshVisibility();
		}

		// ///////////////////////////////////////////////////////////
		public int getActualIndexFromVisibleIndex(final int visibleIndex) {
			return getFSTableColumnFromVisibleIndex(visibleIndex).getIndex();
		}

		// ///////////////////////////////////////////////////////////
		public Vector<JKTableColumn> getColumns() {
			return this.columns;
		}

		// ///////////////////////////////////////////////////////////
		public JKTableColumn getFSTableColumnFromVisibleIndex(final int visibleIndex) {
			for (final JKTableColumn col : this.columns) {
				if (col.getVisibleIndex() == visibleIndex) {
					return col;
				}
			}
			throw new ArrayIndexOutOfBoundsException(visibleIndex);
		}
		// ///////////////////////////////////////////////////////////

		public int getVisibleColumnCount() {
			int count = 0;
			for (final JKTableColumn col : this.columns) {
				if (col.isVisible()) {
					count++;
				}
			}
			return count;
		}

		// ///////////////////////////////////////////////////////////
		public int getVisibleIndexFromActualIndex(final int actualIndex) {
			return this.columns.get(actualIndex).getVisibleIndex();
		}

		// ///////////////////////////////////////////////////////////
		protected void refreshVisibility() {
			int visibleIndex = 0;
			for (final JKTableColumn col : this.columns) {
				if (col.isVisible()) {
					col.setVisibleIndex(visibleIndex++);
				} else {
					col.setVisibleIndex(-1);
				}
			}
		}

	}

	/**
	 *
	 */
	private static final long serialVersionUID = -6003811835691538215L;

	private final Vector<JKTableColumn> tableColumns = new Vector<JKTableColumn>();
	private final Vector<JKTableRecord> records = new Vector<JKTableRecord>();
	private final ColumnVisiblityManagar visibilityManager = new ColumnVisiblityManagar(this.tableColumns);
	private final Vector<JKTableRecord> deletedRecords = new Vector<JKTableRecord>();
	boolean modified;

	boolean allowDelete;

	/**
	 * Instantiates a new JK table model.
	 */
	// /////////////////////////////////////////////////////////////////////////
	public JKTableModel() {
	}

	/**
	 * Adds the JK table column.
	 *
	 * @param col
	 *            the col
	 */
	// ///////////////////////////////////////////////////////////////////
	public void addJKTableColumn(final JKTableColumn col) {
		col.setIndex(this.tableColumns.size());
		this.tableColumns.add(col);
		this.visibilityManager.refreshVisibility();
		fireTableStructureChanged();
	}

	/**
	 * Adds the record.
	 *
	 * @return the JK table record
	 */
	// ///////////////////////////////////////////////////////////////////////////////////////
	public JKTableRecord addRecord() {
		final JKTableRecord record = createEmptyRecord();
		addRecord(record);
		return record;
	}

	/**
	 * Adds the record.
	 *
	 * @param record
	 *            the record
	 */
	// ///////////////////////////////////////////////////////////////////////////////////////
	public void addRecord(final JKTableRecord record) {
		geteRecords().add(record);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}

	/**
	 * Clear records.
	 */
	// //////////////////////////////////////////////////////////////////////
	public void clearRecords() {
		this.records.clear();
		fireTableDataChanged();
	}

	// /////////////////////////////////////////////////////////
	protected JKTableRecord createEmptyRecord() {
		final JKTableRecord record = new JKTableRecord();
		record.addEmptyValues(this.tableColumns);
		record.setStatus(RecordStatus.NEW);
		return record;
	}

	/**
	 * Delete row.
	 *
	 * @param selectedRow
	 *            the selected row
	 * @return the JK table record
	 */
	// /////////////////////////////////////////////////////////
	public JKTableRecord deleteRow(final int selectedRow) {
		final JKTableRecord removed = removeRecord(selectedRow);
		removed.setStatus(RecordStatus.DELETED);
		// Object removed = getDataVector().remove(selectedRow);
		if (removed != null) {
			this.deletedRecords.add(removed);
		}
		fireTableRowsDeleted(selectedRow, selectedRow);
		return removed;
	}

	/**
	 * Delete rows.
	 *
	 * @param rows
	 *            the rows
	 */
	// //////////////////////////////////////////////////////////////////////
	public void deleteRows(final int[] rows) {
		for (int i = rows.length - 1; i >= 0; i--) {
			deleteRow(rows[i]);
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Fire table column data changed.
	 *
	 * @param col
	 *            the col
	 */
	public void fireTableColumnDataChanged(final int col) {
		for (int i = 0; i < getRowCount(); i++) {
			fireTableCellUpdated(i, col);
		}
	}

	/**
	 * Gets the actual column count.
	 *
	 * @return the actual column count
	 */
	public int getActualColumnCount() {
		return this.tableColumns.size();
	}

	/**
	 * Gets the actual column index from visible.
	 *
	 * @param visibleIndex
	 *            the visible index
	 * @return the actual column index from visible
	 */
	public int getActualColumnIndexFromVisible(final int visibleIndex) {
		return getTableColumn(visibleIndex, true).getIndex();
	}

	/**
	 * Gets the actual column name.
	 *
	 * @param index
	 *            the index
	 * @return the actual column name
	 */
	// /////////////////////////////////////////////////////////////////////
	public String getActualColumnName(final int index) {
		return getTableColumn(index).getName();
	}

	/**
	 * Gets the cell editor.
	 *
	 * @param column
	 *            the column
	 * @return the cell editor
	 */
	// /////////////////////////////////////////////////////////
	public TableCellEditor getCellEditor(final int column) {
		return getTableColumn(column).getEditor();
	}

	/**
	 * Gets the cell renderer.
	 *
	 * @param column
	 *            the column
	 * @return the cell renderer
	 */
	// /////////////////////////////////////////////////////////
	public TableCellRenderer getCellRenderer(final int column) {
		return getTableColumn(column).getRenderer();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	// ///////////////////////////////////////////////////////////////////////////////
	@Override
	public Class getColumnClass(final int columnIndex) {
		return getTableColumn(columnIndex).getColumnClass();
		// try {
		// String columnClassName =
		// getTableColumn(columnIndex).getColumnClassName();
		// //
		// System.out.println("Coluinm name : "+getColumnName(columnIndex)+"
		// class = "+
		// // columnClassName);
		// Class<?> clas = Class.forName(columnClassName);
		// if (clas.isInstance(BigDecimal.class)) {
		// return Double.class;
		// }
		// return clas;
		// } catch (Exception e) {
		// ExceptionUtil.handle(e);
		// return null;
		// }
	}

	// // /////////////////////////////////////////////////////////
	// public FSTableColumn getTableColumn(int col) {
	// return getTableColumn(col, true);
	// }

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	// /////////////////////////////////////////////////////////////////////
	@Override
	public int getColumnCount() {
		return this.visibilityManager.getVisibleColumnCount();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(final int visibleColumnIndex) {
		// FSTableColumn tableColumn = getTableColumn(visibleColumnIndex);
		// return
		// tableColumn.getHumanName()+"-"+tableColumn.getVisibleIndex()+'-'+tableColumn.getIndex();
		return getTableColumn(visibleColumnIndex).getHumanName();
	}

	/**
	 * Gets the column type.
	 *
	 * @param col
	 *            the col
	 * @return the column type
	 */
	// /////////////////////////////////////////////////////////////////////
	public int getColumnType(final int col) {
		return getTableColumn(col).getColumnType();
	}

	/**
	 * Gets the colunm index.
	 *
	 * @param name
	 *            the name
	 * @return the colunm index
	 */
	// /////////////////////////////////////////////////////////////////////
	public int getColunmIndex(final String name) {
		for (int i = 0; i < getColumnCount(); i++) {
			if (getActualColumnName(i).trim().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the colunm index by name.
	 *
	 * @param colName
	 *            the col name
	 * @return the colunm index by name
	 */
	// /////////////////////////////////////////////////////////////////////////////
	public int getColunmIndexByName(final String colName) {
		for (final JKTableColumn col : this.tableColumns) {
			if (col.getName().equalsIgnoreCase(colName)) {
				return col.getVisibleIndex();
			}
		}
		return -1;
	}

	/**
	 * Gets the colunm sum.
	 *
	 * @param col
	 *            the col
	 * @return the colunm sum
	 */
	// /////////////////////////////////////////////////////////////////////////
	public double getColunmSum(final int col) {
		double sum = 0;
		for (int i = 0; i < getRowCount(); i++) {
			final double number = getValueAtAsDouble(i, col);
			sum = JKNumbersUtil.addAmounts(sum, number);
		}
		return sum;
	}


	/**
	 * Gets the deleted records.
	 *
	 * @return the deleted records
	 */
	// /////////////////////////////////////////////////////////
	public Vector<JKTableRecord> getDeletedRecords() {
		return this.deletedRecords;
	}

	/**
	 * Gets the deleted records as data vector.
	 *
	 * @return the deleted records as data vector
	 */
	// ///////////////////////////////////////////////////////////////////
	public Vector<Vector> getDeletedRecordsAsDataVector() {
		final Vector<Vector> data = new Vector<Vector>();
		for (final JKTableRecord rec : this.deletedRecords) {
			data.add(rec.toValuesVector());
		}
		return data;
	}

	private Vector<JKTableRecord> geteRecords() {
		return this.records;
	}

	/**
	 * Gets the formatter.
	 *
	 * @param col
	 *            the col
	 * @return the formatter
	 */
	// /////////////////////////////////////////////////////////
	public Format getFormatter(final int col) {
		return getTableColumn(col).getFormatter();
	}

	/**
	 * Gets the integer colunm sum.
	 *
	 * @param col
	 *            the col
	 * @return the integer colunm sum
	 */
	// /////////////////////////////////////////////////////////////////////////
	public int getIntegerColunmSum(final int col) {
		return (int) getColunmSum(col);
	}

	/**
	 * Gets the preffered width.
	 *
	 * @param column
	 *            the column
	 * @return the preffered width
	 */
	// /////////////////////////////////////////////////////////
	public int getPrefferedWidth(final int column) {
		return getTableColumn(column).getPreferredWidth();
	}

	/**
	 * Gets the record.
	 *
	 * @param row
	 *            the row
	 * @return the record
	 */
	// /////////////////////////////////////////////////////////////////////////////
	public JKTableRecord getRecord(final int row) {
		if (row >= getRowCount()) {
			throw new IllegalStateException("Row : " + row + " is out of index");
		}
		return this.records.get(row);
	}

	/**
	 * Gets the records.
	 *
	 * @return the records
	 */
	// ///////////////////////////////////////////////////////////////////
	public Vector<JKTableRecord> getRecords() {
		return this.records;
	}

	/**
	 * Gets the records as data vector.
	 *
	 * @return the records as data vector
	 */
	// ///////////////////////////////////////////////////////////////////
	public Vector<Vector> getRecordsAsDataVector() {
		final Vector<Vector> data = new Vector<Vector>();
		for (final JKTableRecord rec : this.records) {
			data.add(rec.toValuesVector());
		}
		return data;
	}

	// // /////////////////////////////////////////////////////////
	// public Vector<FSTableColumn> getTableColumns() {
	// return tableColumns;
	// }

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	// /////////////////////////////////////////////////////////////////////////////
	@Override
	public int getRowCount() {
		return this.tableColumns.size() == 0 || this.records == null ? 0 : this.records.size();
	}

	/**
	 * Gets the table column.
	 *
	 * @param visibleColumnIndex
	 *            the visible column index
	 * @return the table column
	 */
	// /////////////////////////////////////////////////////////////////////////////
	public JKTableColumn getTableColumn(final int visibleColumnIndex) {
		return getTableColumn(visibleColumnIndex, true);
	}

	// /////////////////////////////////////////////////////////
	/**
	 * return NULL of col is out of bound.
	 *
	 * @param col
	 *            the col
	 * @param visibleIndex
	 *            the visible index
	 * @return the table column
	 */
	public JKTableColumn getTableColumn(final int col, final boolean visibleIndex) {
		int actualIndex;
		if (visibleIndex) {
			actualIndex = this.visibilityManager.getActualIndexFromVisibleIndex(col);
		} else {
			actualIndex = col;
		}
		return this.tableColumns.get(actualIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	// /////////////////////////////////////////////////////////////////////////////
	@Override
	public Object getValueAt(final int row, final int visibleColumnIndex) {
		final int actualIndex = this.visibilityManager.getActualIndexFromVisibleIndex(visibleColumnIndex);
		return getRecords().get(row).getColumnValue(actualIndex);
	}

	/**
	 * Gets the value at as double.
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 * @return the value at as double
	 */
	// ///////////////////////////////////////////////////////////////////////////////////////
	public double getValueAtAsDouble(final int row, final int col) {
		final Object valueAt = getValueAt(row, col);
		double number = 0;
		if (valueAt != null && !valueAt.toString().equals("")) {
			number = Double.parseDouble(valueAt.toString().trim());
		}
		return number;
	}

	/**
	 * Gets the value at as float.
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 * @return the value at as float
	 */
	// ///////////////////////////////////////////////////////////////////////////////////////
	public float getValueAtAsFloat(final int row, final int col) {
		final Object valueAt = getValueAt(row, col);
		float number = 0;
		if (valueAt != null && !valueAt.toString().equals("")) {
			number = Float.parseFloat(valueAt.toString().trim());
		}
		return number;
	}

	/**
	 * Gets the value at as integer.
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 * @return the value at as integer
	 */
	public int getValueAtAsInteger(final int row, final int col) {
		final Object valueAt = getValueAt(row, col);
		int number = 0;
		if (valueAt != null && !valueAt.toString().equals("")) {
			number = Integer.parseInt(valueAt.toString().trim());
		}
		return number;

	}

	/**
	 * Gets the visible column index from actual.
	 *
	 * @param actualIndex
	 *            the actual index
	 * @return the visible column index from actual
	 */
	// //////////////////////////////////////////////////////////////////////
	public int getVisibleColumnIndexFromActual(final int actualIndex) {
		return getTableColumn(actualIndex, false).getVisibleIndex();
	}

	// //
	// /////////////////////////////////////////////////////////////////////////////
	// public FSTableColumn getTableColumn(int index, boolean createIfNotExists)
	// {
	// if (index < tableColumns.size()) {
	// return tableColumns.get(index);
	// }
	// if (create) {
	// FSTableColumn col = createTableColumn(index);
	// addFSTableColumn(col);
	// return col;
	// }
	// throw new ArrayIndexOutOfBoundsException(index);
	// }

	/**
	 * Insert record.
	 *
	 * @param selectedRow
	 *            the selected row
	 */
	// /////////////////////////////////////////////////////////
	public void insertRecord(final int selectedRow) {
		insertRecord(selectedRow, createEmptyRecord());
	}

	/**
	 * Insert record.
	 *
	 * @param row
	 *            the row
	 * @param record
	 *            the record
	 */
	// /////////////////////////////////////////////////////////
	public void insertRecord(final int row, final JKTableRecord record) {
		this.records.insertElementAt(record, row);
		fireTableRowsInserted(row, row);
	}

	/**
	 * Checks if is all data valid.
	 *
	 * @return true, if is all data valid
	 */
	// /////////////////////////////////////////////////////////
	public boolean isAllDataValid() {
		// Make this method smatert
		for (final JKTableColumn col : this.tableColumns) {
			final int lastRow = getRowCount() - 1;
			if (col.isVisible() && col.isRequired()) {
				final Object colValue = getValueAt(lastRow, col.getIndex());
				if (colValue == null || colValue.toString().equals("")) {
					return false;// dont allow
				}
			}
		}
		return true;
	}

	/**
	 * Checks if is allow delete.
	 *
	 * @return true, if is allow delete
	 */
	// //////////////////////////////////////////////////////////////////////
	public boolean isAllowDelete() {
		return this.allowDelete || isEditable();
	}

	/**
	 * Checks if is data modified.
	 *
	 * @return true, if is data modified
	 */
	// /////////////////////////////////////////////////////////
	public boolean isDataModified() {
		if (this.deletedRecords.size() > 0) {
			return true;
		}
		for (final JKTableRecord rec : this.records) {
			if (rec.getStatus() == RecordStatus.MODIFIED) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return true, if is editable
	 */
	// /////////////////////////////////////////////////////////
	public boolean isEditable() {
		// return true if any cell is editable
		for (final JKTableColumn col : this.tableColumns) {
			if (col.isEditable()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if is editable.
	 *
	 * @param column
	 *            the column
	 * @return true, if is editable
	 */
	// /////////////////////////////////////////////////////////
	public boolean isEditable(final int column) {
		return getTableColumn(column).isEditable();
	}

	/**
	 * Checks if is editable.
	 *
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 * @return true, if is editable
	 */
	public boolean isEditable(final int row, final int column) {
		if (isEditable(column)) {
			final int actualIndex = getTableColumn(column).getIndex();
			final JKTableRecord record = getRecord(row);
			return record.isColumnEnabled(actualIndex);
		}
		return false;
	}

	/**
	 * Checks if is numeric clumn.
	 *
	 * @param visibleColIndex
	 *            the visible col index
	 * @return true, if is numeric clumn
	 */
	// ///////////////////////////////////////////////////////////////////////////////////////
	public boolean isNumericClumn(final int visibleColIndex) {
		return getTableColumn(visibleColIndex).isNumeric();
	}

	// /////////////////////////////////////////////////////////
	protected boolean isValidTableColumnIndex(final int actualIndex) {
		return actualIndex >= 0 && actualIndex < this.tableColumns.size();
	}

	/**
	 * Checks if is visible.
	 *
	 * @param col
	 *            the col
	 * @return true, if is visible
	 */
	// /////////////////////////////////////////////////////////////////////
	public boolean isVisible(final int col) {
		return getTableColumn(col).isVisible();
	}

	/**
	 * Refresh visibility.
	 */
	public void refreshVisibility() {
		this.visibilityManager.refreshVisibility();
		fireTableStructureChanged();
	}

	// /////////////////////////////////////////////////////////////////////

	/**
	 * Removes the record.
	 *
	 * @param row
	 *            the row
	 * @return the JK table record
	 */
	// /////////////////////////////////////////////////////////
	public JKTableRecord removeRecord(final int row) {
		return this.records.remove(row);
	}

	/**
	 * Reset records.
	 */
	// ///////////////////////////////////////////////////////////////////
	public void resetRecords() {
		this.records.clear();
		// fireTableDataChanged();
	}

	/**
	 * Sets the allow delete.
	 *
	 * @param allowDelete
	 *            the new allow delete
	 */
	// //////////////////////////////////////////////////////////////////////
	public void setAllowDelete(final boolean allowDelete) {
		this.allowDelete = allowDelete;
	}

	/**
	 * Sets the column value.
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 * @param value
	 *            the value
	 * @param visibleIndex
	 *            the visible index
	 */
	// //////////////////////////////////////////////////////////////////////////////////
	public void setColumnValue(final int row, final int col, final Object value, final boolean visibleIndex) {
		int actualColumn = col;
		if (visibleIndex) {
			actualColumn = getActualColumnIndexFromVisible(col);
		}
		getRecord(row).setColumnValue(actualColumn, value);
		fireTableCellUpdated(row, col);
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable
	 *            the new editable
	 */
	// /////////////////////////////////////////////////////////
	public void setEditable(final boolean editable) {
		for (final JKTableColumn col : this.tableColumns) {
			col.setEditable(editable);
		}
	}

	/**
	 * Sets the editable.
	 *
	 * @param column
	 *            the column
	 * @param editable
	 *            the editable
	 */
	// /////////////////////////////////////////////////////////
	public void setEditable(final int column, final boolean editable) {
		getTableColumn(column).setEditable(editable);
	}

	/**
	 * Sets the editable.
	 *
	 * @param row
	 *            the row
	 * @param col
	 *            the col
	 * @param enable
	 *            the enable
	 */
	public void setEditable(final int row, final int col, final boolean enable) {
		final int actualIndex = getTableColumn(col).getIndex();
		getRecord(row).setColumnEnabled(actualIndex, enable);
	}

	/**
	 * Sets the editor.
	 *
	 * @param colunm
	 *            the colunm
	 * @param cellEditor
	 *            the cell editor
	 */
	// /////////////////////////////////////////////////////////
	public void setEditor(final int colunm, final TableCellEditor cellEditor) {
		getTableColumn(colunm).setEditor(cellEditor);
	}

	/**
	 * Sets the formatter.
	 *
	 * @param col
	 *            the col
	 * @param formatter
	 *            the formatter
	 */
	// /////////////////////////////////////////////////////////
	public void setFormatter(final int col, final Format formatter) {
		getTableColumn(col).setFormatter(formatter);
	}

	/**
	 * Sets the preferred width.
	 *
	 * @param col
	 *            the col
	 * @param width
	 *            the width
	 */
	// /////////////////////////////////////////////////////////
	public void setPreferredWidth(final int col, final int width) {
		getTableColumn(col).setPreferredWidth(width);
	}

	/**
	 * Sets the renderer.
	 *
	 * @param col
	 *            the col
	 * @param cellRenderer
	 *            the cell renderer
	 */
	// /////////////////////////////////////////////////////////
	public void setRenderer(final int col, final TableCellRenderer cellRenderer) {
		getTableColumn(col).setRenderer(cellRenderer);
	}

	/**
	 * Sets the required.
	 *
	 * @param col
	 *            the col
	 * @param required
	 *            the required
	 */
	// /////////////////////////////////////////////////////////
	public void setRequired(final int col, final boolean required) {
		getTableColumn(col).setRequired(required);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	// /////////////////////////////////////////////////////////
	@Override
	public void setValueAt(final Object value, final int rowIndex, final int visibleIndex) {
		final int actualColIndex = this.visibilityManager.getActualIndexFromVisibleIndex(visibleIndex);
		final JKTableRecord record = this.records.get(rowIndex);
		record.setColumnValue(actualColIndex, value);
		record.setStatus(RecordStatus.MODIFIED);
		fireTableCellUpdated(rowIndex, visibleIndex);
		this.modified = true;
	}

	/**
	 * Sets the visible.
	 *
	 * @param col
	 *            the col
	 * @param visible
	 *            the visible
	 */
	// /////////////////////////////////////////////////////////
	public void setVisible(final int col, final boolean visible) {
		getTableColumn(col).setVisible(visible);
		refreshVisibility();
	}

	/**
	 * Sets the visible by actual index.
	 *
	 * @param colunmIndex
	 *            the colunm index
	 * @param visible
	 *            the visible
	 */
	// //////////////////////////////////////////////////////////////////////
	public void setVisibleByActualIndex(final int colunmIndex, final boolean visible) {
		getTableColumn(colunmIndex, false).setVisible(visible);
		refreshVisibility();
	}

	/**
	 * Adds the JK table column.
	 *
	 * @param keyLabel
	 *            the key label
	 */
	public void addJKTableColumn(String keyLabel) {
		JKTableColumn col=new JKTableColumn();
		col.setName(keyLabel);
		addJKTableColumn(col);
	}
}

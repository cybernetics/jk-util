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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import com.jk.logging.JKLogger;
import com.jk.logging.JKLoggerFactory;
import com.jk.util.model.table.JKTableModel;

/**
 * <p>
 * Title: QAC
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company:
 * </p>
 * .
 *
 * @author not attributable
 * @version 1.0
 */
public class JKExcelUtil {
	private static JFileChooser fileChooser = new JFileChooser();

	/**
	 * Builds the excel sheet.
	 *
	 * @param model
	 *            ArrayList
	 */
	public static void buildExcelSheet(final JKTableModel model) {
		final JKExcelUtil sheet = new JKExcelUtil(model);
		try {
			final File file = JKIOUtil.createTempFile("xls");
			sheet.writeTo(file);
			JKIOUtil.executeFile(file.getAbsolutePath());
			file.deleteOnExit();
		} catch (final IOException ex) {
			throw new RuntimeException(ex);
		}
	}


	/**
	 * Parses the file.
	 *
	 * @param selectedFile
	 *            the selected file
	 * @param tableMeta
	 *            the table meta
	 * @param headers
	 *            the headers
	 * @return the array list
	 * @throws ParserException
	 *             the parser exception
	 */


	// ArrayList of Records
	private final HSSFSheet sheet;

	private final HSSFWorkbook workbook;

	private final JKTableModel model;

	private JKLogger logger=JKLoggerFactory.getLogger(getClass());

	/**
	 * Instantiates a new excel util.
	 *
	 * @param model
	 *            the model
	 */
	public JKExcelUtil(final JKTableModel model) {
		this.workbook = new HSSFWorkbook();
		this.sheet = this.workbook.createSheet();
		this.model = model;
		createColumnHeaders();
		createRows();
		setColumnsWidth();
	}

	/**
	 */
	protected void createColumnHeaders() {
		final HSSFRow headersRow = this.sheet.createRow(0);
		final HSSFFont font = this.workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		final HSSFCellStyle style = this.workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		int counter = 1;
		for (int i = 0; i < this.model.getColumnCount(); i++) {
			final HSSFCell cell = headersRow.createCell(counter++);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(this.model.getColumnName(i));
			cell.setCellStyle(style);
		}
	}

	/**
	 *
	 * @param rowIndex
	 *            int
	 */
	protected void createRow(final int rowIndex) {
		final HSSFRow row = this.sheet.createRow(rowIndex + 1); // since the
																// rows in
		// excel starts from 1
		// not 0
		final HSSFCellStyle style = this.workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		int counter = 1;
		for (int i = 0; i < this.model.getColumnCount(); i++) {
			final HSSFCell cell = row.createCell(counter++);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			final Object value = this.model.getValueAt(rowIndex, i);
			setValue(cell, value);
			cell.setCellStyle(style);
		}
	}

	/**
	 */
	protected void createRows() {
		for (int i = 0; i < this.model.getRowCount(); i++) {
			createRow(i);
		}
	}

	/**
	 */
	protected void setColumnsWidth() {
		int counter = 1;
		for (int i = 0; i < this.model.getColumnCount(); i++) {
			if (this.model.isVisible(i)) {
				this.sheet.autoSizeColumn(counter++);
				// sheet.setColumnWidth((short) i, (short)
				// (model.getColunmWidth(i) * 255));
			}
		}
	}

	/**
	 * @param cell
	 * @param value
	 */
	private void setValue(final HSSFCell cell, final Object value) {
		if (value == null) {
			cell.setCellValue("-");
		} else if (value instanceof Float || value instanceof Double || value instanceof Integer || value instanceof Long
				|| value instanceof BigDecimal) {
			cell.setCellValue(Double.parseDouble(value.toString()));
		} else if (value instanceof String) {
			cell.setCellValue(value.toString());
		} else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		} else {
			logger.info("No Special excel r endering for class : " + value.getClass().getName());
			cell.setCellValue(value.toString());
		}
	}

	/**
	 * Write to.
	 *
	 * @param file
	 *            File
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void writeTo(final File file) throws IOException {
		final OutputStream fout = new FileOutputStream(file);
		this.workbook.write(fout);
		fout.close();
	}

	/**
	 * Write to.
	 *
	 * @param out
	 *            OutputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void writeTo(final OutputStream out) throws IOException {
		this.workbook.write(out);
	}
}

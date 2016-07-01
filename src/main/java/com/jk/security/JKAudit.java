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
package com.jk.security;

import java.util.Date;

import com.jk.util.JKObjectUtil;

/**
 * The Class JKAudit.
 *
 * @author Jalal Kiswani
 */
public class JKAudit {
	int auditId;
	JKUser user = JKSecurityManager.isUserLoggedIn() ? JKSecurityManager.getCurrentUser() : null;
	Date date;
	JKAuditType auditType;
	Object businessRecordId;
	String oldValue;
	String newValue;
	String description;
	private String tableName;
	private String gui;

	/**
	 * Gets the audit id.
	 *
	 * @return the audit id
	 */
	public int getAuditId() {
		return this.auditId;
	}

	/**
	 * Gets the audit text.
	 *
	 * @return the audit text
	 */
	public String getAuditText() {
		final StringBuffer b = new StringBuffer();
		b.append(getOldValue().replaceAll(",", "\n"));
		b.append("-----------------------------------------\n");
		b.append(getNewValue().replaceAll(",", "\n"));
		b.append("-----------------------------------------\n");
		return b.toString();
	}

	/**
	 * Gets the audit type.
	 *
	 * @return the audit type
	 */
	public JKAuditType getAuditType() {
		return this.auditType;
	}

	/**
	 * Gets the business record id.
	 *
	 * @return the business record id
	 */
	public Object getBusinessRecordId() {
		return this.businessRecordId;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the gui.
	 *
	 * @return the gui
	 */
	public String getGui() {
		return this.gui;
	}

	/**
	 * Gets the new value.
	 *
	 * @return the new value
	 */
	public String getNewValue() {
		return this.newValue;
	}

	/**
	 * Gets the old value.
	 *
	 * @return the old value
	 */
	public String getOldValue() {
		return this.oldValue;
	}

	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public JKUser getUser() {
		return this.user == null ? new JKUser(1) : this.user;
	}

	/**
	 * Sets the audit id.
	 *
	 * @param auditId
	 *            the new audit id
	 */
	public void setAuditId(final int auditId) {
		this.auditId = auditId;
	}

	/**
	 * Sets the audit type.
	 *
	 * @param auditType
	 *            the new audit type
	 */
	public void setAuditType(final JKAuditType auditType) {
		this.auditType = auditType;
	}

	/**
	 * Sets the business record id.
	 *
	 * @param businessRecordId
	 *            the new business record id
	 */
	public void setBusinessRecordId(final Object businessRecordId) {
		this.businessRecordId = businessRecordId;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Sets the gui.
	 *
	 * @param gui
	 *            the new gui
	 */
	public void setGui(final Object gui) {
		setGui(JKObjectUtil.toXml(gui));
	}

	/**
	 * Sets the gui.
	 *
	 * @param gui
	 *            the new gui
	 */
	public void setGui(final String gui) {
		this.gui = gui;
	}

	/**
	 * Sets the new value.
	 *
	 * @param newValue
	 *            the new new value
	 */
	public void setNewValue(final String newValue) {
		this.newValue = newValue;
	}

	/**
	 * Sets the old value.
	 *
	 * @param oldValue
	 *            the new old value
	 */
	public void setOldValue(final String oldValue) {
		this.oldValue = oldValue;
	}

	/**
	 * Sets the table name.
	 *
	 * @param recordName
	 *            the new table name
	 */
	public void setTableName(final String recordName) {
		this.tableName = recordName;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(final JKUser user) {
		this.user = user;
	}

}

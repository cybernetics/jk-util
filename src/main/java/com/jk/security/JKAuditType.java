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


/**
 * The Class JKAuditType.
 *
 * @author Jalal Kiswani
 */
public class JKAuditType {
	
	/** The audit add record. */
	public static JKAuditType AUDIT_ADD_RECORD = new JKAuditType(1, "ADD");
	
	/** The audit update record. */
	public static JKAuditType AUDIT_UPDATE_RECORD = new JKAuditType(2, "UPDATE");
	
	/** The audit delete record. */
	public static JKAuditType AUDIT_DELETE_RECORD = new JKAuditType(3, "DELETE");
	
	/** The audit login. */
	public static JKAuditType AUDIT_LOGIN = new JKAuditType(4, "LOGIN");
	
	/** The audit logout. */
	public static JKAuditType AUDIT_LOGOUT = new JKAuditType(5, "LOGOUT");

	int auditTypeId;
	String auditTypeName;
	String module;

	/**
	 * Instantiates a new JK audit type.
	 *
	 * @param id
	 *            the id
	 */
	public JKAuditType(final int id) {
		this.auditTypeId = id;
	}

	/**
	 * Instantiates a new JK audit type.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 */
	public JKAuditType(final int id, final String name) {
		this.auditTypeId = id;
		setAuditTypeName(name);
	}

	/**
	 * Gets the audit type.
	 *
	 * @return the audit type
	 */
	public int getAuditType() {
		return this.auditTypeId;
	}

	/**
	 * Gets the audit type name.
	 *
	 * @return the audit type name
	 */
	public String getAuditTypeName() {
		return this.auditTypeName;
	}

	/**
	 * Gets the module.
	 *
	 * @return the module
	 */
	public String getModule() {
		return this.module;
	}

	/**
	 * Sets the audit type.
	 *
	 * @param auditType
	 *            the new audit type
	 */
	public void setAuditType(final int auditType) {
		this.auditTypeId = auditType;
	}

	/**
	 * Sets the audit type name.
	 *
	 * @param auditTypeName
	 *            the new audit type name
	 */
	public void setAuditTypeName(final String auditTypeName) {
		this.auditTypeName = auditTypeName;
	}

	/**
	 * Sets the module.
	 *
	 * @param module
	 *            the new module
	 */
	public void setModule(final String module) {
		this.module = module;
	}
}

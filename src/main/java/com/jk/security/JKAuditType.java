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


public class JKAuditType {
	public static JKAuditType AUDIT_ADD_RECORD = new JKAuditType(1, "ADD");
	public static JKAuditType AUDIT_UPDATE_RECORD = new JKAuditType(2, "UPDATE");
	public static JKAuditType AUDIT_DELETE_RECORD = new JKAuditType(3, "DELETE");
	public static JKAuditType AUDIT_LOGIN = new JKAuditType(4, "LOGIN");
	public static JKAuditType AUDIT_LOGOUT = new JKAuditType(5, "LOGOUT");

	int auditTypeId;
	String auditTypeName;
	String module;

	public JKAuditType(final int id) {
		this.auditTypeId = id;
	}

	public JKAuditType(final int id, final String name) {
		this.auditTypeId = id;
		setAuditTypeName(name);
	}

	public int getAuditType() {
		return this.auditTypeId;
	}

	public String getAuditTypeName() {
		return this.auditTypeName;
	}

	public String getModule() {
		return this.module;
	}

	public void setAuditType(final int auditType) {
		this.auditTypeId = auditType;
	}

	public void setAuditTypeName(final String auditTypeName) {
		this.auditTypeName = auditTypeName;
	}

	public void setModule(final String module) {
		this.module = module;
	}
}

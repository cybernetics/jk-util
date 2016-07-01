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

import java.util.List;

import com.jk.util.JKStringUtil;

/**
 * The Class JKUser.
 *
 * @author Jalal Kiswani
 */
public class JKUser {
	private int userRecordId;
	private String userId;
	private String fullName;
	private String password;
	private int status;

	private boolean disabled;
	private List<JKPrivilige> priviliges;

	/**
	 * Instantiates a new JK user.
	 */
	public JKUser() {
	}

	/**
	 * Instantiates a new JK user.
	 *
	 * @param userRecordId
	 *            the user record id
	 */
	public JKUser(final int userRecordId) {
		this.userRecordId = userRecordId;
	}

	/**
	 * Instantiates a new JK user.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param fullName
	 *            the full name
	 */
	public JKUser(final int id, final String name, final String fullName) {
		this.userRecordId = id;
		this.userId = name;
		this.fullName = fullName;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		if (JKStringUtil.isEmpty(this.fullName)) {
			return getUserId();
		}
		return this.fullName;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return getUserRecordId();
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Gets the user record id.
	 *
	 * @return the user record id
	 */
	public int getUserRecordId() {
		return this.userRecordId;
	}

	/**
	 * Checks if is disabled.
	 *
	 * @return true, if is disabled
	 */
	public boolean isDisabled() {
		return this.disabled;
	}

	/**
	 * Sets the disabled.
	 *
	 * @param disabled
	 *            the new disabled
	 */
	public void setDisabled(final boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * Sets the full name.
	 *
	 * @param name
	 *            the new full name
	 */
	public void setFullName(final String name) {
		this.fullName = name;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(final int status) {
		this.status = status;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * Sets the user record id.
	 *
	 * @param userRecordId
	 *            the new user record id
	 */
	public void setUserRecordId(final int userRecordId) {
		this.userRecordId = userRecordId;
	}

	/**
	 * Sets the priviliges.
	 *
	 * @param priviliges
	 *            the new priviliges
	 */
	public void setPriviliges(List<JKPrivilige> priviliges) {
		this.priviliges = priviliges;
	}

	/**
	 * Gets the priviliges.
	 *
	 * @return the priviliges
	 */
	public List<JKPrivilige> getPriviliges() {
		return priviliges;
	}

}

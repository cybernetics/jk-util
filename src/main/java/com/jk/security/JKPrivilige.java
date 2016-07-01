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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

/**
 * The Class JKPrivilige.
 *
 * @author Jalal Kiswani
 */
public class JKPrivilige {

	int priviligeId;
	String priviligeName;
	JKPrivilige parentPrivlige;
	String desc;
	ArrayList<JKPrivilige> childs = new ArrayList<JKPrivilige>();
	boolean selected;// to be used on the GUI when selected in the security
	private boolean editable = true;
	private int number;

	// panel

	/**
	 * Instantiates a new JK privilige.
	 */
	public JKPrivilige() {
	}

	// private JKPrivilige(final int priviligeId) {
	// this.priviligeId = priviligeId;
	// }

	/**
	 * Instantiates a new JK privilige.
	 *
	 * @param priviligeId
	 *            the privilige id
	 * @param name
	 *            the name
	 * @param parent
	 *            the parent
	 */
	public JKPrivilige(final int priviligeId, final String name, final JKPrivilige parent) {
		this(priviligeId, name, parent, parent == null ? 0 : parent.getChilds().size() + 1);
	}

	private JKPrivilige(final int priviligeId, final String name, final JKPrivilige parent, final int number) {
		this.priviligeId = priviligeId;
		this.priviligeName = name;
		this.parentPrivlige = parent;
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		final JKPrivilige that = (JKPrivilige) obj;
		return this.priviligeId == that.priviligeId;
	}

	/**
	 * Gets the childs.
	 *
	 * @return the childs
	 */
	public ArrayList<JKPrivilige> getChilds() {
		return this.childs;
	}

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Gets the parent privlige.
	 *
	 * @return the parent privlige
	 */
	public JKPrivilige getParentPrivlige() {
		return this.parentPrivlige;
	}

	/**
	 * Gets the privilige id.
	 *
	 * @return the priviligeId
	 */
	public int getPriviligeId() {
		return this.priviligeId;
	}

	/**
	 * Gets the privilige name.
	 *
	 * @return the priviligeName
	 */
	public String getPriviligeName() {
		return this.priviligeName;
	}

	/**
	 * Sets the childs.
	 *
	 * @param childs
	 *            the new childs
	 */
	public void setChilds(final ArrayList<JKPrivilige> childs) {
		this.childs = childs;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc
	 *            the new desc
	 */
	public void setDesc(final String desc) {
		this.desc = desc;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable
	 *            the new editable
	 */
	public void setEditable(final boolean editable) {
		this.editable = editable;
	};

	/**
	 * Sets the number.
	 *
	 * @param number
	 *            the new number
	 */
	public void setNumber(final int number) {
		this.number = number;
	}

	/**
	 * Sets the parent privlige.
	 *
	 * @param parentPrivlige
	 *            the new parent privlige
	 */
	public void setParentPrivlige(final JKPrivilige parentPrivlige) {
		this.parentPrivlige = parentPrivlige;
	}

	/**
	 * Sets the privilige id.
	 *
	 * @param priviligeId
	 *            the priviligeId to set
	 */
	public void setPriviligeId(final int priviligeId) {
		this.priviligeId = priviligeId;
	}

	/**
	 * Sets the privilige name.
	 *
	 * @param priviligeName
	 *            the priviligeName to set
	 */
	public void setPriviligeName(final String priviligeName) {
		this.priviligeName = priviligeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString(false);
	}

	/**
	 * To string.
	 *
	 * @param deep
	 *            the deep
	 * @return the string
	 */
	public String toString(final boolean deep) {
		final StringBuffer buf = new StringBuffer(this.getPriviligeName());
		if (deep) {
			buf.append(getPriviligeId());
			buf.append(",");
		}
		buf.append(getPriviligeName());
		if (deep && this.childs.size() > 0) {
			buf.append(this.childs.toString());
		}
		return buf.toString();
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		StringBuffer buf = new StringBuffer();
		if (getParentPrivlige() != null) {
			buf.append(parentPrivlige.getFullName().concat(" >"));
		}
		buf.append(getPriviligeName());
		return buf.toString();
	}
}

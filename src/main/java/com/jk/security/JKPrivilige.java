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

	public JKPrivilige() {
	}

	// private JKPrivilige(final int priviligeId) {
	// this.priviligeId = priviligeId;
	// }

	public JKPrivilige(final int priviligeId, final String name, final JKPrivilige parent) {
		this(priviligeId, name, parent, parent == null ? 0 : parent.getChilds().size() + 1);
	}

	private JKPrivilige(final int priviligeId, final String name, final JKPrivilige parent, final int number) {
		this.priviligeId = priviligeId;
		this.priviligeName = name;
		this.parentPrivlige = parent;
		this.number = number;
	}

	@Override
	public boolean equals(final Object obj) {
		final JKPrivilige that = (JKPrivilige) obj;
		return this.priviligeId == that.priviligeId;
	}

	public ArrayList<JKPrivilige> getChilds() {
		return this.childs;
	}

	public String getDesc() {
		return desc;
	}

	public int getNumber() {
		return this.number;
	}

	public JKPrivilige getParentPrivlige() {
		return this.parentPrivlige;
	}

	/**
	 * @return the priviligeId
	 */
	public int getPriviligeId() {
		return this.priviligeId;
	}

	/**
	 * @return the priviligeName
	 */
	public String getPriviligeName() {
		return this.priviligeName;
	}

	public void setChilds(final ArrayList<JKPrivilige> childs) {
		this.childs = childs;
	}

	public void setDesc(final String desc) {
		this.desc = desc;
	}

	public void setEditable(final boolean editable) {
		this.editable = editable;
	};

	public void setNumber(final int number) {
		this.number = number;
	}

	public void setParentPrivlige(final JKPrivilige parentPrivlige) {
		this.parentPrivlige = parentPrivlige;
	}

	/**
	 * @param priviligeId
	 *            the priviligeId to set
	 */
	public void setPriviligeId(final int priviligeId) {
		this.priviligeId = priviligeId;
	}

	/**
	 * @param priviligeName
	 *            the priviligeName to set
	 */
	public void setPriviligeName(final String priviligeName) {
		this.priviligeName = priviligeName;
	}

	@Override
	public String toString() {
		return toString(false);
	}

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
}

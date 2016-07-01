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

import java.util.Map;
import java.util.Set;

/**
 * The Class JKMapTableModel.
 *
 * @author Jalal Kiswani
 * @param <K>
 *            the key type
 * @param <V>
 *            the value type
 */
public class JKMapTableModel<K, V> extends JKTableModel {
	private Map<K, V> map;

	/**
	 * Instantiates a new JK map table model.
	 *
	 * @param map
	 *            the map
	 * @param keyLabel
	 *            the key label
	 * @param valueLabel
	 *            the value label
	 */
	public JKMapTableModel(Map<K, V> map, String keyLabel, String valueLabel) {
		this.map = map;
		addJKTableColumn(keyLabel);
		addJKTableColumn(valueLabel);
		Set<K> keySet = map.keySet();
		int row = 0;
		for (K k : keySet) {
			addRecord();
			setValueAt(k, row, 0);
			setValueAt(map.get(k), row++, 1);
		}
	}

}

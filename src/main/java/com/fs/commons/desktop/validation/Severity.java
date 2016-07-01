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
package com.fs.commons.desktop.validation;

import java.io.IOException;

/**
 * Indicates the severity of a problem. See Problem.Kind.
 *
 * @author Tim Boudreau
 */
public enum Severity {

	/**
	 * An information message for the user, which should not block them from
	 * proceeding but may provide advice
	 */
	INFO,
	/**
	 * A warning to the user that they should change a value, but which does not
	 * block them from proceeding
	 */
	WARNING,
	/**
	 * A fatal problem with user input which must be corrected
	 */
	FATAL;
}

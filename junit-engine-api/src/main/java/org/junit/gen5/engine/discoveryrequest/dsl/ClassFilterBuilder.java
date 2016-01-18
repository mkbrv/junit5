/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.engine.discoveryrequest.dsl;

import org.junit.gen5.engine.ClassFilter;
import org.junit.gen5.engine.FilterResult;
import org.junit.gen5.engine.discoveryrequest.ClassNameFilter;

public class ClassFilterBuilder {
	public static ClassFilter anyClass() {
		return testClass -> FilterResult.included("Any class");
	}

	public static ClassNameFilter pattern(String pattern) {
		return new ClassNameFilter(pattern);
	}
}
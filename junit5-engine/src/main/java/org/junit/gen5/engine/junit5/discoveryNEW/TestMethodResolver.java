/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.engine.junit5.discoveryNEW;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.gen5.commons.JUnitException;
import org.junit.gen5.engine.TestDescriptor;
import org.junit.gen5.engine.UniqueId;
import org.junit.gen5.engine.junit5.descriptor.ClassTestDescriptor;
import org.junit.gen5.engine.junit5.descriptor.MethodTestDescriptor;
import org.junit.gen5.engine.junit5.discovery.IsTestMethod;

public class TestMethodResolver implements ElementResolver {

	@Override
	public Optional<UniqueId> willResolve(AnnotatedElement element, TestDescriptor parent) {
		if (!(element instanceof Method))
			return Optional.empty();

		if (!(parent instanceof ClassTestDescriptor))
			return Optional.empty();

		Method testMethod = (Method) element;

		if (!new IsTestMethod().test(testMethod)) {
			return Optional.empty();
		}

		return Optional.of(parent.getUniqueId().append("method", testMethod.getName() + "()"));
	}

	@Override
	public TestDescriptor resolve(AnnotatedElement element, TestDescriptor parent, UniqueId uniqueId) {
		return resolveMethod((Method) element, (ClassTestDescriptor) parent, uniqueId);
	}

	private TestDescriptor resolveMethod(Method testMethod, ClassTestDescriptor parentClassDescriptor,
			UniqueId uniqueId) {
		return new MethodTestDescriptor(uniqueId, parentClassDescriptor.getTestClass(), testMethod);
	}
}

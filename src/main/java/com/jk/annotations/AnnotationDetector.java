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
package com.jk.annotations;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * The Class AnnotationDetector.
 *
 * @author Jalal Kiswani
 */
@Author(name = "Jalal H. Kiswani", date = "1/10/2014", version = "1.0")
public class AnnotationDetector {

	/**
	 * Utility method to scan the given package and handler for the annotation
	 * of the given class. Its uses the Spring annotation detector
	 *
	 * @param clas
	 *            the clas
	 * @param basePackage
	 *            the base package
	 * @param handler
	 *            the handler
	 */
	public static void scan(final Class<? extends Annotation> clas, final String[] basePackage, final AnnotationHandler handler) {
		final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.setResourceLoader(new PathMatchingResourcePatternResolver(Thread.currentThread().getContextClassLoader()));
		scanner.addIncludeFilter(new AnnotationTypeFilter(clas));
		for (final String pck : basePackage) {
			for (final BeanDefinition bd : scanner.findCandidateComponents(pck)) {
				handler.handleAnnotationFound(bd.getBeanClassName());
			}
		}
	}

	/**
	 * Scan as list.
	 *
	 * @param clas
	 *            the clas
	 * @param basePackage
	 *            the base package
	 * @return the list
	 */
	public static List<String> scanAsList(final Class<? extends Annotation> clas, final String... basePackage) {
		final List<String> classes = new ArrayList<>();
		scan(clas, basePackage, new AnnotationHandler() {

			@Override
			public void handleAnnotationFound(String className) {
				classes.add(className);
			}
		});
		return classes;
	}
}

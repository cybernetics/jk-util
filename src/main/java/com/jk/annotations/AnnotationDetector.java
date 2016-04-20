/*
 *
 */
package com.jk.annotations;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * The Class AnnotationDetector.
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
	public static void scan(Class clas, String[] basePackage, AnnotationHandler handler) {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.setResourceLoader(new PathMatchingResourcePatternResolver(Thread.currentThread().getContextClassLoader()));
		scanner.addIncludeFilter(new AnnotationTypeFilter(clas));
		for (String pck : basePackage) {
			for (BeanDefinition bd : scanner.findCandidateComponents(pck)) {
				handler.handleAnnotationFound(bd.getBeanClassName());
			}
		}
	}
}

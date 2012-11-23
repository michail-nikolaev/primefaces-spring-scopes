package org.nkey.primefaces.scopes.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:42
 */
@ComponentScan(basePackageClasses = BasePackageMarker.class)
@ImportResource({ "classpath*:applicationContext.xml" })
public class BasePackageMarker {
}

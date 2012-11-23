package org.nkey.primefaces.scopes.test.config;

import org.nkey.primefaces.scopes.test.BasePackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:42
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = BasePackageMarker.class)
@ImportResource({ "classpath*:*applicationContext.xml" })
public class WebConfiguration {
}

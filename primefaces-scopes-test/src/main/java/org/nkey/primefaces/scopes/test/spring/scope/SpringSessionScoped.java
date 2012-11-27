package org.nkey.primefaces.scopes.test.spring.scope;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author m.nikolaev Date: 21.11.12 Time: 1:01 File | Settings | File Templates.
 */
@Qualifier
@Scope("session")
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringSessionScoped {
}

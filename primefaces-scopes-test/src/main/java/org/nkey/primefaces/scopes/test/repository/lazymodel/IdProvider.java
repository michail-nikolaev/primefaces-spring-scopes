package org.nkey.primefaces.scopes.test.repository.lazymodel;

import java.io.Serializable;

/**
 * @author m.nikolaev Date: 11.12.12 Time: 3:03
 */
public interface IdProvider<ID extends Serializable> {
    ID getId();
}

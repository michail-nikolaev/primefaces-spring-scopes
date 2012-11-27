package org.nkey.primefaces.scopes.test.jsf;

import org.nkey.primefaces.scopes.test.spring.scope.SpringViewScoped;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * ViewScoped bean example.
 *
 * @author m.nikolaev
 */
@Component
@SpringViewScoped
public class NumberHolder implements Serializable {
    private Integer counter = 0;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public void increment() {
        counter++;
    }
}

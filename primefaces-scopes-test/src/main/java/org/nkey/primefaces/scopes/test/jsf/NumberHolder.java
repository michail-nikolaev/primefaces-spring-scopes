package org.nkey.primefaces.scopes.test.jsf;

import org.nkey.primefaces.scopes.test.spring.scope.SpringViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.Serializable;

/**
 * ViewScoped bean example.
 *
 * @author m.nikolaev
 */
@Component
@SpringViewScoped
public class NumberHolder implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(NumberHolder.class);
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

    @PreDestroy
    public void clean() {
        LOGGER.debug("Clean called for VewScope");
    }
}

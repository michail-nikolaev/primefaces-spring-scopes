package org.nkey.primefaces.scopes.test.jsf;

import org.nkey.primefaces.scopes.test.spring.Car;
import org.nkey.primefaces.scopes.test.spring.CarRepository;
import org.nkey.primefaces.scopes.test.spring.scope.SpringRequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:48
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@SpringRequestScoped
@Component
public class TableBean implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableBean.class);
    @Inject
    private CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.getRandomCars();
    }

    @PreDestroy
    public void clean() {
        LOGGER.debug("Clean called for VewScope");
    }
}

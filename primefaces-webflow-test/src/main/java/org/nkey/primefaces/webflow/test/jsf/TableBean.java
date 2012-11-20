package org.nkey.primefaces.webflow.test.jsf;

import org.nkey.primefaces.webflow.test.spring.Car;
import org.nkey.primefaces.webflow.test.spring.CarRepository;
import org.nkey.primefaces.webflow.test.spring.scope.ViewsScopedComponent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:48
 */

@ViewsScopedComponent
@Component
public class TableBean implements Serializable {

    @Inject
    private CarRepository carRepository;

    public List<Car> getCars() {
        return carRepository.getRandomCars();
    }
}

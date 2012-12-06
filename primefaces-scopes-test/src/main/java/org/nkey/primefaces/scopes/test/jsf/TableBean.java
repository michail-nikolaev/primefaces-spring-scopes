package org.nkey.primefaces.scopes.test.jsf;

import org.nkey.primefaces.scopes.test.domain.Car;
import org.nkey.primefaces.scopes.test.repository.CarRepository;
import org.nkey.primefaces.scopes.test.spring.scope.SpringRequestScoped;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:48
 */
@SpringRequestScoped
@Component
public class TableBean implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableBean.class);
    @Inject
    private CarRepository carRepository;
    private LazyDataModel<Car> carLazyDataModel;

    public LazyDataModel<Car> getCarsModel() {
        return carLazyDataModel;
    }

    @PostConstruct
    public void init() {
        carLazyDataModel = new SpringDataJPALazyDataModel<>(carRepository);
    }

    @PreDestroy
    public void clean() {
        LOGGER.debug("Clean called for RequestScope");
    }

    public void invalidateSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().setViewRoot(null);
    }
}

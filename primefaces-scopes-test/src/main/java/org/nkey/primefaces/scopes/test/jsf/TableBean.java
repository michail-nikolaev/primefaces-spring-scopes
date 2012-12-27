package org.nkey.primefaces.scopes.test.jsf;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.nkey.primefaces.scopes.test.domain.Car;
import org.nkey.primefaces.scopes.test.repository.CarRepository;
import org.nkey.primefaces.scopes.test.repository.lazymodel.HibernateSearchLazyDataModel;
import org.nkey.primefaces.scopes.test.spring.scope.SpringRequestScoped;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:48
 */
@SpringRequestScoped
@Component
public class TableBean implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TableBean.class);
    @Inject
    private CarRepository carRepository;
    @Inject
    private FullTextEntityManager fullTextEntityManager;
    private LazyDataModel<Car> carLazyDataModel;

    public LazyDataModel<Car> getCarsModel() {
        return carLazyDataModel;
    }

    @PostConstruct
    public void init() {
        carLazyDataModel = new HibernateSearchLazyDataModel<>(fullTextEntityManager, Car.class, "description");
    }

    @PreDestroy
    public void clean() {
        LOGGER.debug("Clean called for RequestScope");
    }

    public void invalidateSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().setViewRoot(null);
    }

    public List<String> autoComplete(String value) {
        return carRepository.getModels("%" + value + "%");
    }


    @SuppressWarnings("UnusedDeclaration")
    public static class ColumnModel {
        private String header;
        private String expression;
        private String order;

        public ColumnModel(String header, String expression, String order) {
            this.header = header;
            this.expression = expression;
            this.order = order;
        }

        public String getHeader() {
            return header;
        }

        public String getValue() {
            return resolveExpression(expression);
        }

        public String getOrder() {
            return order;
        }

        private String resolveExpression(String expression) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Application app = facesContext.getApplication();

            // Here we bind a value expression into the item,
            // so it can dynamically change its language
            ELContext elContext = facesContext.getELContext();
            ExpressionFactory expressionFactory = app.getExpressionFactory();
            ValueExpression valueExpression =
                    expressionFactory.createValueExpression(elContext, expression, Object.class);

            return valueExpression.getValue(elContext).toString();
        }
    }

    public List<ColumnModel> getColumns() {
        return Arrays
                .asList(new ColumnModel("Year <br/> Model", "#{function:concat2(car.year, car.model)}", "year;model"),
                        new ColumnModel("Color", "#{car.color}", "color"),
                        new ColumnModel("Manufacturer", "#{car.manufacturer}", "manufacturer"));
    }
}

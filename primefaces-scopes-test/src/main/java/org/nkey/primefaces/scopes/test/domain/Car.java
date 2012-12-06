package org.nkey.primefaces.scopes.test.domain;

import org.nkey.primefaces.scopes.test.jsf.SpringDataJPALazyDataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:49
 */
@Entity
public class Car implements Serializable, SpringDataJPALazyDataModel.IDProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String model;
    @Column
    private Integer year;
    @Column
    private String manufacturer;
    @Column
    private String color;

    public Car() {
    }

    public Car(String model, Integer year, String manufacturer, String color) {
        this.color = color;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

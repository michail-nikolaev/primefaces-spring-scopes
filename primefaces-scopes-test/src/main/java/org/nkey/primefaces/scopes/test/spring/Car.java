package org.nkey.primefaces.scopes.test.spring;

import java.io.Serializable;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:49
 */
public class Car implements Serializable {
    private String model;
    private Integer year;
    private String manufacturer;
    private String color;

    public Car() {}

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
}

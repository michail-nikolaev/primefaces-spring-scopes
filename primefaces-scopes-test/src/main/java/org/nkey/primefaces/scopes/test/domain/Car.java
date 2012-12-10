package org.nkey.primefaces.scopes.test.domain;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.nkey.primefaces.scopes.test.repository.lazymodel.IdProvider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author m.nikolaev Date: 20.11.12 Time: 22:49
 */
@Entity
@Indexed
public class Car implements Serializable, IdProvider {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String model;
    @Column @Field(index = Index.YES, analyze = Analyze.NO, store = Store.NO)
    private Integer year;
    @Column @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String manufacturer;
    @Column @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String color;

    @Transient
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    public String getDescription() {
        return String.format("%s %s %s %s", model, year, color, manufacturer);
    }

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

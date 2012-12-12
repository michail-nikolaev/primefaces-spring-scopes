package org.nkey.primefaces.scopes.test.repository;

import org.nkey.primefaces.scopes.test.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author m.nikolaev
 *         Date: 06.12.12
 *         Time: 21:30
 */
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select distinct c.model from Car as c where lower(c.model) like lower(?1)")
    List<String> getModels(String value);
}

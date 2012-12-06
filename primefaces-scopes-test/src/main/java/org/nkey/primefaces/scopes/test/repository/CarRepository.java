package org.nkey.primefaces.scopes.test.repository;

import org.nkey.primefaces.scopes.test.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author m.nikolaev
 *         Date: 06.12.12
 *         Time: 21:30
 */
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

}

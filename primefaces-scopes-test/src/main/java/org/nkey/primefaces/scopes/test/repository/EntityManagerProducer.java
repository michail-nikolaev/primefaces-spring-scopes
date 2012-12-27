package org.nkey.primefaces.scopes.test.repository;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.nkey.primefaces.scopes.test.spring.scope.SpringRequestScoped;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * @author m.nikolaev Date: 11.12.12 Time: 2:04
 */
@Configuration
public class EntityManagerProducer {
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @SpringRequestScoped
    public FullTextEntityManager fullTextEntityManager() {
        return Search.getFullTextEntityManager(entityManagerFactory.createEntityManager());
    }
}

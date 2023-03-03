/*
package com.example.dreamgames;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Configuration
public class JpaTestConfig {

    @Bean
    public TestEntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        TestEntityManager entityManager = new TestEntityManager(entityManagerFactory);
        EntityManagerHolder entityManagerHolder = new EntityManagerHolder(entityManager.getEntityManager());
        TransactionSynchronizationManager.bindResource(entityManagerFactory, entityManagerHolder);
        return entityManager;
    }*/
/*
    @Bean
    public void entityManagerCleanup(EntityManagerFactory entityManagerFactory) {
        TransactionSynchronizationManager.unbindResource(entityManagerFactory);
        EntityManagerHolder entityManagerHolder = (EntityManagerHolder) TransactionSynchronizationManager.getResource(entityManagerFactory);
        EntityManagerFactoryUtils.closeEntityManager(entityManagerHolder.getEntityManager());
    }
}*/

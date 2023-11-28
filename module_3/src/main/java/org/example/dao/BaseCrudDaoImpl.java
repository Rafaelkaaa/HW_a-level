package org.example.dao;

import org.example.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.UUID;

public class BaseCrudDaoImpl<O extends BaseEntity> implements BaseCrudDao <O>{
    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("jpa-hibernate-postgresql");
    protected final EntityManager entityManager = emf.createEntityManager();
    @Override
    public void create(O entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public O read(UUID id, Class clazz) {
        return (O) entityManager.find(clazz, id);
    }

    @Override
    public void update(O entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(UUID id, Class clazz) {
        entityManager.getTransaction().begin();
        entityManager.remove(read(id, clazz));
        entityManager.getTransaction().commit();
    }
}

package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.GroupDao;
import org.example.model.Group;

import java.util.List;

public class GroupDaoImpl implements GroupDao {
    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("jpa-hibernate-postgresql");
    private final EntityManager entityManager = emf.createEntityManager();

    @Override
    public void create(Group value) {
        entityManager.getTransaction().begin();
        entityManager.persist(value);
        entityManager.getTransaction().commit();
    }

    @Override
    public Group read(String id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void update(Group value) {
        entityManager.getTransaction().begin();
        entityManager.merge(value);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        entityManager.getTransaction().begin();
        entityManager.remove(read(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Group> findAll() {
        return entityManager.createQuery("select g from Group g").getResultList();
    }

    @Override
    public void addStudentToGroup(String studentId, String groupId) {
        entityManager.createNativeQuery("insert into stu_gro values (?,?)")
                .setParameter(1, groupId)
                .setParameter(2, studentId);
    }

    @Override
    public List<Group> findByStudentId(String id) {
        List<Group> groups = entityManager
                .createNativeQuery("SELECT * FROM classes.stu_gro " +
                        "INNER JOIN classes.groups " +
                        "ON classes.stu_gro.group_id = classes.groups.id " +
                        "WHERE classes.stu_gro.student_id =?")
                .setParameter(1, id)
                .getResultList();
        return groups;
    }

}

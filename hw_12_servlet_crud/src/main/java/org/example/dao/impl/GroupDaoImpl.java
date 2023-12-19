package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.GroupDao;
import org.example.model.Group;
import org.example.model.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    public Group read(UUID id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void update(Group value) {
        entityManager.getTransaction().begin();
        entityManager.merge(value);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(UUID id) {
        entityManager.getTransaction().begin();
        entityManager.remove(read(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Group> findAll() {
        entityManager.clear();
        return entityManager.createQuery("select g from Group g").getResultList();
    }

    @Override
    public void addStudentToGroup(UUID studentId, UUID groupId) {
        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, studentId);
        Group group = entityManager.find(Group.class, groupId);

        if (student != null && group != null) {
            Set<Group> groups = student.getGroups();
            if (groups == null) {
                groups = new HashSet<>();
            }
            groups.add(group);
            student.setGroups(groups);

            Set<Student> students = group.getStudents();
            if (students == null) {
                students = new HashSet<>();
            }
            students.add(student);
            group.setStudents(students);

            entityManager.merge(student);
            entityManager.merge(group);
        }

        entityManager.getTransaction().commit();
    }

    @Override
    public Set<Group> findByStudentId(UUID id) {
        return entityManager.find(Student.class, id).getGroups();
    }
}

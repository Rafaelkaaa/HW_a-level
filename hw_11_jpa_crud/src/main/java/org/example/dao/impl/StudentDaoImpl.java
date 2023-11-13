package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.StudentDao;
import org.example.model.Group;
import org.example.model.Student;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class StudentDaoImpl implements StudentDao {
    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("jpa-hibernate-postgresql");
    private final EntityManager entityManager = emf.createEntityManager();

    @Override
    public void create(Student value) {
        entityManager.getTransaction().begin();
        entityManager.persist(value);
        entityManager.getTransaction().commit();
    }

    @Override
    public Student read(UUID id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void update(Student value) {
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
    public List<Student> findAll() {
        return entityManager.createQuery("select s from Student s").getResultList();
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
    public Set<Student> findByGroupId(UUID id) {
        return entityManager.find(Group.class, id).getStudents();
    }
}

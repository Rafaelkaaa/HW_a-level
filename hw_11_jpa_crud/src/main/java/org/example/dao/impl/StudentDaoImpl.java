package org.example.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.StudentDao;
import org.example.model.Student;

import java.util.List;

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
    public Student read(String id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void update(Student value) {
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
    public List<Student> findAll() {
        return entityManager.createQuery("select s from Student s").getResultList();
    }

    @Override
    public void addStudentToGroup(String studentId, String groupId) {
        entityManager.createNativeQuery("insert into stu_gro values (?,?)")
                .setParameter(1, groupId)
                .setParameter(2, studentId);
    }

    @Override
    public List<Student> findByGroupId(String id) {
        List<Student> students = entityManager
                .createNativeQuery("SELECT * FROM classes.stu_gro " +
                        "INNER JOIN classes.students " +
                        "ON classes.stu_gro.student_id = classes.students.id " +
                        "WHERE classes.stu_gro.group_id =?")
                .setParameter(1, id)
                .getResultList();
        return students;
    }

}

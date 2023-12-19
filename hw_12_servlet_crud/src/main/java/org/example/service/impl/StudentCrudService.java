package org.example.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dao.StudentDao;
import org.example.dao.impl.StudentDaoImpl;
import org.example.model.Student;
import org.example.service.ClassesCrudService;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCrudService implements ClassesCrudService<Student> {
    final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void create(Student value) {
        studentDao.create(value);
    }

    @Override
    public Student read(String id) {
        return studentDao.read(UUID.fromString(id));
    }

    @Override
    public Collection<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void update(Student newValue) {
        studentDao.update(newValue);
    }

    @Override
    public void delete(String id) {
        studentDao.delete(UUID.fromString(id));
    }

    @Override
    public void addStudentToGroup(String studentId, String groupId) {
        studentDao.addStudentToGroup(UUID.fromString(studentId), UUID.fromString(groupId));
    }

    public List<Student> findByGroupId (String id){
        return studentDao.findByGroupId(UUID.fromString(id)).stream().toList();
    }
}

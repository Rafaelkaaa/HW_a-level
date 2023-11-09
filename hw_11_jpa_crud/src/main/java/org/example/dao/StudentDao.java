package org.example.dao;

import org.example.model.Student;

import java.util.List;

public interface StudentDao extends ClassDao<Student>{
    List<Student> findByGroupId (String id);
}

package org.example.dao;

import org.example.model.Student;

import java.util.Set;
import java.util.UUID;

public interface StudentDao extends ClassDao<Student>{
    Set<Student> findByGroupId (UUID id);
}

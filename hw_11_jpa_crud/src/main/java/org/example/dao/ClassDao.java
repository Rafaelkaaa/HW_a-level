package org.example.dao;

import org.example.model.BaseEntity;

import java.util.List;

public interface ClassDao<E extends BaseEntity> {
    void create(E value);
    E read(String id);
    void update(E value);
    void delete(String id);
    List<E> findAll();
    void addStudentToGroup(String studentId, String groupId);
}

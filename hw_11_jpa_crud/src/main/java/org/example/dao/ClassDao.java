package org.example.dao;

import org.example.model.BaseEntity;

import java.util.List;
import java.util.UUID;

public interface ClassDao<E extends BaseEntity> {
    void create(E value);
    E read(UUID id);
    void update(E value);
    void delete(UUID id);
    List<E> findAll();
    void addStudentToGroup(UUID studentId, UUID groupId);
}

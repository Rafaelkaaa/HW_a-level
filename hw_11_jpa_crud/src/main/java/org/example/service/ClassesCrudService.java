package org.example.service;

import org.example.model.BaseEntity;

import java.util.Collection;

public interface ClassesCrudService<O extends BaseEntity> {

    void create(O value);
    O read(String id);
    Collection<O> findAll();
    void update( O newValue);
    void delete(String id);
    void addStudentToGroup(String studentId, String groupId);
}

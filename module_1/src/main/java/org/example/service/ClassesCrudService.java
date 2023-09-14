package org.example.service;

import org.example.collections.CraftArrayList;
import org.example.model.BaseEntity;
import org.example.model.StudentGroupEntry;

public interface ClassesCrudService<O extends BaseEntity> {
    CraftArrayList<StudentGroupEntry> classes = new CraftArrayList();
    void create(O value);
    O read(String id);
    O read(int index);
    CraftArrayList<O> findAll();
    void update(O oldValue, O newValue);
    void delete(String id);
    void delete(int index);
    void addStudentToGroup(String studentId, String groupId);
}

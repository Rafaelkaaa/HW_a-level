package org.example.service;

import org.example.collections.CraftArrayList;
import org.example.model.BaseEntity;
import org.example.model.StudentGroupEntry;

public class ClassesService<O extends BaseEntity> {
     O read(String id, CraftArrayList<O> values) throws IllegalArgumentException {
        for (int i = 0; i < values.size(); i++) {
            O value = values.get(i);
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new IllegalArgumentException("There is no entity with the specified id");
    }

    void delete(String id, CraftArrayList<O> values) {
        for (int i = 0; i < values.size(); i++) {
            if(values.get(i).getId().equals(id)){
                values.remove(i);
                return;
            }
        }
    }

    void addStudentToGroup(String studentId, String groupId, CraftArrayList<StudentGroupEntry> classes){
         classes.create(new StudentGroupEntry(studentId, groupId));
    }
}

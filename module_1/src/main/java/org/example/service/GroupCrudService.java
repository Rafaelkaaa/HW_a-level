package org.example.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.collections.CraftArrayList;
import org.example.model.Group;
import org.example.model.StudentGroupEntry;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupCrudService implements ClassesCrudService<Group> {
    CraftArrayList<Group> groups;
    ClassesService<Group> classesService;

    public GroupCrudService() {
        groups = new CraftArrayList();
        classesService = new ClassesService();
    }

    public void create(Group group) {
        groups.create(group);
    }

    public Group read(String id) {
        return classesService.read(id, groups);
    }


    public Group read(int index) throws ArrayIndexOutOfBoundsException {
        return groups.get(index);
    }

    public CraftArrayList<Group> findByStudentId(String id) {
        CraftArrayList<Group> groupsWithStudent = new CraftArrayList();

        for (int i = 0; i < classes.size(); i++) {
            StudentGroupEntry entry = classes.get(i);

            if (entry.getStudentId().equals(id)) {
                groupsWithStudent.create(read(entry.getGroupId()));
            }
        }
        return groupsWithStudent;
    }

    public CraftArrayList<Group> findAll() {
        return groups;
    }

    public void update(Group oldGroup, Group newGroup) {oldGroup.setName(newGroup.getName());}

    public void delete(String id) {
        deleteEntriesWithGroupId(id);
        classesService.delete(id, groups);
    }

    public void delete(int index) {
        delete(groups.get(index).getId());
    }

    public void addStudentToGroup(String studentId, String groupId) {
        classesService.addStudentToGroup(studentId, groupId, classes);
    }

    private void deleteEntriesWithGroupId(String id) {
        for (int i = 0; i < classes.size(); i++) {
            String groupId = classes.get(i).getGroupId();

            if (groupId.equals(id)) {
                classes.remove(i);
            }
        }
    }
}
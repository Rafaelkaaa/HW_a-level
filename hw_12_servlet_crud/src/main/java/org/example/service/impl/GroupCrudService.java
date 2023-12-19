package org.example.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dao.GroupDao;
import org.example.dao.impl.GroupDaoImpl;
import org.example.model.Group;
import org.example.service.ClassesCrudService;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupCrudService implements ClassesCrudService<Group> {
    final GroupDao groupDao = new GroupDaoImpl();
    @Override
    public void create(Group value) {
        groupDao.create(value);
    }

    @Override
    public Group read(String id) {
        return groupDao.read(UUID.fromString(id));
    }

    @Override
    public Collection<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public void update(Group newValue) {
        groupDao.update(newValue);
    }

    @Override
    public void delete(String id) {
        groupDao.delete(UUID.fromString(id));
    }

    @Override
    public void addStudentToGroup(String studentId, String groupId) {
        groupDao.addStudentToGroup(UUID.fromString(studentId), UUID.fromString(groupId));
    }

    public List<Group> findByStudentId(String id) {
        return groupDao.findByStudentId(UUID.fromString(id)).stream().toList();
    }

}
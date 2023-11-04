package org.example.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dao.GroupDao;
import org.example.dao.impl.GroupDaoImpl;
import org.example.model.Group;
import org.example.service.ClassesCrudService;

import java.util.Collection;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupCrudService implements ClassesCrudService<Group> {
    final GroupDao groupDao = new GroupDaoImpl();
    @Override
    public void create(Group value) {
        groupDao.create(value);
    }

    @Override
    public Group read(String id) {
        return groupDao.read(id);
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
        groupDao.delete(id);
    }

    @Override
    public void addStudentToGroup(String studentId, String groupId) {
        groupDao.addStudentToGroup(studentId, groupId);
    }

    public List<Group> findByStudentId(String id) {
        return groupDao.findByStudentId(id);
    }

}
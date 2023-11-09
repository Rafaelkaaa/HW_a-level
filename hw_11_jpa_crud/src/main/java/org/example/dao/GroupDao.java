package org.example.dao;

import org.example.model.Group;

import java.util.List;

public interface GroupDao extends ClassDao<Group> {

    List<Group> findByStudentId (String id);
}

package org.example.dao;

import org.example.model.Group;

import java.util.Set;
import java.util.UUID;

public interface GroupDao extends ClassDao<Group> {

    Set<Group> findByStudentId (UUID id);
}

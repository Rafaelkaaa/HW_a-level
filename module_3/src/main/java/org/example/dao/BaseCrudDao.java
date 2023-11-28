package org.example.dao;

import org.example.entity.BaseEntity;

import java.util.UUID;

public interface BaseCrudDao<O extends BaseEntity> {
    void create(O entity);
    O read(UUID id, Class clazz);
    void update(O entity);
    void delete(UUID id, Class clazz);
}

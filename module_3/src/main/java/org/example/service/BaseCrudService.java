package org.example.service;

import org.example.entity.BaseEntity;

import java.util.UUID;

public interface BaseCrudService <O extends BaseEntity>{
    void create(O entity);
    O read(UUID id);
    void update(O entity);
    void delete(UUID id);
}

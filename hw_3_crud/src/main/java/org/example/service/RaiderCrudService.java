package org.example.service;

import org.example.craft.collections.CraftArrayList;
import org.example.entity.Raider;

public interface RaiderCrudService {

    void create(Raider raider);
    Raider read(String id);
    Raider read(int index);
    CraftArrayList<Raider> findAll();
    void update(Raider oldValue, Raider newValue);
    void delete(String id);
    void delete(int index);
}

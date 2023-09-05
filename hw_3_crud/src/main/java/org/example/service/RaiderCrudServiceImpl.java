package org.example.service;

import org.example.craft.collections.CraftArrayList;
import org.example.entity.Raider;

public class RaiderCrudServiceImpl implements RaiderCrudService {

    private CraftArrayList<Raider> raiders = new CraftArrayList();

    @Override
    public void create(Raider raider) {
        raiders.create(raider);
    }

    @Override
    public Raider read(String id) {
        for (int i = 0; i < raiders.size(); i++) {
            Raider raider = raiders.get(i);
            if (raider.getId().equals(id)) {
                return raider;
            }
        }
        throw new IllegalArgumentException("There is no raider with the specified id");
    }

    @Override
    public Raider read(int index) {
        return raiders.get(index);
    }


    public CraftArrayList<Raider> findAll() {
        return raiders;
    }

    @Override
    public void update(Raider oldValue, Raider newValue) {
        raiders.update(oldValue, newValue);
    }

    @Override
    public void delete(String id) {
        for (int i = 0; i < raiders.size(); i++) {
            Raider raider = raiders.get(i);
            if (raider.getId().equals(id)) {
                raiders.remove(raider);
            }
        }
    }

    @Override
    public void delete(int index) {
        raiders.remove(index);
    }
}

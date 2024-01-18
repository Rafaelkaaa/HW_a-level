package com.example.hw_13.service;

import com.example.hw_13.dto.BaseDto;

import java.util.Collection;

public interface BaseCrudService<O extends BaseDto> {

    void create(O value);

    O read(String id);

    Collection<O> findAll();

    void update(O newValue);

    void delete(String id);
}

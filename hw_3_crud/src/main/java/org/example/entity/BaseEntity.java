package org.example.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}

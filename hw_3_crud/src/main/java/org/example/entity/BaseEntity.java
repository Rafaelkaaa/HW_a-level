package org.example.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    private final String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString(); // а є ж ймовірність, що колись randomUUID поверне існуючий id?))
    }
}

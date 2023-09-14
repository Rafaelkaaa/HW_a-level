package org.example.model;

import com.github.f4b6a3.uuid.UuidCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    String id;

    public BaseEntity() {
        this.id = UuidCreator.getTimeBased().toString();
    }
}

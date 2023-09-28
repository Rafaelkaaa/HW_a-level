package org.example.model;

import com.github.f4b6a3.uuid.UuidCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    final String id;

    public BaseEntity() {
        this.id = UuidCreator.getTimeBased().toString();
    }
}

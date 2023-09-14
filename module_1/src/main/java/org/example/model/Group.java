package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Group extends BaseEntity {
    String name;

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", id='" + getId() + '\'' +
                '}';
    }
}

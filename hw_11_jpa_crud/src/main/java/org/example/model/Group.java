package org.example.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group extends BaseEntity {
    @Column(unique=true)
    String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "stu_gro",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    Set<Student> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return getId().equals(group.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}

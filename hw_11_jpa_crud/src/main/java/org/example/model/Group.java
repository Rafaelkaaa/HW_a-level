package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
public class Group extends BaseEntity {
    String name;

    @ManyToMany( cascade = { CascadeType.ALL })
    @JoinTable(
            name = "stu_gro",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    Set<Student> students = new HashSet<>();
}

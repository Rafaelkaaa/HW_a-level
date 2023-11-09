package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
@Entity
@Table(name = "students")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity {
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Positive
    int age;
    @ManyToMany(mappedBy = "students")
    Set<Group> groups;
}

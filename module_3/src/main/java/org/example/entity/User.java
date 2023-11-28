package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Account> accounts;
}

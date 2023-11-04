package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity {
    String firstName;
    String lastName;
    int age;

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
            return;
        }
        throw new IllegalArgumentException("Age should be positive number");
    }
}

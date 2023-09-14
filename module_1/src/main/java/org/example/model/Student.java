package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
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

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", id='" + getId() + '\'' +
                '}';
    }
}

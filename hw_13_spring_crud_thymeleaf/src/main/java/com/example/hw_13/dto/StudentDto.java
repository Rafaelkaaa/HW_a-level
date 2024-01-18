package com.example.hw_13.dto;

import com.example.hw_13.entity.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto extends BaseDto {
    public StudentDto(Student student) {
        setId(student.getId().toString());
        setFirstName(student.getFirstName());
        setLastName(student.getLastName());
        setAge(student.getAge());
    }

    String firstName;
    String lastName;
    int age;
}

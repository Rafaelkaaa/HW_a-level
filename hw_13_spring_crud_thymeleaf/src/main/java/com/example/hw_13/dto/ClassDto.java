package com.example.hw_13.dto;

import com.example.hw_13.entity.Group;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassDto {
    public ClassDto(Group group) {
        this.group = new GroupDto(group);
        if (group.getStudents() != null) {
            this.students = group.getStudents()
                    .stream()
                    .map(StudentDto::new)
                    .collect(Collectors.toList());
        }
    }

    GroupDto group;
    List<StudentDto> students;
}

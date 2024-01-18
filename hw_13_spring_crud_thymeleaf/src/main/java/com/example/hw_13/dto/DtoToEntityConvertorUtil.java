package com.example.hw_13.dto;

import com.example.hw_13.entity.Group;
import com.example.hw_13.entity.Student;

import java.util.UUID;

public class DtoToEntityConvertorUtil {

    public static Student convertStudentDto(StudentDto studentDto) {
        Student student = new Student();
        if (studentDto.getId() != null) {
            student.setId(UUID.fromString(studentDto.getId()));
        }
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAge(studentDto.getAge());
        return student;
    }

    public static Group convertGroupDto(GroupDto groupDto) {
        Group group = new Group();
        if (groupDto.getId() != null) {
            group.setId(UUID.fromString(groupDto.getId()));
        }
        group.setName(groupDto.getName());
        return group;
    }
}

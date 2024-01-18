package com.example.hw_13.service.impl;

import com.example.hw_13.dao.GroupRepository;
import com.example.hw_13.dao.StudentRepository;
import com.example.hw_13.dto.DtoToEntityConvertorUtil;
import com.example.hw_13.dto.StudentDto;
import com.example.hw_13.entity.Group;
import com.example.hw_13.entity.Student;
import com.example.hw_13.service.BaseCrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class StudentService implements BaseCrudService<StudentDto> {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public void create(StudentDto value) {
        studentRepository.save(DtoToEntityConvertorUtil.convertStudentDto(value));
    }

    @Override
    public StudentDto read(String id) {
        Student student = studentRepository
                .findById(UUID.fromString(id))
                .get();
        return new StudentDto(student);
    }

    @Override
    public Collection<StudentDto> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void update(StudentDto newValue) {
        studentRepository.save(DtoToEntityConvertorUtil.convertStudentDto(newValue));
    }

    @Override
    public void delete(String id) {
        UUID studentUUID = UUID.fromString(id);
        Student student = studentRepository.findById(studentUUID).get();
        student.getGroups().forEach(group -> {
            group.getStudents().remove(student);
            groupRepository.save(group);
        });
        student.setGroups(null);

        studentRepository.deleteById(studentUUID);
    }

    public void addStudentToGroup(String studentId, String groupId) {
        Student student = studentRepository.findById(UUID.fromString(studentId)).get();
        Group group = groupRepository.findById(UUID.fromString(groupId)).get();
        student.getGroups().add(group);
        group.getStudents().add(student);
        groupRepository.save(group);
        studentRepository.save(student);
    }

    public Map<String, List<StudentDto>> findStudentsNotInGroup() {
        Map<String, List<StudentDto>> studentsNotInGroup = new HashMap<>();
        groupRepository.findAll()
                .forEach(group ->
                        studentsNotInGroup.put(group.getId().toString(), studentRepository.findAll()
                                .stream()
                                .filter(student -> !group.getStudents().contains(student))
                                .map(StudentDto::new)
                                .toList()));
        return studentsNotInGroup;
    }
}

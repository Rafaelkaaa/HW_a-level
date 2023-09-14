package org.example.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.collections.CraftArrayList;
import org.example.model.Student;
import org.example.model.StudentGroupEntry;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCrudService implements ClassesCrudService<Student> {
    CraftArrayList<Student> students = new CraftArrayList();
    ClassesService<Student> classesService;

    public void create(Student student) {
        students.create(student);
    }

    public Student read(String id) {
        if (classesService == null) {
            classesService = new ClassesService();
        }
        return classesService.read(id, students);
    }

    public Student read(int index) {
        return students.get(index);
    }

    public CraftArrayList<Student> findByGroupId(String id) {
        CraftArrayList<Student> studentsFromGroup = new CraftArrayList();

        for (int i = 0; i < classes.size(); i++) {
            StudentGroupEntry entry = classes.get(i);

            if (entry.getGroupId().equals(id)) {
                studentsFromGroup.create(read(entry.getStudentId()));
            }
        }
        return studentsFromGroup;
    }

    public CraftArrayList<Student> findAll() {
        return students;
    }

    public void update(Student oldStudent, Student newStudent) {
        oldStudent.setFirstName(newStudent.getFirstName());
        oldStudent.setLastName(newStudent.getLastName());
        oldStudent.setAge(newStudent.getAge());
    }

    public void delete(String id) {
        deleteEntriesWithStudentId(id);
        if (classesService == null) {
            classesService = new ClassesService();
        }

        classesService.delete(id, students);
    }

    public void delete(int index) {
        delete(students.get(index).getId());
    }

    public void addStudentToGroup(String studentId, String groupId) {
        classesService.addStudentToGroup(studentId, groupId, classes);
    }

    private void deleteEntriesWithStudentId(String id) {
        for (int i = 0; i < classes.size(); i++) {
            String studentId = classes.get(i).getStudentId();

            if (studentId.equals(id)) {
                classes.remove(i);
            }
        }
    }
}

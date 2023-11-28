package ru.hogwarts.school.service;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudent(Long id);
    Student updateStudent(Long id, Student student);
    void removeStudent(Long id);
    List<Student> getStudentsByAge(Integer age);
    List<Student> getStudentsWithAgeBetween(Integer min, Integer max);
    List<Student> getStudentsFromFaculty(Long facultyId);

}

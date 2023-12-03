package ru.hogwarts.school.service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudent(Long id);
    Student updateStudent(Long id, Student student);
    void removeStudent(Long id);
    List<Student> findStudentsByAge(Integer age);
    List<Student> findStudentsWithAgeBetween(Integer min, Integer max);
    List<Student> findStudentsFromFaculty(Faculty faculty);

}
